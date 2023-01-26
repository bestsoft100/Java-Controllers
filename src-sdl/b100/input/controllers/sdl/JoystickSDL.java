package b100.input.controllers.sdl;

import static org.libsdl.SDL.*;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.Joystick;

public class JoystickSDL extends Joystick {

	private ControllerSDL controller;
	private int x;
	private int y;
	
	public JoystickSDL(int id, Button button, ControllerSDL controller, int x, int y) {
		super(id, button);
		
		this.controller = controller;
		this.x = x;
		this.y = y;
	}

	@Override
	protected float pollX() throws ControllerDisconnectException {
		return SDL_GameControllerGetAxis(controller.getPointer(), x) / 32767.0f;
	}

	@Override
	protected float pollY() throws ControllerDisconnectException {
		return (SDL_GameControllerGetAxis(controller.getPointer(), y) / 32767.0f) * -1.0f;
	}

}
