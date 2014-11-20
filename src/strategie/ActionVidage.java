package strategie;

public class ActionVidage extends Action {

	int nbInterventionElem;
	
	public ActionVidage(double d, int nbElem) {
		super(d);
		this.nbInterventionElem = nbElem;
		this.typeAction = TypeAction.ACTIONVIDAGE;
	}
	
	public int getNbInterventionElem() {
		return nbInterventionElem;
	}

}