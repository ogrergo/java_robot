package strategie;

public class ActionVidage extends Action {

	int nbInterventionElem;
	
	public ActionVidage(int cout, int nbElem) {
		super(cout);
		this.nbInterventionElem = nbElem;
	}
	
	public int getNbInterventionElem() {
		return nbInterventionElem;
	}

}
