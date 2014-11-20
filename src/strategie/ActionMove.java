package strategie;

import donnees.Direction;

/**
 * <b><code>ActionMove</code> est la classe permettant de gérer les actions de type mouvement.
 * Cette classe hérite de la classe Action </b>
 * <p>
 * Une ActionMove est caractérisée par :
 * <ul>
 * <li>une direction</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class ActionMove extends Action {
	/**
	* la direction du mouvement
	*/
	private Direction direction;
	
	/**
	 * Constructeur d'une ActionMove
	 * 
	 * @param d
	 *            le cout de cette Action
	 * @param direction
	 *            la direction du mouvement
	 */
	public ActionMove(double d, Direction direction) {
		super(d);
		this.direction = direction;
		this.typeAction = TypeAction.ACTIONMOVE;
	}
	
	/**
	 * Retourne la direction du mouvement
	 * 
	 * @return la direction du mouvement 
	 */
	public Direction getDirection() {
		return direction;
	}
}