package donnees;

import evenement.Date;

/**
 * <b><code>RobotRoues</code> est la classe représentant un robot de type Roues</b>
 * <p>
 * Un robot Roues est caractérisé par les mêmes attributs que les autres robots.
 * Il ne peut aller que sur des terrains libres ou sur des habitations.
 * Il se déplace à une vitesse par défaut de 80 km/h.
 * Il a un réservoir de 5000 litres qu'il remplit en 10 minutes.
 * Il se remplit à côté d'une case contenant de l'eau, et déverse à un débit de 100 litres par 5 secondes.
 * </p>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public class RobotRoues extends Robot {

	/**
	 * Capacite du reservoir d'un RobotRoues
	 */
	private static final int eau_max = 5000;
		
	/**
	 * Constructeur d'un RobotRoues
	 * 
	 * @param c
	 * 			la case initiale du robot
	 */
	public RobotRoues(Case c) {
		super(c);
		this.vitesse_defaut = Date.toMpMin(80);
		this.seRemplitACoteEau = true;
	}

	/**
	 * Retourne la capacité du reservoir
	 */
	@Override
	public int getEauMax() {
		return RobotRoues.eau_max;
	}

	/**
	 * Retourne le temps que prend le vidage d'une unité de vidage (voir getEauLitreVidage)
	 * 
	 * @return le temps que prend le vidage d'une unité de vidage (voir getEauLitreVidage)
	 */
	@Override
	public double getEauTempsVidage() {
		return 5/60.;
	}

	/**
	 * Retourne le temps de déplacement en fonction de la nature du terrain
	 * 
	 * @param t
	 * 			La nature du terrain
	 * @param carte
	 * 			La carte
	 */
	public double getTempsDeplacementMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case TERRAIN_LIBRE:
		case HABITAT:
			return  ((double)carte.getTailleCases()) / this.vitesse_defaut;
		default:
			return Double.MAX_VALUE;
		}
	}

	/**
	 * Retourne la capacité d'une unité de vidage
	 * 
	 * @return la capacité d'une unité de vidage
	 */
	@Override
	protected double getEauLitreVidage() {
		return 100;
	}

	/**
	 * Test si le robot peut se remplir depuis une case donnée
	 * 
	 * @param c
	 * 			une case
	 * @param ca
	 * 			la carte
	 * 
	 * @return vrai si le robot peut se remplir depuis c
	 */
	public boolean canFill(Case c, Carte ca) {
		return ca.isPlage(c);
	}

	/**
	 * Retourne vrai si le robot se remplie depuis une case d'eau
	 * 
	 *@return vrai si le robot se remplie depuis une case d'eau
	 */
	@Override
	public boolean seRemplieSurEau() {
		return false;
	}

	/**
	 * Retourne le temps de Remplissage
	 * 
	 * @return le temps de Remplissage
	 */
	@Override
	public double getEauTempsRemplissage() {
		return 10;
	}
}