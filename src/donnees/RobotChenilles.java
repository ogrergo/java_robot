package donnees;

public class RobotChenilles extends Robot {

	private static final int eau_max = 2000;
	private static final double temps_remplissage = 0.15; //En seconde par litre
	private static final double temps_vidage = 0.08;	//En seconde par litre
	private static double[] tab_vitesse_milieu = {0,30,0,60,60};
	
	@Override
	void setVitesse(double v) {
		RobotChenilles.tab_vitesse_milieu[3] = v;
		RobotChenilles.tab_vitesse_milieu[4] = v;
		RobotChenilles.tab_vitesse_milieu[1] = v/2;
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
		return RobotChenilles.temps_vidage;
	}

	@Override
	public double[] getTabVitesseMilieu() {
		return RobotChenilles.tab_vitesse_milieu;
	}

}
