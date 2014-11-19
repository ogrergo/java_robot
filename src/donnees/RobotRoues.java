package donnees;

public class RobotRoues extends Robot {

	private static final int eau_max = 5000;
	private static final double temps_remplissage = 0.12; //En seconde par litre
		
	public RobotRoues(Case c) {
		super(c);
		this.vitesse_defaut = 80;
	}

	@Override
	public int getEauMax() {
		return RobotRoues.eau_max;
	}

	@Override
	public double getEauTempsRemplissage() {
		return RobotRoues.temps_remplissage;
	}

	@Override
	public double getEauTempsVidage() {
		return 5;
	}

	public double getVitesseMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case TERRAIN_LIBRE:
		case HABITAT:
			return (carte.getTailleCases()/1000) / (this.vitesse_defaut / 3600);
		default:
			return 0;
		}
	}

	@Override
	protected double getEauLitreVidage() {
		return 100;
	}

	public boolean CanFile(Case c) {
		return false;
	}
}
