package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.State;
import donnees.WorldElement;

public class EvenementStrategieDebut extends Evenement {

	private Robot robot;
	
	public EvenementStrategieDebut(Date date, Simulateur s, Robot r) {
		super(date, s);
		robot = r;
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s)
			throws ExecutionException {
		System.out.println("Debut strat");
		robot.setState(State.BUSY);
		return s;
	}

}
