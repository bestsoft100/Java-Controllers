package b100.input.controllers.jinput;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;
import net.java.games.input.Component;

public class ButtonJInput extends Button {
	
	private Component component;
	private ComponentListener componentListener;
	
	public ButtonJInput(int id, Component component, ComponentListener componentListener) {
		super(id);
		
		this.component = component;
		this.componentListener = componentListener;
	}

	@Override
	protected boolean pollState() throws ControllerDisconnectException {
		return componentListener.isPressed(component);
	}

}
