package evenement;

public abstract class Manager {
	protected Simulateur simulateur;
	
	public void setSimulateur(Simulateur s) {
		simulateur = s;
	}
	
	public abstract void manage();
}
