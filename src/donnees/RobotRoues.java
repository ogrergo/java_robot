package donnees;

public class RobotRoues extends Robot {

	private static final int eau_max = 5000;
	private static final double temps_remplissage = 0.12; //En seconde par litre
	private static final double temps_vidage = 0.05;	//En seconde par litre
	private static double[] tab_vitesse_milieu = {0,0,0,80,80}; //En km/h
	
	@Override
	void setVitesse(double v) {
		RobotRoues.tab_vitesse_milieu[3] = v;
		RobotRoues.tab_vitesse_milieu[4] = v;
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
		return RobotRoues.temps_vidage;
	}

	@Override
	public double[] getTabVitesseMilieu() {
		return RobotRoues.tab_vitesse_milieu;
	}

}
