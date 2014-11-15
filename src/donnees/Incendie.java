package donnees;

public class Incendie implements WorldElement {
	private Case position;
	private int litreEau;
	
	public Incendie(Case pos, int litre) {
		this.position = pos;
		this.litreEau = litre;
	}
		
	public int getLitreEau() {
		return litreEau;
	}

	@Override
	public Case getCase() {
		return position;
	}
}
