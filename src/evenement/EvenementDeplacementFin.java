package evenement;

import java.util.Set;

import donnees.Direction;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementDeplacementFin extends Evenement {
	private Robot robot;
	private Direction direction;
	
		
	public Robot getRobot() {
		return this.robot;
	}

	public Direction getDirection() {
		return direction;
	}

	public EvenementDeplacementFin(Date date, Robot r, Direction d, Simulateur s) {
		super(date, s);
		robot = r;
		direction = d;
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		try {
			System.out.println("deplacement fin");
			robot.move(direction, data.getCarte());
			s.add(robot);
		} catch (InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
		return s;
	}

	@Override
	public void updateDate() throws ExecutionException {
		// Rien a faire ici
		
	}
}
