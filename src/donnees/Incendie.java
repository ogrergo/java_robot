package donnees;

/**
 * <b><code>Incendie</code> est la classe représentant un incendie</b>
 * <p>
 * Un incendie est caractérisé par :
 * <ul>
 * <li>sa position</li>
 * <li>le nombre de litres d'eau nécessaires pour l'éteindre</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Incendie implements WorldElement {
	/**
	 * La position sur la carte
	 */
	private Case position;
	/**
	 * Le nombre de litres d'eau nécessaires pour éteindre l'incendie
	 */
	private int litreEau;

	/**
	 * Constructeur d'un incendie
	 * 
	 * @param pos
	 *            La case sur la carte
	 * @param litre
	 *            Le nombre de litres d'eau nécessaires pour éteindre l'incendie
	 */

	public Incendie(Case pos, int litre) {
		this.position = pos;
		this.litreEau = litre;
	}
		
	/**
	 * Retourne le nombre de litres d'eau nécessaires pour éteindre l'incendie
	 * 
	 * @return le nombre de litres d'eau nécessaires pour éteindre l'incendie
	 */
	public int getLitreEau() {
		return litreEau;
	}

	/**
	 * Verse une certaine quantité d'eau sur un feu
	 * 
	 * @param litreEau
	 *            un nombre de litre
	 * @param data
	 *            les données de la simulation
	 *
	 */
	public void eteindre(int litreEau, DonneesSimulation data) {
		this.litreEau -= litreEau;
	
		if (this.litreEau <= 0) {
			this.litreEau = 0;
			//Suppression de l'incendie de la liste des incendies de l'objet data
			data.removeIncendie(this);
		}
	}

	/**
	 * Retourne la case de l'incendie
	 * 
	 * @return la case de l'incendie
	 */
	@Override
	public Case getCase() {
		return position;
	}

	/**
	 * Test si un incendie necessite toujours de l'eau pour l'éteindre
	 * 
	 * @return vrai si l'incendie necessite toujours de l'eau pour l'éteindre, faux sinon 
	 */
	@Override
	public boolean isAlive() {
		return litreEau != 0;
	}
	
}
