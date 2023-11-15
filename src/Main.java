import audio.Audio;
import audio.PulseChannel;
import io.github.libsdl4j.api.gamecontroller.SDL_GameControllerAxis;
import io.github.libsdl4j.api.gamecontroller.SDL_GameControllerButton;
import sdl.GameController;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		// Create four pulse channels
		PulseChannel pulse0 = Audio.getInstance().makePulse();
		PulseChannel pulse1 = Audio.getInstance().makePulse();
		PulseChannel pulse2 = Audio.getInstance().makePulse();
		PulseChannel pulse3 = Audio.getInstance().makePulse();
		// Get a controller
		GameController controller = GameController.get(0);

		while (true) {
			// Check each of the four face buttons and use them to play tones
			if (controller.getRawButton(SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_A)) {
				pulse0.startTone(440);
			} else {
				pulse0.stopTone();
			}
			if (controller.getRawButton(SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_B)) {
				pulse1.startTone(554.37);
			} else {
				pulse1.stopTone();
			}
			if (controller.getRawButton(SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_Y)) {
				pulse2.startTone(659.25);
			} else {
				pulse2.stopTone();
			}
			if (controller.getRawButton(SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_X)) {
				pulse3.startTone(880);
			} else {
				pulse3.stopTone();
			}

			// Use the guide button (PS button on PS4 controllers) as a quit button
			if (controller.getRawButton(SDL_GameControllerButton.SDL_CONTROLLER_BUTTON_GUIDE))
				break;
			// Sleep 1ms to avoid wasting lots of CPU
			Thread.sleep(1);
		}
		// Shutdown the audio and controller subsystems
		Audio.getInstance().shutdown();
		sdl.Manager.getInstance().shutdown();
	}
}
