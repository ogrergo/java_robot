package evenement;

/**
 * <b><code>Manager</code> est la classe abstraite permettant de d�terminer les evenements � effectuer</b>
 * <p>
 * Un Manager est caract�ris� par :
 * <ul>
 * <li>un simulateur</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public abstract class Manager {
	/**
	 * Simulateur associ� au manager
	 */
	protected Simulateur simulateur;
	
	/**
	 * Permet de modifier le simulateur associ� au manager
	 * 
	 * @param s
	 * 			le simulateur
	 */
	public void setSimulateur(Simulateur s) {
		simulateur = s;
	}
	
	/**
	 * D�termine tous les evenement � executer
	 */
	public abstract void manage();
}
