package b100.input.controllers.jamepad;

import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;

import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;

public class ControllerLibraryJamepad extends ControllerLibrary {
	
	private final int controllerCount;
	
	private ControllerManager controllerManager;
	
	public ControllerLibraryJamepad(int controllerCount) {
		this.controllerCount = controllerCount;
	}
	
	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		this.controllerManager = new ControllerManager(controllerCount);
		this.controllerManager.initSDLGamepad();
		
		for(int i=0; i < controllerCount; i++) {
			ControllerIndex controllerIndex = controllerManager.getControllerIndex(i);
			
			controllers.add(new ControllerJamepad(this, controllerIndex, controllerInputHandler));
		}
	}

	@Override
	public void update() {
		for(int i=0; i < controllers.size(); i++) {
			ControllerJamepad controllerJamepad = (ControllerJamepad) controllers.get(i);
			if(controllerJamepad.isConnected()) {
				try{
					controllerJamepad.update();
				}catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
	}

}
