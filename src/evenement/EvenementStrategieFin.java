package evenement;

import java.util.Set;

import donnees.Robot;
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
		robot.setStrat(null);
		simulateur.manage();
		return s;
	}

}
