package evenement;

import java.util.Set;

import donnees.Incendie;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementEteindreFeu extends Evenement{

	private Robot robot;
	private Incendie incendie;
	
	//VERIFIER QUELQUE PART QUE LE ROBOT ET L'INCENDIE SONT SUR LA MEME CASE
	
	public EvenementEteindreFeu(Date date, Robot r, Incendie i, Simulateur s) {
		super(date, s);
		robot = r;
		incendie = i;
	}

	public void updateDate() {
		//Trouver le temps qu'il met à vider toute son eau
		double tps_vider = robot.getTempsVider(incendie.getLitreEau());
		System.out.println("temps vidage " + tps_vider);
		//date.increment((long)tps_deplacement+1);
		robot.setDernierEvent((long)tps_vider + 1);
		System.out.println("dernier event : " + robot.getDernierEvent().getDate() + " / date : " + date.getDate());
	}
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		System.out.println("Vidage fin date : " + date.getDate());
		simulateur.addEvenement(new EvenementEteindreFeuFin(date, 
															robot,
															incendie,
															simulateur));
		s.add(robot);
		return s;
	}
/*	
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
	}*/

}
