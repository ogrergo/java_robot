package donnees;

public class RobotPattes extends Robot {

	private static final int eau_max = Integer.MAX_VALUE;
	private static final double temps_remplissage = 0; //En seconde
	
	public RobotPattes(Case c) {
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
		return 1;
	}

	public double getVitesseMilieu(NatureTerrain t, Carte carte) {
		switch(t) {
		case EAU:
			return 0;
		case ROCHE:
			return (carte.getTailleCases()/1000) / (this.vitesse_defaut / (3*3600));
		default:
			return (carte.getTailleCases()/1000) / (this.vitesse_defaut/ 3600);
		}
	}

	@Override
	protected double getEauLitreVidage() {
		return 10;
	}
	
	public boolean canFill(Case c, Carte ca) {
		return ca.caseVoisineEau(c);
	}

}
