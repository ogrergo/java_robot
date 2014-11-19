package donnees;

import java.util.List;

import evenement.Date;
import evenement.EvenementDeplacement;
import evenement.EvenementDeplacementFin;
import evenement.EvenementEteindreFeu;
import evenement.EvenementEteindreFeuFin;
import evenement.EvenementRemplirReservoir;
import evenement.EvenementRemplirReservoirFin;
import evenement.Simulateur;

public abstract class Robot implements WorldElement {
	protected Case position;
	protected static Carte carte;
	protected double vitesse_defaut;
	private int eau_dispo;
	
	private Case dernierPosition = null;
	private Date dernierEvent = new Date(0);
	
	
	public Robot(Case position) {
		this.eau_dispo = this.getEauMax();
		this.position = position;
		dernierPosition = position;
	}
	
	public static void setCarte(Carte c) {
		carte = c;
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
	
	protected abstract int getEauMax();
	
	protected abstract double getEauTempsRemplissage();
	
	protected abstract double getEauTempsVidage();

	protected abstract double getEauLitreVidage();
	
	protected abstract double getVitesseMilieu(NatureTerrain t);
	
	public Case getCase() {
		return position;
	}
	
	public void moveto(Case c, Simulateur simu) throws InvalidCaseException {
		List<Direction> direction = Astar.getShortestPath(dernierPosition, c, carte, this);
		for(int i = 0; i < direction.size(); i++) {
			System.out.println("D: " +  direction.get(i));
			addRobotMoveEvent(direction.get(i), simu);
		}
	}
	
	public void addRobotMoveEvent(Direction d, Simulateur s) throws InvalidCaseException {
		s.addEvenement(
				new EvenementDeplacement(
						dernierEvent,
						this, 
						s));
		
		dernierEvent.increment(
				(long) carte.tempsDeplacement(
						this, 
						dernierPosition,
						carte.getCase(dernierPosition, d)));
		
		s.addEvenement(
				new EvenementDeplacementFin(
						dernierEvent, 
						this,
						d,
						s));
		
		dernierPosition = carte.getCase(dernierPosition, d);
		dernierEvent.increment(1);
	}
	
	public void addRobotEteindreFeu(int eau, Simulateur s) {
		Incendie incendie = s.getData().getIncendieAtCase(dernierPosition);
		s.addEvenement(
				new EvenementEteindreFeu(
						dernierEvent,
						this,
						s));
		
		dernierEvent.increment(
				(long) getTempsVider(eau));
		
		s.addEvenement(
				new EvenementEteindreFeuFin(
						dernierEvent, 
						this,
						incendie,
						eau,
						s));
		
		dernierEvent.increment(1);
	}
	
	public void addRobotRemplirReservoir(Simulateur s) {
		s.addEvenement(
				new EvenementRemplirReservoir(
						dernierEvent, 
						this,
						s));
		
		dernierEvent.increment(
				(long) getTempsremplir());
		
		s.addEvenement(
				new EvenementRemplirReservoirFin(
						dernierEvent, 
						this,
						s));
		
		dernierEvent.increment(1);
	}
	
	private void setPosition(Case c) {
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
	public void deverserEau(Incendie incendie, int volume, DonneesSimulation data) {
		if (this.eau_dispo != -1) { //Si le robot n'est pas un robot à pattes
			if (this.eau_dispo >= volume)
				this.eau_dispo -= volume;
			else {
				this.eau_dispo = 0;
			}
		}
		
		incendie.eteindre((int) ((volume/getEauLitreVidage())*getEauLitreVidage() == volume ? volume : (1 + volume/getEauLitreVidage())*getEauLitreVidage()), data);
	}
	
	public void remplirReservoir() {
		this.eau_dispo = this.getEauMax();
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
