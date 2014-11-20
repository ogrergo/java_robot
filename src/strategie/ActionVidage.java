package strategie;

/**
 * <b><code>ActionVidage</code> est la classe permettant de gérer les actions de type vidage d'eau sur un incendie.
 * Cette classe hérite de la classe Action </b>
 * <p>
 * Une ActionVidage est caractérisée par :
 * <ul>
 * <li>un nombre d'intervention unitaire (en litre d'eau)</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class ActionVidage extends Action {

	/**
	 * Nombre d'intervention unitaire
	 */
	int nbInterventionElem;
	
	/**
	 * Constructeur d'une ActionVidage
	 * 
	 * @param d
	 *            le cout de cette Action
	 * @param nbElem
	 *            nombre d'intervention unitaire
	 */
	public ActionVidage(double d, int nbElem) {
		super(d);
		this.nbInterventionElem = nbElem;
		this.typeAction = TypeAction.ACTIONVIDAGE;
	}
	
	/**
	 * Retourne le nombre de litre d'une intervention unitaire
	 * 
	 * @return le nombre de litre d'une intervention unitaire 
	 */
	public int getNbInterventionElem() {
		return nbInterventionElem;
	}

}