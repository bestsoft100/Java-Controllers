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
	private ControllerIndex index;
	
	public ControllerJamepad(ControllerLibraryJamepad library, ControllerIndex controllerIndex, ControllerInputHandler controllerInputHandler) {
		super(controllerInputHandler);
		
		this.library = library;
		this.index = controllerIndex;
		
		addButton(new ButtonJamepad(BUTTON_A, controllerIndex, ControllerButton.A));
		addButton(new ButtonJamepad(BUTTON_B, controllerIndex, ControllerButton.B));
		
		addButton(new ButtonJamepad(BUTTON_X, controllerIndex, ControllerButton.X));
		addButton(new ButtonJamepad(BUTTON_Y, controllerIndex, ControllerButton.Y));
		
		addButton(new ButtonJamepad(BUTTON_START, controllerIndex, ControllerButton.START));
		addButton(new ButtonJamepad(BUTTON_SELECT, controllerIndex, ControllerButton.BACK));
		addButton(new ButtonJamepad(BUTTON_HOME, controllerIndex, ControllerButton.GUIDE));
		
		addButton(new ButtonJamepad(BUTTON_L1, controllerIndex, ControllerButton.LEFTBUMPER));
		addButton(new ButtonJamepad(BUTTON_R1, controllerIndex, ControllerButton.RIGHTBUMPER));
		
		addButton(new AxisButtonJamepad(BUTTON_L2, controllerIndex, ControllerAxis.TRIGGERLEFT));
		addButton(new AxisButtonJamepad(BUTTON_R2, controllerIndex, ControllerAxis.TRIGGERRIGHT));
		
		addButton(new ButtonJamepad(BUTTON_D_UP, controllerIndex, ControllerButton.DPAD_UP));
		addButton(new ButtonJamepad(BUTTON_D_LEFT, controllerIndex, ControllerButton.DPAD_LEFT));
		addButton(new ButtonJamepad(BUTTON_D_DOWN, controllerIndex, ControllerButton.DPAD_DOWN));
		addButton(new ButtonJamepad(BUTTON_D_RIGHT, controllerIndex, ControllerButton.DPAD_RIGHT));
		
		Button joyLeftButton = addButton(new ButtonJamepad(BUTTON_LEFT_STICK, controllerIndex, ControllerButton.LEFTSTICK));
		Button joyRightButton = addButton(new ButtonJamepad(BUTTON_RIGHT_STICK, controllerIndex, ControllerButton.RIGHTSTICK));
		
		addJoystick(new JoystickJamepad(STICK_LEFT, joyLeftButton, controllerIndex, ControllerAxis.LEFTX, ControllerAxis.LEFTY));
		addJoystick(new JoystickJamepad(STICK_RIGHT, joyRightButton, controllerIndex, ControllerAxis.RIGHTX, ControllerAxis.RIGHTY));
	}
	
	@Override
	public String getName() {
		try {
			return index.getName();
		}catch (ControllerUnpluggedException e) {
			return null;
		}
	}

	@Override
	public ControllerLibrary getLibrary() {
		return library;
	}

	@Override
	public boolean isConnected() {
		return index.isConnected();
	}
	
	public ControllerIndex getIndex() {
		return index;
	}

}
