package donnees;

public abstract class Robot implements WorldElement {
	private Case position;
	
	public Case getCase() {
		return position;
	}
	
	public void setPosition(Case c) {
		position = c;
	}
	
	public void move(Direction d, Carte carte) throws InvalidCaseException {
		setPosition(carte.getCase(position, d));
	}
	
	abstract double getVitesse(NatureTerrain t);
	abstract void setVitesse(double v);
	
	abstract void deverserEau(int volume);
	abstract void remplirReservoir();
	
}
