package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.WorldElement;

public class EvenementRemplirReservoirFin extends Evenement {
	private Robot robot;

	public EvenementRemplirReservoirFin(Date date, Robot r, Simulateur s) {
		super(date, s);
		robot = r;
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) {
		System.out.println("Remplir");
		robot.remplirReservoir();
		s.add(robot);
		return s;
	}
}
