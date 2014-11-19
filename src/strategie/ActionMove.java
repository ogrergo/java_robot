package strategie;

import donnees.Direction;

public class ActionMove extends Action {
	private Direction direction;
	
	public ActionMove(double d, Direction direction) {
		super(d);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

}
