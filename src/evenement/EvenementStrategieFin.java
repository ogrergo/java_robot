package evenement;

import java.util.Set;

import donnees.Robot;
import donnees.WorldElement;

/**
 * <b><code>EvenementStrategieFin</code> est la classe permettant de terminer une strategie.
 * Cette classe hérite de Evenement</b>
 * <p>
 * Un evenementStrategieFin est caractérisé par :
 * <ul>
 * <li>un robot</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class EvenementStrategieFin extends Evenement {

	/**
	 * Robot concerné par cette Evenement
	 */
	private Robot robot;
	
	/**
	 * Constructeur d'un EvenementStrategieFin
	 * 
	 * @param date
	 * 			date  à laquelle à lieu cet evenement
	 * @param s
	 * 			simulateur associé
	 * @param r
	 * 			robot concerné
	 */
	public EvenementStrategieFin(Date date, Simulateur s, Robot r) {
		super(date, s);
		robot = r;
	}

	/**
	 * Met à null la strategie associé au robot
	 * 
	 * @param s
	 * 			un ensemble de WordlElement 
	 * 
	 * @return un ensemble de WorldElement
	 * @throws ExecutionException : si l'exception InvalidCaseException est levée
	 */
	@Override
	public Set<WorldElement> execute(Set<WorldElement> s)
			throws ExecutionException {
		robot.setStrat(null);
		simulateur.manage();
		return s;
	}

}
