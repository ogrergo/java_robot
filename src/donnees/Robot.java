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
 * <b><code>Robot</code> est la classe abstraite représentant un robot
 * <p>
 * Elle implémente <code>WorldElement</code>.
 * </b>
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public abstract class Robot implements WorldElement {

	/**
	 * Position du Robot
	 */
	protected Case position;

	/**
	 * Vitesse du robot. En mètre par minute.
	 */
	protected double vitesse_defaut;

	/**
	 * Quantité d'eau présente dans le reservoir. En litre.
	 */
	private int eau_dispo;

	protected boolean seRemplitACoteEau;


	private Date dernierEvent = new Date(0);
	/**
	 * Stratégie en cours d'execution. Vaut <code>null</code> si le robot est libre.
	 */
	private Strategie strat;

	/**
	 * Créer un nouveau Robot à la case en paramètre.
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
	 * Ordonne au robot de faire la strategie en paramètre. Le Robot poste les <code>Evenement</code> associés aux actions de la <code>Strategie</code> dans le simulateur.
	 * 
	 * 
	 * @param strat la stratégie à effectuer.
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
	 * Génère une stratégie pour que ce robot éteigne l'incendie en paramètre.
	 * Si le robot n'en trouve pas, renvoie <code>null</code>.
	 * Le robot se rend à l'incendie, puis vide son réservoir, va le remplir et répète ce schéma tant qu'il n'est pas éteint.
	 * 
	 * @param inc l'incendie à eteindre.
	 * @param data les donnèes de simulation.
	 * @return la stratégie ou <code>null</code>.
	 */
	public Strategie getBestStrategie(Incendie inc, DonneesSimulation data) {

		Case last_case = position;
		int last_eau = eau_dispo;

		Strategie res = new Strategie();
		System.out.println("ldebut_eau" + last_eau);

		int feu = inc.getLitreEau();
		while(true) {
			/*
			 * On va à l'incendie
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
			 * On va à coté de l'eau (ou dessus)
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
	 * Ajoute l'<code>Evenement</code> associé à l'<code>Action</code> passée en paramètre au simulateur.
	 * Elle est ajouté 
	 * @param action l'action à ajouter
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
	 * Effectue l'Action en paramètre. Appelle la bonne fonction de traitement en fonction du type de l'action.
	 * @param action l'action à faire.
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
	 * Traitant associé à l'Action vidage. Vide le reservoir comme spécifier dans l'action sur l'incendie présent.
	 * @param action l'action.
	 * @param s le simulateur.
	 * @throws InvalidCaseException Si il n'y a pas de d'incendie à cette case.
	 */
	private void doActionVidage(Action action, Simulateur s) throws InvalidCaseException {
		//System.out.println("ALplle");
		deverserEau(s.getData().getIncendieAtCase(position), ((ActionVidage)action).getNbInterventionElem(), s.getData());
	}

	/**
	 * Traitant associé à l'action remplissage. Remplit le reservoir à sa capacitée maximal.
	 * @param action l'action.
	 * @param s le simulateur.
	 */
	private void doActionRemplissage(Action action, Simulateur s) {
		remplirReservoir();
	}

	/**
	 * Traitant associé à l'action move. Bouge le robot d'une case dans la direction spécifié dans l'action.
	 * @param action l'action.
	 * @param s le simulateur.
	 * @throws InvalidCaseException Si le robot bouge de manière invalide.
	 */
	private void doActionMove(Action action, Simulateur s) throws InvalidCaseException {
		move(((ActionMove)action).getDirection(), s.getData().getCarte());
	}

	/**
	 * Déplace le robot dans la direction d.
	 * @param d la direction.
	 * @param c la carte des cases.
	 * @throws InvalidCaseException si on essaye de se deplacer sur une case pas accéssible.
	 */
	private void move(Direction d, Carte c) throws InvalidCaseException {
		if (c.estCaseAccessible(this, c.getCase(getCase(), d)))
			position = c.getCase(position, d);
		else
			throw new InvalidCaseException("Ce robot ne peut pas se rendre dans la direction " + d.name() + " depuis " + position);
	}

	/**
	 * Renvoie la quantité d'eau disponible en litre.
	 * @return l'eau disponible.
	 */
	public int getEauDispo() {
		return this.eau_dispo;
	}

	/**
	 * Deverse le nombre d'intervention élémentaire nbElem sur l'incendie en paramètre et retire cette quantité au total d'eau disponible.
	 * 
	 * @param incendie l'incendie à etteindre.
	 * @param nbElem le nombre d'intervention unitaire à effectuer.
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
	 * Retourne la vitesse par dÃ©faut d'un robot
	 * 
	 * @return vitesse par dÃ©faut du robot
	 * @see Robot#getVitesse()
	 */
	public double getVitesse() {
		return vitesse_defaut;
	}

	public abstract boolean seRemplieSurEau();
}
