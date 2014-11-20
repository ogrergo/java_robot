package donnees;

import evenement.Date;

/**
 * <b><code>RobotDrone</code> est la classe représentant un robot de type Drone</b>
 * <p>
 * Un robot Drone est caractérisé par les mêmes attributs que les autres robots.
 * Il peut aller sur n'importe quelle case, et sa vitesse ne change pas en fonction de ces cases.
 * Il se déplace à une vitesse par défaut de 100 km/h.
 * Il a un réservoir de 10000 litres qu'il rempli en 30 minutes et déverse tout en une seule fois, en 30 secondes.
 * Il se remplit sur une case contenant de l'eau.
 * </p>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class RobotDrone extends Robot {

	private static final int eau_max = 10000;
	
	public RobotDrone(Case c) {
		super(c);
		this.vitesse_defaut = Date.toMpMin(100);
		this.seRemplitACoteEau = false;
	}
	
	@Override
	public int getEauMax() {
		return RobotDrone.eau_max;
	}
	
	@Override
	public void setVitesse(double v) {
		if(v > 150)
			v = 150;
		
		this.vitesse_defaut = Date.toMpMin(v);
	}

	@Override
	public double getEauTempsVidage() {
		return 1/2.;
	}

	@Override

	public double getTempsDeplacementMilieu(NatureTerrain t, Carte carte) {
		return carte.getTailleCases() / this.vitesse_defaut ;
	}

	@Override
	protected double getEauLitreVidage() {
		return eau_max;
	}

	public boolean canFill(Case c, Carte ca) {
		return c.getNature() == NatureTerrain.EAU;
	}

	@Override
	public boolean seRemplieSurEau() {
		return true;
	}

	@Override
	public double getEauTempsRemplissage() {
		return 30;
	}
}
