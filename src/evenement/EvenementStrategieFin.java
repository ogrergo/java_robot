package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.State;
import donnees.WorldElement;

public class EvenementStrategieFin extends Evenement {

	private Robot robot;
	
	public EvenementStrategieFin(Date date, Simulateur s, Robot r) {
		super(date, s);
		robot = r;
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s)
			throws ExecutionException {

		System.out.println("fin strat");
		robot.setStrat(null);
		return s;
	}

}
