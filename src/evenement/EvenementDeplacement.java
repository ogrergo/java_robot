package evenement;

import java.util.Set;

import donnees.Direction;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementDeplacement extends Evenement {
	private Robot robot;
	private Direction direction;
	
	public EvenementDeplacement(Date date, Robot r, Direction d) {
		super(date);
		robot = r;
		direction = d;
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		try {
			robot.move(direction, data.getCarte());
			s.add(robot);
		} catch (InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
		return s;
	}
}
