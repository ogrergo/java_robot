package evenement;

import java.util.Set;

import donnees.Direction;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementDeplacement extends Evenement {
	private Robot robot;
	private Direction direction;
	
		
	public Robot getRobot() {
		return this.robot;
	}

	public Direction getDirection() {
		return direction;
	}

	public EvenementDeplacement(Date date, Robot r, Direction d, Simulateur s) {
		super(date, s);
		robot = r;
		direction = d;
		simulateur = s;
	}

	public void updateDate() throws ExecutionException {
		try {
			double tps_deplacement = robot.getTempsDeplacement(direction, data.getCarte());
			System.out.println("deplacement " + tps_deplacement + "direction" + direction);
			//date.increment((long)tps_deplacement+1);
			robot.setDernierEvent((long)tps_deplacement + 1);
			System.out.println("dernier event : " + robot.getDernierEvent().getDate() + " / date : " + date.getDate());
		}catch(InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		System.out.println("Deplacement fin date : " + date.getDate());
		simulateur.addEvenement(new EvenementDeplacementFin(date, 
															robot,
															direction,
															simulateur));
		s.add(robot);
		return s;
	}
}
