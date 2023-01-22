package b100.input.controllers.jamepad;

import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerUnpluggedException;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.Joystick;

public class JoystickJamepad extends Joystick {

	private ControllerIndex controllerIndex;
	
	private ControllerAxis x;
	private ControllerAxis y;
	
	public JoystickJamepad(int id, Button button, ControllerIndex controllerIndex, ControllerAxis x, ControllerAxis y) {
		super(id, button);
		
		this.x = x;
		this.y = y;
		
		this.controllerIndex = controllerIndex;
	}

	@Override
	protected float pollX() throws ControllerDisconnectException {
		try {
			return controllerIndex.getAxisState(x);
		} catch (ControllerUnpluggedException e) {
			throw new ControllerDisconnectException();
		}
	}

	@Override
	protected float pollY() throws ControllerDisconnectException {
		try {
			return controllerIndex.getAxisState(y);
		}catch (ControllerUnpluggedException e) {
			throw new ControllerDisconnectException();
		}
	}

}
