package strategie;

/**
 * <b><code>ActionRemplissage</code> est la classe permettant de g�rer les actions de type remplissage du r�servoir.
 * Cette classe h�rite de la classe Action </b>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public class ActionRemplissage extends Action {

	/**
	 * Constructeur d'une ActionRemplissage
	 * 
	 * @param cout
	 *            le cout de cette Action
	 */

	public ActionRemplissage(double cout) {
		super(cout);
		this.typeAction = TypeAction.ACTIONREMPLISSAGE;
	}

}