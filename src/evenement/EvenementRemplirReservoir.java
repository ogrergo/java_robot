package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.WorldElement;

public class EvenementRemplirReservoir extends Evenement {

	private Robot robot;
		
	//VERIFIER QUELQUE PART QUE LE ROBOT ET L'INCENDIE SONT SUR LA MEME CASE
	
	public EvenementRemplirReservoir(Date date, Robot r, Simulateur s) {
		super(date, s);
		robot = r;
	}
			
	public void updateDate() {
		//Trouver le temps qu'il met à vider toute son eau
		double tps_remplir = robot.getTempsremplir();
		System.out.println("temps remplissage " + tps_remplir);
		//date.increment((long)tps_deplacement+1);
		robot.setDernierEvent((long)tps_remplir + 1);
		System.out.println("dernier event : " + robot.getDernierEvent().getDate() + " / date : " + date.getDate());
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		System.out.println("Remplissage fin date : " + date.getDate());
		simulateur.addEvenement(new EvenementRemplirReservoirFin(date, 
																robot,
																simulateur));
		s.add(robot);
		return s;
	}
}
