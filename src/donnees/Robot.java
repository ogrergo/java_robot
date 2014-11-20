package donnees;

import java.util.List;

import strategie.Action;
import strategie.ActionMove;
import strategie.ActionRemplissage;
import strategie.ActionVidage;
import strategie.Strategie;
import strategie.TypeAction;
import evenement.Date;
import evenement.EvenementAction;
import evenement.EvenementStrategieDebut;
import evenement.EvenementStrategieFin;
import evenement.Simulateur;

public abstract class Robot implements WorldElement {
	protected Case position;
	
	/**
	 * En m/min
	 */
	protected double vitesse_defaut;
	private int eau_dispo;

	private Case last_case;
	private int last_eau;

	protected boolean seRemplitACoteEau;

	private Date dernierEvent = new Date(0);
	private State state;


	public Robot(Case position) {
		this.eau_dispo = this.getEauMax();
		this.position = position;
		last_case = position;
		last_eau = eau_dispo;
		this.state = State.AVAILABLE;
	}

	public void setVitesse(double v) {
		this.vitesse_defaut = Date.toMpMin(v);
	}

	public Date getDernierEvent() {
		return dernierEvent;
	}

	public void setDernierEvent(long dernier) {
		this.dernierEvent.increment(dernier);
	}
	
	public abstract double getEauTempsRemplissage();

	protected abstract int getEauMax();

	protected abstract double getEauTempsVidage();

	protected abstract double getEauLitreVidage();

	protected abstract double getTempsDeplacementMilieu(NatureTerrain t, Carte c);

	protected abstract boolean canFill(Case c, Carte ca);

	public Case getCase() {
		return position;
	}


	public void doStrategie(Strategie strat, Simulateur s) {
		s.addEvenement(
				new EvenementStrategieDebut(dernierEvent, s, this));
		//dernierEvent.increment(1);

		for(int i = 0; i < strat.getNbActions(); i++) {
			System.out.println("Posting time" + strat.getAction(i).getCout());
			addActionEvent(strat.getAction(i), s);
		}

		s.addEvenement(
				new EvenementStrategieFin(dernierEvent, s, this));
		//dernierEvent.increment(1);

	}

	public Strategie getBestStrategie(Incendie inc, DonneesSimulation data) {
		Strategie res = new Strategie();

		int feu = inc.getLitreEau();
		System.out.println("++++Feu de " + feu + " strategie");
		while(true) {
			if(last_case != inc.getCase()) {
				System.out.println("--move from " + last_case + " to " + inc.getCase());
				List<ActionMove> l_m = Astar.getShortestPath(last_case, inc.getCase(), data.getCarte(), this);
				
				if(l_m == null)
					return null;
				
				res.addAction(l_m);
				last_case = inc.getCase();
			}

			while(last_eau > 0) {
				System.out.println("--Verser de l'eau fire:" + feu);
				res.addAction(new ActionVidage(getEauTempsVidage(), 1));
				last_eau -= getEauLitreVidage();
				feu -= getEauLitreVidage();
				if(feu <= 0)
					return res;
			}


			if(!canFill(last_case, data.getCarte())) {
				Case water = data.getCarte().findNearestWater(last_case, this);
				System.out.print("--Case water : " + water);
				if(water == null)
					return null;

				List<ActionMove> list = Astar.getShortestPath(last_case, water, data.getCarte(), this);
				if (list == null)
					return null;
				
				res.addAction(list);
				System.out.println("--move from " + last_case + " to " + water);
				last_case = water;
			}
			
			System.out.println("--puis remplir");
			res.addAction(new ActionRemplissage((int) (getEauTempsRemplissage() * getEauMax())));
			last_eau = getEauMax();
		}
	}


	private void addActionEvent(Action action, Simulateur s) {
		dernierEvent.increment(action.getCout());
		s.addEvenement(
				new EvenementAction(
						dernierEvent, 
						s, 
						action, 
						this));
		//dernierEvent.increment(1);
	}

	public void doAction(Action action, Simulateur s) throws InvalidCaseException {
		if(action.getTypeAction() == TypeAction.ACTIONMOVE) {
			doActionMove(action, s);
		} else if(action.getTypeAction() == TypeAction.ACTIONREMPLISSAGE) {
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
		if (c.estCaseAccessible(this, c.getCase(getCase(), d)))
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


	public double getVitesse() {
		return vitesse_defaut;
	}

	public abstract boolean seRemplieSurEau();
}
