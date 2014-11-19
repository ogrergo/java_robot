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
	
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException {
		System.out.println("Remplissage fin date : " + getDate().getDate());
		s.add(robot);
		return s;
	}
}
