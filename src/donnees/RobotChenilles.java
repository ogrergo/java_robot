package donnees;

public class RobotChenilles extends Robot {

	private static final int eau_max = 2000;
	private static final double temps_remplissage = 0.15; //En seconde par litre
	
	public RobotChenilles(Case c) {
		super(c);
		this.vitesse_defaut = 60;
	}

	@Override
	public int getEauMax() {
		return RobotChenilles.eau_max;
	}

	@Override
	public double getEauTempsRemplissage() {
		return RobotChenilles.temps_remplissage;
	}

	@Override
	public double getEauTempsVidage() {
		return 8;
	}

	@Override
	protected double getEauLitreVidage() {
		return 100;
	}

	public double getVitesseMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case EAU:
		case ROCHE:
			return 0;
		case FORET:
			return (carte.getTailleCases()/1000) / (this.vitesse_defaut/2) * 3600;
		default:
			return (carte.getTailleCases()/1000) / this.vitesse_defaut * 3600;
		}
	}

}
