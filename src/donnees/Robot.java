package donnees;

import java.util.List;

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

	protected abstract boolean canFill(Case c, Carte ca);

	public Case getCase() {
		return position;
	}


	public void doStrategie(Strategie strat, Simulateur s) {
		s.addEvenement(
				new EvenementStrategieDebut(dernierEvent, s, this));
		dernierEvent.increment(1);

		for(int i = 0; i < strat.getNbActions(); i++) {
			System.out.println("Posting time" + strat.getAction(i).getCout());
			addActionEvent(strat.getAction(i), s);
		}

		s.addEvenement(
				new EvenementStrategieFin(dernierEvent, s, this));
		dernierEvent.increment(1);

	}

	public Strategie getBestStrategie(Incendie inc, DonneesSimulation data) {
		Strategie res = new Strategie();

		int feu = inc.getLitreEau();
		System.out.println("Feu de " + feu);
		while(true) {
			if(last_case != inc.getCase()) {
				System.out.println("need to go for fire! from  (" + last_case.getLigne() + "; " + last_case.getColonne() + ") to ("+
						inc.getCase().getLigne() + "; " + inc.getCase().getColonne() + ")");
				res.addAction(Astar.getShortestPath(last_case, inc.getCase(), data.getCarte(), this, false));
				last_case = inc.getCase();
			}

			while(last_eau > 0) {
				System.out.println("Verser de l'eau");
				res.addAction(new ActionVidage((int) getEauTempsVidage(), 1));
				last_eau -= getEauLitreVidage();
				feu -= getEauLitreVidage();
				if(feu <= 0)
					return res;
			}
			if(!canFill(last_case, data.getCarte())) {
				Case water = data.getCarte().findNearestWater(last_case, this, seRemplitACoteEau);
				System.out.print("Case water : lig " + water.getLigne() + " col " + water.getColonne());
				if(water == null)
					return null;
				List<ActionMove> list = Astar.getShortestPath(last_case, water, data.getCarte(), this, seRemplitACoteEau);
				if(!canFill(water, data.getCarte()))
					list.remove(list.size() - 1);
				if (list == null) System.out.print("rrrrrrrrrrrr");
				res.addAction(list);
				System.out.print("OOOOOOOO");
				try {
					last_case = ActionMove.getLastCase(list, last_case, data.getCarte());
					System.out.println("Aller remplir a la case "+ last_case.getLigne() + "; " + last_case.getColonne() + ")");

				} catch (InvalidCaseException e) {
					return null;
				}
			}
			System.out.println("puis remmlir");
			res.addAction(new ActionRemplissage((int) (getEauTempsRemplissage() * getEauMax())));
			last_eau = getEauMax();
		}
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


	public double getVitesse() {
		return vitesse_defaut;
	}
}
