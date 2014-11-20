package donnees;

/**
 * <b><code>WorldElement</code> est une interface repr�sentant un objet ayant une case associ� et ayant un �tat viavant/mort.</b>
 * Cette interface est impl�ment�e par Robot et Incendie
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public interface WorldElement {
	/**
	 * Test si l'objet est dans l'�tat vivant.
	 * @return true si oui.
	 */
	public boolean isAlive();
	
	/**
	 * Renvoie la position de l'objet.
	 * @return la case.
	 */
	Case getCase();
}
