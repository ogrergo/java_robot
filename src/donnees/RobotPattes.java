package donnees;

import evenement.Date;

/**
 * <b><code>RobotPattes</code> est la classe représentant un robot de type Pattes</b>
 * <p>
 * Un robot Pattes est caractérisé par les mêmes attributs que les autres robots.
 * Il ne peut pas aller sur l'eau.
 * Il se déplace à une vitesse par défaut de 30 km/h, qui est réduite à 10 km/h sur du rocher. 
 * Il n'utilise pas d'eau mais de la poudre, qui est aussi efficace que si il versait 10L d'eau par secondes.
 * </p>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class RobotPattes extends Robot {

	private static final int eau_max = Integer.MAX_VALUE;
	
	public RobotPattes(Case c) {
		super(c);
		this.vitesse_defaut = Date.toMpMin(30);
	}

	@Override
	public int getEauMax() {
		return RobotPattes.eau_max;
	}

	@Override
	public void setVitesse(double v) {
		return;
	}
	
	@Override
	public double getEauTempsVidage() {
		return 1.0/60.;
	}

	public double getTempsDeplacementMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case EAU:
			return Double.MAX_VALUE;
		case ROCHE:
			return  ((double)carte.getTailleCases() * 3) / this.vitesse_defaut;
		default:
			return  ((double)carte.getTailleCases()) / this.vitesse_defaut;
		}
	}

	@Override
	protected double getEauLitreVidage() {
		return 10;
	}
	
	public boolean canFill(Case c, Carte ca) {
		return ca.isPlage(c);
	}

	@Override
	public boolean seRemplieSurEau() {
		return false;
	}

	@Override
	public double getEauTempsRemplissage() {
		return 0;
	}

}
