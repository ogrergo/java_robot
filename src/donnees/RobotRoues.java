package donnees;

import evenement.Date;

public class RobotRoues extends Robot {

	private static final int eau_max = 5000;
		
	public RobotRoues(Case c) {
		super(c);
		this.vitesse_defaut = Date.toMpMin(80);
		this.seRemplitACoteEau = true;
	}

	@Override
	public int getEauMax() {
		return RobotRoues.eau_max;
	}

	@Override
	public double getEauTempsVidage() {
		return 5/60.;
	}

	public double getTempsDeplacementMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case TERRAIN_LIBRE:
		case HABITAT:
			return  ((double)carte.getTailleCases()) / this.vitesse_defaut;
		default:
			return Double.MAX_VALUE;
		}
	}

	@Override
	protected double getEauLitreVidage() {
		return 100;
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
		return 10;
	}
}
