package sdl;

import io.github.libsdl4j.api.Sdl;
import io.github.libsdl4j.api.SdlSubSystemConst;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.event.SDL_EventType;
import io.github.libsdl4j.api.event.SdlEvents;
import io.github.libsdl4j.api.gamecontroller.SDL_GameController;
import io.github.libsdl4j.api.gamecontroller.SdlGamecontroller;
import io.github.libsdl4j.api.hints.SdlHints;
import io.github.libsdl4j.api.hints.SdlHintsConst;
import io.github.libsdl4j.api.joystick.SDL_JoystickID;
import io.github.libsdl4j.api.joystick.SdlJoystick;
import io.github.libsdl4j.api.sensor.SDL_SensorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager {
	private static Manager instance;
	private boolean running;
	private SDLThread thread;
	private ArrayList<GameController> controllers = new ArrayList<>();
	private Map<SDL_JoystickID, Integer> jidLookup = new HashMap<>();

	private static class SDLThread extends Thread {
		final Manager manager;
		SDLThread(Manager manager) {
			this.manager = manager;
		}

		@Override
		public void run() {
			final int subsystems = SdlSubSystemConst.SDL_INIT_GAMECONTROLLER | SdlSubSystemConst.SDL_INIT_EVENTS;
			SdlHints.SDL_SetHint(SdlHintsConst.SDL_HINT_JOYSTICK_HIDAPI_PS4_RUMBLE, "1");
			Sdl.SDL_InitSubSystem(subsystems);
			SdlGamecontroller.SDL_GameControllerEventState(1);
			try {
				SDL_Event evt = new SDL_Event();
				while (manager.running && SdlEvents.SDL_WaitEvent(evt) != 0) {
					switch (evt.type) {
						case SDL_EventType.SDL_CONTROLLERDEVICEADDED: {
							SDL_JoystickID jid = manager.getController(evt.cdevice.which).connect(evt.cdevice.which);
							System.out.println("Controller added: " + evt.cdevice.which + " => " + jid.intValue());
							synchronized (manager) {
								manager.jidLookup.put(jid, evt.cdevice.which);
							}
							break;
						}
						case SDL_EventType.SDL_CONTROLLERDEVICEREMAPPED: {
							SDL_JoystickID jid = manager.getController(evt.cdevice.which).disconnect();
							synchronized (manager) {
								manager.jidLookup.remove(jid);
							}
							break;
						}
						case SDL_EventType.SDL_CONTROLLERBUTTONDOWN:
						case SDL_EventType.SDL_CONTROLLERBUTTONUP: {
							GameController controller = manager.getController(evt.cbutton.which);
							if (controller != null) {
								controller.buttons[evt.cbutton.button] = evt.type == SDL_EventType.SDL_CONTROLLERBUTTONDOWN;
							}
							break;
						}
						case SDL_EventType.SDL_CONTROLLERAXISMOTION: {
							GameController controller = manager.getController(evt.caxis.which);
							if (controller != null) {
								controller.axes[evt.caxis.axis] = Math.max((float)evt.caxis.value / 32767.f, -1);
							}
							break;
						}
						case SDL_EventType.SDL_CONTROLLERSENSORUPDATE: {
							GameController controller = manager.getController(evt.csensor.which);
							if (evt.csensor.sensor == SDL_SensorType.SDL_SENSOR_ACCEL) {
								controller.accel = evt.csensor.data;
							} else if (evt.csensor.sensor == SDL_SensorType.SDL_SENSOR_GYRO) {
								controller.gyro = evt.csensor.data;
							}
							break;
						}
					}
				}
			} finally {
				Sdl.SDL_QuitSubSystem(subsystems);
			}
		}
	}

	private Manager() {
		thread = new SDLThread(this);
		start();
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread.start();
	}

	public void shutdown() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println("SDL thread failed to join: " + e.getLocalizedMessage());
		}
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	GameController getControllerInternal(int idx) {
		while (controllers.size() < idx + 1) {
			controllers.add(new GameController());
		}
		return controllers.get(idx);
	}

	public GameController getController(int idx) {
		synchronized (this) {
			return getControllerInternal(idx);
		}
	}

	public GameController getController(SDL_JoystickID jid) {
		synchronized (this) {
			if (jidLookup.containsKey(jid)) {
				return getControllerInternal(jidLookup.get(jid));
			} else {
				return null;
			}
		}
	}
}