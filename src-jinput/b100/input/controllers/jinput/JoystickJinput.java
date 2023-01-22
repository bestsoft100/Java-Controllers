package b100.input.controllers.jinput;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.Joystick;
import net.java.games.input.Component;

public class JoystickJinput extends Joystick {

	private Component xComp;
	private Component yComp;
	
	public JoystickJinput(int id, Component x, Component y, Button button) {
		super(id, button);
		
		this.xComp = x;
		this.yComp = y;
		this.button = button;
	}
	
	@Override
	protected float pollX() throws ControllerDisconnectException {
		return xComp.getPollData();
	}
	
	@Override
	protected float pollY() throws ControllerDisconnectException {
		return -yComp.getPollData();
	}

}
