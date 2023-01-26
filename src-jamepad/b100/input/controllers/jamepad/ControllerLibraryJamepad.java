package b100.input.controllers.jamepad;

import java.util.ArrayList;
import java.util.List;

import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;

import b100.input.controllers.Controller;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;

public class ControllerLibraryJamepad extends ControllerLibrary {
	
	private final int controllerCount;
	
	private ControllerManager controllerManager;
	
	private boolean initialized = false;
	
	private List<Controller> disconnectedControllers = new ArrayList<>();
	
	public ControllerLibraryJamepad(int controllerCount) {
		this.controllerCount = controllerCount;
	}
	
	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		this.controllerManager = new ControllerManager(controllerCount);
		this.controllerManager.initSDLGamepad();
		
		for(int i=0; i < controllerCount; i++) {
			ControllerIndex controllerIndex = controllerManager.getControllerIndex(i);
			
			Controller controller = new ControllerJamepad(this, controllerIndex, controllerInputHandler); 
			
			if(controllerIndex.isConnected()) {
				controllers.add(controller);
			}else {
				disconnectedControllers.add(controller);
			}
		}
		
		initialized = true;
	}

	@Override
	public void update() {
		controllerManager.update();
		
		for(int i=0; i < disconnectedControllers.size(); i++) {
			Controller controller = disconnectedControllers.get(i);
			if(controller.isConnected()) {
				disconnectedControllers.remove(i--);
				controllers.add(controller);
				
				onControllerConnected(controller);
			}
		}
		
		for(int i=0; i < controllers.size(); i++) {
			ControllerJamepad controllerJamepad = (ControllerJamepad) controllers.get(i);
			if(controllerJamepad.isConnected()) {
				try{
					controllerJamepad.update();
				}catch (ControllerDisconnectException e) {
					controllers.remove(i--);
					disconnectedControllers.add(controllerJamepad);
					onControllerDisconnected(controllerJamepad);
				}
			}else {
				controllers.remove(i--);
				disconnectedControllers.add(controllerJamepad);
				onControllerDisconnected(controllerJamepad);
			}
		}
	}

	@Override
	public String getName() {
		return "Jamepad";
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

}
