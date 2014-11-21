package donnees;

import evenement.Date;

/**
 * <b><code>RobotChenilles</code> est la classe représentant un robot de type Chenille</b>
 * <p>
 * Un robot Chenille est caractérisé par les màªmes attributs que les autres robots.
 * Il ne peut cependant pas aller dans l'eau ni sur des rochers
 * Il se déplace a une vitese par défault de 60 km/h, qui est divisée par 2 en foràªt.
 * Il a un réservoir de 2000 litres qu'il remplit en 5 minutes.
 * Il se remplit à  cà´té dÂ’une case contenant de lÂ’eau, et déverse à  un débit de 100 litres par 8 secondes.
 * </p>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class RobotChenilles extends Robot {

	private static final int eau_max = 2000;
	

	public RobotChenilles(Case c) {
		super(c);
		this.vitesse_defaut = Date.toMpMin(60);
		this.seRemplitACoteEau = true;
	}

	@Override
	public int getEauMax() {
		return RobotChenilles.eau_max;
	}
	
	@Override
	public double getEauTempsVidage() {
		return 8/60.;
	}
	
	public void setVitesse(double v) {
		if(v > 80)
			v = 80;
		
		this.vitesse_defaut = Date.toMpMin(v);
	}

	@Override
	protected double getEauLitreVidage() {
		return 100;
	}

	public double getTempsDeplacementMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case EAU:
		case ROCHE:
			return Double.MAX_VALUE;
		case FORET:
			return ((double)carte.getTailleCases() * 2) / this.vitesse_defaut;
		default:
			return (double)carte.getTailleCases() / this.vitesse_defaut;
		}
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
		return 5;
	}
}
