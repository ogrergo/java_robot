package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.WorldElement;

/**
 * <b><code>EvenementStrategieFin</code> est la classe permettant de terminer une strategie.
 * Cette classe h�rite de Evenement</b>
 * <p>
 * Un evenementStrategieFin est caract�ris� par :
 * <ul>
 * <li>un robot</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class EvenementStrategieFin extends Evenement {

	/**
	 * Robot concern� par cette Evenement
	 */
	private Robot robot;
	
	/**
	 * Constructeur d'un EvenementStrategieFin
	 * 
	 * @param date
	 * 			date  � laquelle � lieu cet evenement
	 * @param s
	 * 			simulateur associ�
	 * @param r
	 * 			robot concern�
	 */
	public EvenementStrategieFin(Date date, Simulateur s, Robot r) {
		super(date, s);
		robot = r;
	}

	/**
	 * Met � null la strategie associ� au robot
	 * 
	 * @param s
	 * 			un ensemble de WordlElement 
	 * 
	 * @return un ensemble de WorldElement
	 * @throws ExecutionException : si l'exception InvalidCaseException est lev�e
	 */
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s)
			throws ExecutionException {
		robot.setStrat(null);
		simulateur.manage();
		return s;
	}

}
