package evenement;

import java.util.Set;

import donnees.Incendie;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementEteindreFeuFin extends Evenement {
	private Robot robot;
	private Incendie incendie;	
		
	public EvenementEteindreFeuFin(Date date, Robot r, Incendie i, Simulateur s) {
		super(date, s);
		robot = r;
		incendie = i;
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) {
		System.out.println("Debut Eteindre feu necessitant " + incendie.getLitreEau() + "lit eau robot initial" + robot.getEauDispo());
		int eau_debut = robot.getEauDispo();
		robot.deverserEau(incendie.getLitreEau());
		incendie.setLitreEau(incendie.getLitreEau() - (eau_debut - robot.getEauDispo()));
		System.out.println("Fin Eau necessaire pour eteindre" + incendie.getLitreEau());
		if (incendie.getLitreEau() == 0)
			if (data.removeIncendie(incendie))
				System.out.println("Incendie virer de la liste");
			else
				System.out.println("FUCK");
		s.add(robot);
		return s;
	}

	@Override
	public void updateDate() throws ExecutionException {
		// Rien a faire ici
		
	}
}
