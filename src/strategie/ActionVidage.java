package strategie;

public class ActionVidage extends Action {

	int nbInterventionElem;
	
	public ActionVidage(int cout, int nbElem) {
		super(cout);
		this.nbInterventionElem = nbElem;
		this.typeAction = TypeAction.ACTIONVIDAGE;
	}
	
	public int getNbInterventionElem() {
		return nbInterventionElem;
	}

}