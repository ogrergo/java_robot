package evenement;

/**
 * <b><code>Manager</code> est la classe abstraite permettant de déterminer les evenements à  effectuer</b>
 * <p>
 * Un Manager est caractérisé par :
 * <ul>
 * <li>un simulateur</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public abstract class Manager {
	/**
	 * Simulateur associé au manager
	 */
	protected Simulateur simulateur;
	
	/**
	 * Permet de modifier le simulateur associé au manager
	 * 
	 * @param s
	 * 			le simulateur
	 */
	public void setSimulateur(Simulateur s) {
		simulateur = s;
	}
	
	/**
	 * Détermine tous les evenement à  executer
	 */
	public abstract void manage();
}
