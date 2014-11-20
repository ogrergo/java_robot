package donnees;

import evenement.Date;

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
