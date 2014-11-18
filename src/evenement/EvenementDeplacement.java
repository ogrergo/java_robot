package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.WorldElement;

public class EvenementDeplacement extends Evenement {
	private Robot robot;
	
		
	public Robot getRobot() {
		return this.robot;
	}

	public EvenementDeplacement(Date date, Robot r, Simulateur s) {
		super(date, s);
		robot = r;
		simulateur = s;
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		System.out.println("Deplacement fin date : " + this.getDate().getDate());
		
		s.add(robot);
		return s;
	}
}
