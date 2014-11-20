package evenement;

import java.util.Set;

import strategie.Action;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

/**
 * <b><code>EvenementAction</code> est la classe concernant les evenement de type action (déplacement, remplissage et vidage).
 * Cette classe hérite de Evenement</b>
 * <p>
 * Un evenementAction est caractérisé par :
 * <ul>
 * <li>une action</li>
 * <li>un robot</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class EvenementAction extends Evenement {

	/**
	 * Action concernée par cette Evenement
	 */
	private Action action;
	/**
	 * Robot concerné par cette Evenement
	 */
	private Robot robot;
	
	/**
	 * Constructeur d'un EvenementAction
	 * 
	 * @param date
	 * 			date  à laquelle à lieu cet evenement
	 * @param s
	 * 			simulateur associé
	 * @param a
	 * 			action à executer
	 * @param r
	 * 			robot concerné
	 */
	public EvenementAction(Date date, Simulateur s, Action a, Robot r) {
		super(date, s);
		action = a;
		robot = r;
	}

	/**
	 * Execute l'action associée au robot
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
		
		try {
			System.out.println("action");
			robot.doAction(action, simulateur);
			s.add(robot);
		} catch (InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
		return s;
	}

}
