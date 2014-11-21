package strategie;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>Strategie</code> est la classe repr�sentant une strat�gie</b>
 * <p>
 * La strat�gie est caract�ris�e par :
 * <ul>
 * <li>la liste des actions � effectuer pour �teindre un feu</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Strategie {
	/**
	 * La liste des actions � r�aliser
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	/**
	 * Ajoute une action � la liste des actions de la strat�gie
	 * 
	 * @param a
	 * 			l'action � ajouter � la strat�gie
	 */
	public void addAction(Action a) {
		actions.add(a);
	}
	
	/**
	 * Ajoute une liste d'action � la liste des actions de la strat�gie
	 * 
	 * @param a
	 * 			la liste d'action � ajouter
	 */
	public void addAction(List<? extends Action> a) {
		actions.addAll(a);
	}
	
	/**
	 * Retourne le cout d'une strategie
	 * 
	 * @return le cout d'une strategie
	 */
	public double getCout() {
		double res = 0;
		for(Action a : actions) {
			res += a.getCout();
		}
		return res;
	}

	/**
	 * Retourne le nombre d'action pr�sent dans la liste 
	 * 
	 * @return le nombre d'action pr�sent dans la liste
	 */
	public int getNbActions() {
		return actions.size();
	}

	/**
	 * Retourne l'action correspondant � l'indice donn� dans la liste des actions
	 * 
	 * @param i
	 * 			indice correspondant � une action de la liste des actions
	 * 
	 * @return l'action correspondant � l'indice i dans la liste des actions
	 */
	public Action getAction(int i) {
		return actions.get(i);
	}

}
