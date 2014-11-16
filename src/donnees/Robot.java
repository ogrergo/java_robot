package donnees;

public abstract class Robot implements WorldElement {
	private Case position;
	private Reservoir reservoir;
	private ComportementDeplacement deplacement;
	
	
	public Case getCase() {
		return position;
	}
	
	private void setPosition(Case c) {
		position = c;
	}
	
	public void move(Direction d, Carte carte) throws InvalidCaseException {
		setPosition(carte.getCase(position, d));
	}
	
	public double getVitesse(NatureTerrain t) {
		deplacement.getVitesse(t);
	}
	
	abstract void setVitesse(double v);
	
	public void intervenir(int volume) {
		reservoir.intervenir(volume);
	}
	
	public void remplirReservoir() {
		reservoir.remplirReservoir();
	}
	
}
