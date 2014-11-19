package donnees;

import strategie.Action;
import strategie.ActionMove;
import strategie.ActionRemplissage;
import strategie.ActionVidage;
import strategie.Strategie;
import evenement.Date;
import evenement.EvenementAction;
import evenement.EvenementStrategieDebut;
import evenement.EvenementStrategieFin;
import evenement.Simulateur;

public abstract class Robot implements WorldElement {
	protected Case position;
	protected double vitesse_defaut;
	private int eau_dispo;
	
	private Date dernierEvent = new Date(0);
	private State state;
	
	
	public Robot(Case position) {
		this.eau_dispo = this.getEauMax();
		this.position = position;
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
	
	protected abstract double getVitesseMilieu(NatureTerrain t, Carte c);
	
	public Case getCase() {
		return position;
	}
	
	
	public void doStrategie(Strategie strat, Simulateur s) {
		s.addEvenement(
				new EvenementStrategieDebut(dernierEvent, s, this));
		dernierEvent.increment(1);
		
		for(int i = 0; i < strat.getNbActions(); i++) {
			System.out.println("Posting");
			addActionEvent(strat.getAction(i), s);
		}

		s.addEvenement(
				new EvenementStrategieFin(dernierEvent, s, this));
		dernierEvent.increment(1);

	}
	
	private void addActionEvent(Action action, Simulateur s) {
		dernierEvent.increment((long) action.getCout());
		s.addEvenement(
				new EvenementAction(
						dernierEvent, 
						s, 
						action, 
						this));
		dernierEvent.increment(1);
	}

	public void doAction(Action action, Simulateur s) throws InvalidCaseException {
		if(action instanceof ActionMove) {
			doActionMove(action, s);
		} else if(action instanceof ActionRemplissage) {
			doActionRemplissage(action, s);
		} else {
			doActionVidage(action, s);
		}
		
	}

	private void doActionVidage(Action action, Simulateur s) throws InvalidCaseException {
		System.out.print("Avant res" + eau_dispo + " ");
		deverserEau(s.getData().getIncendieAtCase(position), ((ActionVidage)action).getNbInterventionElem(), s.getData());
		System.out.println(" apres : " + eau_dispo);
	}

	private void doActionRemplissage(Action action, Simulateur s) {
		remplirReservoir();
	}

	private void doActionMove(Action action, Simulateur s) throws InvalidCaseException {
		move(((ActionMove)action).getDirection(), s.getData().getCarte());
	}

	private void move(Direction d, Carte c) throws InvalidCaseException {
		if (this.getVitesseMilieu(c.getCase(position, d).getNature(), c)!= 0)
			position = c.getCase(position, d);
		else
			throw new InvalidCaseException("Ce robot ne peut pas se rendre sur cette surface");
	}
	
	public int getEauDispo() {
		return this.eau_dispo;
	}
	
	//A VERIFIER AVEC SIMULATION 1
	public void deverserEau(Incendie incendie, int nbElem, DonneesSimulation data) {
		if (this.eau_dispo != -1) { //Si le robot n'est pas un robot à pattes
			if (this.eau_dispo >= nbElem * getEauLitreVidage())
				this.eau_dispo -= nbElem * getEauLitreVidage();
			else {
				this.eau_dispo = 0;
			}
		}
		incendie.eteindre((int) (nbElem * getEauLitreVidage()), data);
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
	
	//A modif
	public double getTempsremplir() {
		return this.getEauTempsRemplissage() * this.getEauMax();
	}	
	
	public boolean isAlive() {
		return true;
	}

	public void setState(State s) {
		this.state = s;
	}
	public State getState() {
		return state;
	}
}
