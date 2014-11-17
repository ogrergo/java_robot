package donnees;

public class RobotDrone extends Robot {

	private static final int eau_max = 10000;
	private static final double temps_remplissage = 0.18; //En seconde par litre
	private static final double temps_vidage = 0.003;	//En seconde par litre
	private static double[] tab_vitesse_milieu = {100,100,100,100,100};
	
	@Override
	void setVitesse(double v) {
		for (int i=0; i < 5; i++) {
			RobotDrone.tab_vitesse_milieu[i] = v;
		}
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
		return RobotDrone.temps_vidage;
	}

	@Override
	public double[] getTabVitesseMilieu() {
		return RobotDrone.tab_vitesse_milieu;
	}

}
