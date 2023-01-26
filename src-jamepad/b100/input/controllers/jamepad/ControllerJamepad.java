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
		
		addButton(new ButtonJamepad(BUTTON_A, index, ControllerButton.A));
		addButton(new ButtonJamepad(BUTTON_B, index, ControllerButton.B));
		
		addButton(new ButtonJamepad(BUTTON_X, index, ControllerButton.X));
		addButton(new ButtonJamepad(BUTTON_Y, index, ControllerButton.Y));
		
		addButton(new ButtonJamepad(BUTTON_START, index, ControllerButton.START));
		addButton(new ButtonJamepad(BUTTON_SELECT, index, ControllerButton.BACK));
		addButton(new ButtonJamepad(BUTTON_HOME, index, ControllerButton.GUIDE));
		
		addButton(new ButtonJamepad(BUTTON_L1, index, ControllerButton.LEFTBUMPER));
		addButton(new ButtonJamepad(BUTTON_R1, index, ControllerButton.RIGHTBUMPER));
		
		addButton(new AxisButtonJamepad(BUTTON_L2, index, ControllerAxis.TRIGGERLEFT));
		addButton(new AxisButtonJamepad(BUTTON_R2, index, ControllerAxis.TRIGGERRIGHT));
		
		addButton(new ButtonJamepad(BUTTON_D_UP, index, ControllerButton.DPAD_UP));
		addButton(new ButtonJamepad(BUTTON_D_LEFT, index, ControllerButton.DPAD_LEFT));
		addButton(new ButtonJamepad(BUTTON_D_DOWN, index, ControllerButton.DPAD_DOWN));
		addButton(new ButtonJamepad(BUTTON_D_RIGHT, index, ControllerButton.DPAD_RIGHT));
		
		Button buttonStickLeft = addButton(new ButtonJamepad(BUTTON_LEFT_STICK, controllerIndex, ControllerButton.LEFTSTICK));
		Button buttonStickRight = addButton(new ButtonJamepad(BUTTON_RIGHT_STICK, controllerIndex, ControllerButton.RIGHTSTICK));
		
		addJoystick(new JoystickJamepad(STICK_LEFT, buttonStickLeft, controllerIndex, ControllerAxis.LEFTX, ControllerAxis.LEFTY));
		addJoystick(new JoystickJamepad(STICK_RIGHT, buttonStickRight, controllerIndex, ControllerAxis.RIGHTX, ControllerAxis.RIGHTY));
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
