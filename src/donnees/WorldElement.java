package donnees;

/**
 * <b><code>WorldElement</code> est une interface représentant un objet ayant une case associé et ayant un état viavant/mort.</b>
 * Cette interface est implémentée par Robot et Incendie
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public interface WorldElement {
	/**
	 * Test si l'objet est dans l'état vivant.
	 * @return true si oui.
	 */
	public boolean isAlive();
	
	/**
	 * Renvoie la position de l'objet.
	 * @return la case.
	 */
	Case getCase();
}
