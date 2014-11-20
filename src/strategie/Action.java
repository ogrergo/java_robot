package strategie;

/**
 * <b><code>Action</code> est la classe abstraite des actions possibles</b>
 * <p>
 * Une action est caractérisée par :
 * <ul>
 * <li>un cout</li>
 * <li>un type d'action (ActionMove, ActionRemplissage, ActionVidage) </li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public abstract class Action {
	
	/**
	 * Le cout de l'action
	 */
	private double cout;
	/**
	 * Le TypeAction de l'action
	 */
	protected TypeAction typeAction;
	
	/**
	 * Constructeur d'une action.
	 * 
	 * @param d
	 *            le cout de cette action
	 */
	public Action(double d) {
		this.cout = d;
	}
	
	/**
	 * Retourne le TypeAction
	 * 
	 *@return le TypeAction
	 */
	public TypeAction getTypeAction() {
		return this.typeAction;
	}
	
	/**
	 * Retourne le cout de l'action
	 * 
	 *@return le cout de l'action
	 */
	public double getCout() {
		return cout;
	}

}