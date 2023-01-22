package b100.input.controllers.jamepad;

import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerUnpluggedException;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;

public class ButtonJamepad extends Button {

	private final ControllerIndex index;
	private final ControllerButton button;
	
	public ButtonJamepad(int id, ControllerIndex index, ControllerButton button) {
		super(id);
		this.button = button;
		this.index = index;
	}
	
	@Override
	protected boolean pollState() throws ControllerDisconnectException {
		try{
			return index.isButtonPressed(button);
		}catch (ControllerUnpluggedException e) {
			throw new ControllerDisconnectException();
		}
	}

}
