package donnees;

import evenement.Date;

public abstract class Robot implements WorldElement {
	private Case position;
	
	private int eau_dispo;
	private Date dernierEvent = new Date(0);
	
	public Date getDernierEvent() {
		return dernierEvent;
	}

	public void setDernierEvent(long dernier) {
		this.dernierEvent.increment(dernier);
	}

	public Robot() {
		this.eau_dispo = this.getEauMax();
	}
	
	public abstract int getEauMax();
	
	public abstract double getEauTempsRemplissage();
	
	public abstract double getEauTempsVidage();
	
	public abstract double[] getTabVitesseMilieu();
	
	public Case getCase() {
		return position;
	}
	
	public void setPosition(Case c) {
		position = c;
	}
	
	public void move(Direction d, Carte carte) throws InvalidCaseException {
		if (this.getTabVitesseMilieu()[carte.getCase(position, d).getNature().ordinal()] != 0)
			setPosition(carte.getCase(position, d));
		else
			throw new InvalidCaseException("Ce robot ne peut pas se rendre sur cette surface");
	}
	
	public double getVitesse(NatureTerrain t) {
		return this.getTabVitesseMilieu()[t.ordinal()];
	}
	
	public int getEauDispo() {
		return this.eau_dispo;
	}
	
	abstract void setVitesse(double v);
	
	//A VERIFIER AVEC SIMULATION 1
	public void deverserEau(int volume) {
		if (this.eau_dispo != -1) { //Si le robot n'est pas un robot à pattes
			if (this.eau_dispo >= volume)
				this.eau_dispo -= volume;
			else
				this.eau_dispo = 0;
		}
	}
	
	public void remplirReservoir() {
		this.eau_dispo = this.getEauMax();
	}
	
	public double getTempsDeplacement(Direction d, Carte c) throws InvalidCaseException {
		try {
				System.out.println("dernier event : " + this.dernierEvent.getDate());
				return ((c.getTailleCases()/1000) / this.getVitesse(c.getCase(position, d).getNature())) * 3600;
		} catch(InvalidCaseException e) {
			throw new InvalidCaseException("Ce robot ne peut pas se rendre sur cette surface");
		}
	}
	
	public double getTempsVider(int tailleIncendie) {
		if (tailleIncendie > this.getEauDispo())
			return this.getEauDispo() * this.getEauTempsVidage();
		else
			return tailleIncendie * this.getEauTempsVidage();
	}
	
	public double getTempsremplir() {
		return this.getEauTempsRemplissage() * this.getEauMax();
	}
}
