package evenement;

import java.util.Set;

import strategie.Action;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

/**
 * <b><code>EvenementAction</code> est la classe concernant les evenement de type action (d�placement, remplissage et vidage).
 * Cette classe h�rite de Evenement</b>
 * <p>
 * Un evenementAction est caract�ris� par :
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
	 * Action concern�e par cette Evenement
	 */
	private Action action;
	/**
	 * Robot concern� par cette Evenement
	 */
	private Robot robot;
	
	/**
	 * Constructeur d'un EvenementAction
	 * 
	 * @param date
	 * 			date  � laquelle � lieu cet evenement
	 * @param s
	 * 			simulateur associ�
	 * @param a
	 * 			action � executer
	 * @param r
	 * 			robot concern�
	 */
	public EvenementAction(Date date, Simulateur s, Action a, Robot r) {
		super(date, s);
		action = a;
		robot = r;
	}
	
	/**
	 * Execute l'action associ�e au robot
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
		
		try {
			robot.doAction(action, simulateur);
			s.add(robot);
		} catch (InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
		return s;
	}

}
