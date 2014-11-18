package donnees;

import evenement.Date;

public abstract class Robot implements WorldElement {
	protected Case position;
	protected static Carte carte;
	protected double vitesse_defaut;
	private int eau_dispo;
	private Date dernierEvent = new Date(0);
	
	public Robot(Carte c) {
		super();
		this.eau_dispo = this.getEauMax();
		Robot.carte = c;
	}
	
	void setVitesse(double v) {
		this.vitesse_defaut = v;
	}
	
	public Date getDernierEvent() {
		return dernierEvent;
	}

	public void setDernierEvent(long dernier) {
		this.dernierEvent.increment(dernier);
	}
	
	public abstract int getEauMax();
	
	public abstract double getEauTempsRemplissage();
	
	public abstract double getEauTempsVidage();
	
	public abstract double getVitesseMilieu(NatureTerrain t);
	
	public Case getCase() {
		return position;
	}
	
	public void setPosition(Case c) {
		position = c;
	}
	
	public void move(Direction d) throws InvalidCaseException {
		if (this.getVitesseMilieu(carte.getCase(position, d).getNature())!= 0)
			setPosition(carte.getCase(position, d));
		else
			throw new InvalidCaseException("Ce robot ne peut pas se rendre sur cette surface");
	}
	
	public int getEauDispo() {
		return this.eau_dispo;
	}
	
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
	
	public double getTempsDeplacement(Direction d) throws InvalidCaseException {
		try {
				System.out.println("dernier event : " + this.dernierEvent.getDate());
				return this.getVitesseMilieu(carte.getCase(position, d).getNature());
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
	
	public boolean isAlive() {
		return true;
	}
}
