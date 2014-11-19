package strategie;

import java.util.List;

import donnees.Carte;
import donnees.Case;
import donnees.Direction;
import donnees.InvalidCaseException;

public class ActionMove extends Action {
	private Direction direction;
	
	public ActionMove(double d, Direction direction) {
		super(d);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public static Case getLastCase(List<ActionMove> a, Case dep, Carte c) throws InvalidCaseException {
		for(int i = 0; i < a.size(); i++) {
			dep = c.getCase(dep,a.get(i).direction);
		}
		return dep;
	}
}
