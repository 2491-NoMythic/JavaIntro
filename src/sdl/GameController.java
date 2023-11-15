package sdl;

import io.github.libsdl4j.api.Sdl;
import io.github.libsdl4j.api.SdlSubSystemConst;
import io.github.libsdl4j.api.gamecontroller.SDL_GameController;
import io.github.libsdl4j.api.gamecontroller.SDL_GameControllerAxis;
import io.github.libsdl4j.api.gamecontroller.SDL_GameControllerButton;
import io.github.libsdl4j.api.gamecontroller.SdlGamecontroller;
import io.github.libsdl4j.api.joystick.SDL_JoystickID;
import io.github.libsdl4j.api.joystick.SdlJoystick;
import io.github.libsdl4j.api.sensor.SDL_Sensor;
import io.github.libsdl4j.api.sensor.SDL_SensorType;

import java.util.Arrays;

public class GameController {
	SDL_GameController gameController;
	SDL_JoystickID joystickID;

	boolean[] buttons = new boolean[SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_MAX];
	float[] axes = new float[SDL_GameControllerAxis.SDL_CONTROLLER_AXIS_MAX];
	float[] gyro = new float[3];
	float[] accel = new float[3];

	public GameController() {
	}

	private void clearData() {
		Arrays.fill(buttons, false);
		Arrays.fill(axes, 0);
		gyro = new float[3];
		accel = new float[3];
	}

	private void enableSensor(int sensor) {
		if (SdlGamecontroller.SDL_GameControllerHasSensor(gameController, sensor)) {
			SdlGamecontroller.SDL_GameControllerSetSensorEnabled(gameController, sensor, true);
		}
	}

	SDL_JoystickID connect(int id) {
		synchronized (this) {
			if (gameController == null) {
				gameController = SdlGamecontroller.SDL_GameControllerOpen(id);
				clearData();
				if (gameController != null) {
					enableSensor(SDL_SensorType.SDL_SENSOR_ACCEL);
					enableSensor(SDL_SensorType.SDL_SENSOR_GYRO);
				}
			}
			if (gameController == null)
				joystickID = new SDL_JoystickID(-1);
			else
				joystickID = SdlJoystick.SDL_JoystickInstanceID(SdlGamecontroller.SDL_GameControllerGetJoystick(gameController));
			return joystickID;
		}
	}

	SDL_JoystickID disconnect() {
		synchronized (this) {
			if (gameController != null) {
				SdlGamecontroller.SDL_GameControllerClose(gameController);
				gameController = null;
				clearData();
			}
			SDL_JoystickID old = joystickID;
			joystickID = new SDL_JoystickID(-1);
			return old;
		}
	}

	/// Is the controller connected to the computer?
	public boolean isConnected() {
		return gameController != null;
	}

	/// Get the controller with the given ID
	public static GameController get(int i) {
		return Manager.getInstance().getController(i);
	}

	/// Get the value of a button using the SDL button ID
	public boolean getRawButton(int idx) {
		if (idx < buttons.length) {
			return buttons[idx];
		} else {
			return false;
		}
	}

	/// Get the value of an axis using the SDL axis ID
	public double getRawAxis(int idx) {
		if (idx < axes.length) {
			return axes[idx];
		} else {
			return 0;
		}
	}

	/// Get a value from the controller's accelerometer
	public double getAccelValue(int idx) {
		float[] data = accel;
		if (idx < data.length) {
			return data[idx];
		} else {
			return 0;
		}
	}

	/// Get a value from the controller's gyroscrope
	public double getGyroValue(int idx) {
		float[] data = gyro;
		if (idx < data.length) {
			return data[idx];
		} else {
			return 0;
		}
	}

	public float[] getAccel() {
		return Arrays.copyOf(accel, 3);
	}

	public float[] getGyro() {
		return Arrays.copyOf(gyro, 3);
	}

	@Override
	protected void finalize() {
	}
}