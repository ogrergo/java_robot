package donnees;

public abstract class Reservoir {
	private int capacity_max;
	private int remplissage;
	private int time_per_liter_out;
	private int time_per_liter_in;
	
	private Date debut_remplissage;
	
	abstract void intervenir(int volume);
	abstract int getDebit();

	public void remplirReservoir() {
		
	}

}
