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

	public void eteindre(int litreEau, DonneesSimulation data) {
		this.litreEau -= litreEau;
	
		if (this.litreEau <= 0) {
			this.litreEau = 0;
			data.removeIncendie(this);
		}
	}

	@Override
	public Case getCase() {
		return position;
	}

	@Override
	public boolean isAlive() {
		return litreEau != 0;
	}
	
}
