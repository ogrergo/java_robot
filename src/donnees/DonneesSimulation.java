package donnees;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <b><code>DonneesSimulation</code> est la classe permettant de stocker les donn�es r�lative � la simulation</b>
 * <p>
 * La donn�es relatives � la simulation sont :
 * <ul>
 * <li>la carte</li>
 * <li>les incendies</li>
 * <li>les robot </li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public class DonneesSimulation {
	/**
	 * La carte relative � la simulation
	 */
	private Carte carte;
	/**
	 * La liste des incendies
	 */
	private ArrayList<Incendie> incendies = new ArrayList<Incendie>();
	/**
	 * La liste des robots
	 */
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	
	/**
	 * Constructeur de la DonneesSimulation
	 * 
	 * @param carte
	 *            la carte sur laquelle la simulation a lieu
	 */
	public DonneesSimulation(Carte carte) {
		this.carte = carte;
	}
	
	/**
	 * Ajoute un incendie � la liste des incendies
	 * 
	 * @param e
	 *            un incendie
	 */
	public void addIncendie(Incendie e) {
		incendies.add(e);
	}
	
	/**
	 * Supprime un incendie � la liste des incendies
	 * 
	 * @param e
	 *            un incendie
	 *            
	 * @return vrai si la suppression s'est pass� normalement, faux s'il y a eu une erreur
	 */
	public boolean removeIncendie(Incendie e) {
		return incendies.remove(e);
	}
	
	/**
	 * Ajoute un robot � la liste des robots
	 * 
	 * @param r
	 *            un robot
	 */
	public void addRobot(Robot r) {
		robots.add(r);
	}
	
	/**
	 * Retourne la carte de la simulation
	 * 
	 * @return la carte de la simulation
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Retourne la collection des incendies de la simulation
	 * 
	 * @return la collection des incendies de la simulation
	 */
	public Collection<Incendie> getIncendies() {
		return incendies;
	}

	/**
	 * Retourne l'incendie se trouvant sur une case donn�e
	 *
	 *@param c
	 *			une case
	 *
	 * @throws InvalidCaseException : si pas d'incendie sur la case c
	 * @return l'incendie se trouvant sur la case c
	 */
	public Incendie getIncendieAtCase(Case c) throws InvalidCaseException {
		for(Incendie inc : incendies) {
			if(inc.getCase() == c)
				return inc;
		}
		throw new InvalidCaseException("Aucun incendie � la case ("+c.getLigne()+", " + c.getColonne() +").");
	}
	
	/**
	 * Retourne la collection des robots de la simulation
	 * 
	 * @return la collection des robots de la simulation
	 */
	public Collection<Robot> getRobots() {
		return robots;
	}
	
	/**
	 * Retourne le robot d'indice id de la liste des robots stock� dans cette classe
	 * 
	 * @param id
	 * 			l'indice dans la liste robots
	 * 
	 * @return le robot d'indice id de la liste robots
	 */
	public Robot getRobotbyId(int id) {
		return robots.get(id);
	}
	
	/**
	 * Retourne l'incendie d'indice id de la liste des incendies stock� dans cette classe
	 * 
	 * @param id
	 * 			l'indice dans la liste incendies
	 * 
	 * @return l'incendie d'indice id de la liste incendies
	 */
	public Incendie getIncendiebyId(int id) {
		return incendies.get(id);
	}
}
