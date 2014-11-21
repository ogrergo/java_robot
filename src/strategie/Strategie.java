package strategie;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>Strategie</code> est la classe représentant une stratégie</b>
 * <p>
 * La stratégie est caractérisée par :
 * <ul>
 * <li>la liste des actions à  effectuer pour éteindre un feu</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Strategie {
	/**
	 * La liste des actions à  réaliser
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	/**
	 * Ajoute une action à  la liste des actions de la stratégie
	 * 
	 * @param a
	 * 			l'action à  ajouter à  la stratégie
	 */
	public void addAction(Action a) {
		actions.add(a);
	}
	
	/**
	 * Ajoute une liste d'action à  la liste des actions de la stratégie
	 * 
	 * @param a
	 * 			la liste d'action à  ajouter
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
	 * Retourne le nombre d'action présent dans la liste 
	 * 
	 * @return le nombre d'action présent dans la liste
	 */
	public int getNbActions() {
		return actions.size();
	}

	/**
	 * Retourne l'action correspondant à  l'indice donné dans la liste des actions
	 * 
	 * @param i
	 * 			indice correspondant à  une action de la liste des actions
	 * 
	 * @return l'action correspondant à  l'indice i dans la liste des actions
	 */
	public Action getAction(int i) {
		return actions.get(i);
	}

}
