package strategie;

public abstract class Action {
	private double cout;
	protected TypeAction typeAction;
	
	public Action(double d) {
		this.cout = d;
	}
	
	public TypeAction getTypeAction() {
		return this.typeAction;
	}
	
	public double getCout() {
		return cout;
	}

}