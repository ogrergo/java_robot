package donnees;

public class RobotDrone extends Robot {

	private static final int eau_max = 10000;
	private static final double temps_remplissage = 0.18; //En seconde par litre
	
	public RobotDrone(Case c) {
		super(c);
		this.vitesse_defaut = 100;
	}
	
	@Override
	public int getEauMax() {
		return RobotDrone.eau_max;
	}

	@Override
	public double getEauTempsRemplissage() {
		return RobotDrone.temps_remplissage;
	}

	@Override
	public double getEauTempsVidage() {
		return 30;
	}

	@Override
	public double getVitesseMilieu(NatureTerrain t) {
		return (carte.getTailleCases()/1000) / (this.vitesse_defaut) * 3600;
	}

	@Override
	protected double getEauLitreVidage() {
		return eau_max;
	}

}
