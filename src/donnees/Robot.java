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
import evenement.EvenementStrategieFin;
import evenement.Simulateur;

/**
 * <b><code>Robot</code> est la classe abstraite repr�sentant un robot
 * <p>
 * Elle impl�mente <code>WorldElement</code>.
 * </b>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public abstract class Robot implements WorldElement {

	/**
	 * Position du Robot
	 */
	protected Case position;

	/**
	 * Vitesse du robot. En m�tre par minute.
	 */
	protected double vitesse_defaut;

	/**
	 * Quantit� d'eau pr�sente dans le reservoir. En litre.
	 */
	private int eau_dispo;

	protected boolean seRemplitACoteEau;


	private Date dernierEvent = new Date(0);
	/**
	 * Strat�gie en cours d'execution. Vaut <code>null</code> si le robot est libre.
	 */
	private Strategie strat;

	/**
	 * Cr�er un nouveau Robot � la case en param�tre.
	 * @param position la nouvelle position.
	 */
	public Robot(Case position) {
		this.eau_dispo = this.getEauMax();
		this.position = position;	
	}

	public void setVitesse(double v) {
		this.vitesse_defaut = Date.toMpMin(v);
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

	/**
	 * Ordonne au robot de faire la strategie en param�tre. Le Robot poste les <code>Evenement</code> associ�s aux actions de la <code>Strategie</code> dans le simulateur.
	 * 
	 * 
	 * @param strat la strat�gie � effectuer.
	 * @param s le simulateur.
	 */
	public void doStrategie(Strategie strat, Simulateur s) {
		setStrat(strat);
		for(int i = 0; i < strat.getNbActions(); i++) {
			addActionEvent(strat.getAction(i), s);
		}
		dernierEvent.increment(0.1);
		s.addEvenement(
				new EvenementStrategieFin(dernierEvent, s, this));
	}

	/**
	 * G�n�re une strat�gie pour que ce robot �teigne l'incendie en param�tre.
	 * Si le robot n'en trouve pas, renvoie <code>null</code>.
	 * Le robot se rend � l'incendie, puis vide son r�servoir, va le remplir et r�p�te ce sch�ma tant qu'il n'est pas �teint.
	 * 
	 * @param inc l'incendie � eteindre.
	 * @param data les donn�es de simulation.
	 * @return la strat�gie ou <code>null</code>.
	 */
	public Strategie getBestStrategie(Incendie inc, DonneesSimulation data) {

		Case last_case = position;
		int last_eau = eau_dispo;

		Strategie res = new Strategie();
		System.out.println("ldebut_eau" + last_eau);

		int feu = inc.getLitreEau();
		while(true) {
			/*
			 * On va � l'incendie
			 */
			if(last_case != inc.getCase()) {
				List<ActionMove> l_m = Astar.getShortestPath(last_case, inc.getCase(), data.getCarte(), this);

				if(l_m == null)
					return null;

				res.addAction(l_m);
				last_case = inc.getCase();
			}

			/*
			 * On se vide
			 */
			while(last_eau > 0) {
				res.addAction(new ActionVidage(getEauTempsVidage(), 1));

				last_eau -= getEauLitreVidage();
				feu -= getEauLitreVidage();
				if(feu <= 0) {
					System.out.println("last_eau" + last_eau);
					return res;
				}
				
			}

			/*
			 * On va � cot� de l'eau (ou dessus)
			 */
			if(!canFill(last_case, data.getCarte())) {
				Case water = data.getCarte().findNearestWater(last_case, this);
				if(water == null)
					return null;

				List<ActionMove> list = Astar.getShortestPath(last_case, water, data.getCarte(), this);
				if (list == null)
					return null;

				res.addAction(list);
				last_case = water;
			}

			/*
			 * On se remplit
			 */
			res.addAction(new ActionRemplissage(getEauTempsRemplissage()));
			last_eau = this.getEauMax();
		}
	}


	/**
	 * Ajoute l'<code>Evenement</code> associ� � l'<code>Action</code> pass�e en param�tre au simulateur.
	 * Elle est ajout� 
	 * @param action l'action � ajouter
	 * @param s
	 */
	private void addActionEvent(Action action, Simulateur s) {
		dernierEvent.increment(action.getCout());
		s.addEvenement(
				new EvenementAction(
						dernierEvent, 
						s, 
						action, 
						this));
	}

	/**
	 * Effectue l'Action en param�tre. Appelle la bonne fonction de traitement en fonction du type de l'action.
	 * @param action l'action � faire.
	 * @param s le simulateur.
	 * @throws InvalidCaseException Si l'action echoue.
	 */
	public void doAction(Action action, Simulateur s) throws InvalidCaseException {
		if(action.getTypeAction() == TypeAction.ACTIONMOVE) {
			doActionMove(action, s);
		} else if(action.getTypeAction() == TypeAction.ACTIONREMPLISSAGE) {
			doActionRemplissage(action, s);
		} else {
			doActionVidage(action, s);
		}
	}

	/**
	 * Traitant associ� � l'Action vidage. Vide le reservoir comme sp�cifier dans l'action sur l'incendie pr�sent.
	 * @param action l'action.
	 * @param s le simulateur.
	 * @throws InvalidCaseException Si il n'y a pas de d'incendie � cette case.
	 */
	private void doActionVidage(Action action, Simulateur s) throws InvalidCaseException {
		//System.out.println("ALplle");
		deverserEau(s.getData().getIncendieAtCase(position), ((ActionVidage)action).getNbInterventionElem(), s.getData());
	}

	/**
	 * Traitant associ� � l'action remplissage. Remplit le reservoir � sa capacit�e maximal.
	 * @param action l'action.
	 * @param s le simulateur.
	 */
	private void doActionRemplissage(Action action, Simulateur s) {
		remplirReservoir();
	}

	/**
	 * Traitant associ� � l'action move. Bouge le robot d'une case dans la direction sp�cifi� dans l'action.
	 * @param action l'action.
	 * @param s le simulateur.
	 * @throws InvalidCaseException Si le robot bouge de mani�re invalide.
	 */
	private void doActionMove(Action action, Simulateur s) throws InvalidCaseException {
		move(((ActionMove)action).getDirection(), s.getData().getCarte());
	}

	/**
	 * D�place le robot dans la direction d.
	 * @param d la direction.
	 * @param c la carte des cases.
	 * @throws InvalidCaseException si on essaye de se deplacer sur une case pas acc�ssible.
	 */
	private void move(Direction d, Carte c) throws InvalidCaseException {
		if (c.estCaseAccessible(this, c.getCase(getCase(), d)))
			position = c.getCase(position, d);
		else
			throw new InvalidCaseException("Ce robot ne peut pas se rendre dans la direction " + d.name() + " depuis " + position);
	}

	/**
	 * Renvoie la quantit� d'eau disponible en litre.
	 * @return l'eau disponible.
	 */
	public int getEauDispo() {
		return this.eau_dispo;
	}

	/**
	 * Deverse le nombre d'intervention �l�mentaire nbElem sur l'incendie en param�tre et retire cette quantit� au total d'eau disponible.
	 * 
	 * @param incendie l'incendie � etteindre.
	 * @param nbElem le nombre d'intervention unitaire � effectuer.
	 * @param data
	 */
	public void deverserEau(Incendie incendie, int nbElem, DonneesSimulation data) {
		if (this.eau_dispo < nbElem * getEauLitreVidage())
			return;

		//System.out.println("eau a mettre : " + incendie.getLitreEau() + " eau dispo " + eau_dispo);
		this.eau_dispo -= nbElem * getEauLitreVidage();
		incendie.eteindre((int) (nbElem * getEauLitreVidage()), data);
	}

	public void remplirReservoir() {
		this.eau_dispo = this.getEauMax();
	}

	public boolean isAlive() {
		return true;
	}

	public void setStrat(Strategie s) {
		this.strat = s;
	}
	public boolean isAvailable() {
		return strat == null;
	}

	/**
	 * Retourne la vitesse par défaut d'un robot
	 * 
	 * @return vitesse par défaut du robot
	 * @see Robot#getVitesse()
	 */
	public double getVitesse() {
		return vitesse_defaut;
	}

	public abstract boolean seRemplieSurEau();
}
