package donnees;

public class RobotPattes extends Robot {

	private static final int eau_max = -1;
	private static final double temps_remplissage = 0; //En seconde
	private static final double temps_vidage = 0.1;	//En seconde par litre
	
	public RobotPattes(Carte c) {
		super(c);
		this.vitesse_defaut = 30;
	}

	@Override
	public int getEauMax() {
		return RobotPattes.eau_max;
	}

	//GENERER UNE ERREUR ICI CAR ON NE PEUT PAS APPELER CETTE FONCTION SUR CE TYPE DE ROBOT
	@Override
	public double getEauTempsRemplissage() {
		return RobotPattes.temps_remplissage;
	}

	@Override
	public double getEauTempsVidage() {
		return RobotPattes.temps_vidage;
	}

	public double getVitesseMilieu(NatureTerrain t) {
		switch(t) {
		case EAU:
			return 0;
		case ROCHE:
			return (carte.getTailleCases()/1000) / 10 * 3600;
		default:
			return (carte.getTailleCases()/1000) / this.vitesse_defaut * 3600;
		}
	}
}
