package b100.input.controllers;

public abstract class Button {
	
	private final int id;
	
	private boolean pressed;
	private boolean updated;
	
	public Button(int id) {
		this.id = id;
	}
	
	public final void update() throws ControllerDisconnectException {
		boolean state = pollState();
		
		updated = pressed != state;
		
		this.pressed = state;
	}
	
	protected abstract boolean pollState() throws ControllerDisconnectException;
	
	public int getId() {
		return id;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isUpdated() {
		return updated;
	}
	
}
