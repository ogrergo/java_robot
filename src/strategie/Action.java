package strategie;

public abstract class Action {
	private double cout;
	
	public Action(double d) {
		this.cout = d;
	}
	
	public double getCout() {
		return cout;
	}

}
