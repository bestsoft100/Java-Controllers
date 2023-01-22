package b100.input.controllers.jamepad;

import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerUnpluggedException;

import b100.input.controllers.Button;
import b100.input.controllers.Controller;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;

public class ControllerJamepad extends Controller {
	
	private ControllerLibraryJamepad library;
	private ControllerIndex controllerIndex;
	
	public ControllerJamepad(ControllerLibraryJamepad library, ControllerIndex controllerIndex, ControllerInputHandler controllerInputHandler) {
		super(controllerInputHandler);
		
		this.library = library;
		this.controllerIndex = controllerIndex;
		
		addButton(new ButtonJamepad(BUTTON_A, controllerIndex, ControllerButton.A));
		addButton(new ButtonJamepad(BUTTON_B, controllerIndex, ControllerButton.B));
		
		addButton(new ButtonJamepad(BUTTON_X, controllerIndex, ControllerButton.X));
		addButton(new ButtonJamepad(BUTTON_Y, controllerIndex, ControllerButton.Y));
		
		addButton(new ButtonJamepad(BUTTON_START, controllerIndex, ControllerButton.START));
		addButton(new ButtonJamepad(BUTTON_SELECT, controllerIndex, ControllerButton.GUIDE));
		
		addButton(new ButtonJamepad(BUTTON_L1, controllerIndex, ControllerButton.LEFTBUMPER));
		addButton(new ButtonJamepad(BUTTON_R1, controllerIndex, ControllerButton.RIGHTBUMPER));
		
		addButton(new ButtonJamepad(BUTTON_D_UP, controllerIndex, ControllerButton.DPAD_UP));
		addButton(new ButtonJamepad(BUTTON_D_LEFT, controllerIndex, ControllerButton.DPAD_LEFT));
		addButton(new ButtonJamepad(BUTTON_D_DOWN, controllerIndex, ControllerButton.DPAD_DOWN));
		addButton(new ButtonJamepad(BUTTON_D_RIGHT, controllerIndex, ControllerButton.DPAD_RIGHT));
		
		Button joyLeftButton = addButton(new ButtonJamepad(BUTTON_JOY_LEFT, controllerIndex, ControllerButton.LEFTSTICK));
		Button joyRightButton = addButton(new ButtonJamepad(BUTTON_JOY_RIGHT, controllerIndex, ControllerButton.RIGHTSTICK));
		
		addJoystick(new JoystickJamepad(JOY_LEFT, joyLeftButton, controllerIndex, ControllerAxis.LEFTX, ControllerAxis.LEFTY));
		addJoystick(new JoystickJamepad(JOY_RIGHT, joyRightButton, controllerIndex, ControllerAxis.RIGHTX, ControllerAxis.RIGHTY));
	}
	
	@Override
	public String getName() {
		try {
			return controllerIndex.getName();
		}catch (ControllerUnpluggedException e) {
			return null;
		}
	}

	@Override
	public ControllerLibrary getLibrary() {
		return library;
	}
	
	public boolean isConnected() {
		return controllerIndex.isConnected();
	}

}
