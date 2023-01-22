package b100.input.controllers;

public abstract class Joystick {
	
	private final int id;
	
	public Button button;
	
	private float xValue;
	private float yValue;
	
	private boolean movementChanged = false;
	
	private float deadZone = 0.25f;
	private float deadZoneValue;
	
	public Joystick(int id, Button button) {
		this.id = id;
		this.button = button;
	}
	
	public void update() throws ControllerDisconnectException {
		float x = pollX();
		float y = pollY();
		
		this.deadZoneValue = this.deadZone * 0.5f;
		
		if(x < deadZoneValue && x > -deadZoneValue) x = 0.0f;
		if(y < deadZoneValue && y > -deadZoneValue) y = 0.0f;
		
		x = modify(x);
		y = modify(y);
		
		boolean update = false;
		
		if(x != this.xValue) update = true;
		if(y != this.yValue) update = true;
		
		movementChanged = update;
		
		this.xValue = x;
		this.yValue = y;
	}
	
	public float modify(float val) {
		if(val == 0.0f) return 0.0f;

		if(val > 0.0f) val -= deadZoneValue;
		if(val < 0.0f) val += deadZoneValue;
		
		val /= (1.0f - deadZoneValue);
		
		return val;
	}

	protected abstract float pollX() throws ControllerDisconnectException;
	
	protected abstract float pollY() throws ControllerDisconnectException;
	
	public int getId() {
		return id;
	}
	
	public float getX() {
		return xValue;
	}
	
	public float getY() {
		return yValue;
	}
	
	public boolean isMovementChanged() {
		return movementChanged;
	}
	
}
