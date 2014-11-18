package evenement;

import java.util.Set;

import donnees.Incendie;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementEteindreFeuFin extends Evenement {
	private Robot robot;
	private Incendie incendie;	
	private int eauDeverse;
		
	public EvenementEteindreFeuFin(Date date, Robot r, Incendie i, int eauDeverse, Simulateur s) {
		super(date, s);
		robot = r;
		incendie = i;
		this.eauDeverse = eauDeverse;
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) {
		System.out.println("Debut Eteindre feu necessitant " + incendie.getLitreEau() + "lit eau robot initial" + robot.getEauDispo());
		robot.deverserEau(incendie, eauDeverse);
		s.add(robot);
		return s;
	}
}
