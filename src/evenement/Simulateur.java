package evenement;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;
/**
 * <b><code>Simulateur</code> est la classe contenant la file de priorité d'Evenement. Elle garde une date courant et execute les Evenements antérieur à cette date.</b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Simulateur {

	/**
	 * Temps par défaut d'incrément d'une step de simulation.
	 */
	private double step_duration = 1;
	/**
	 * Date courante. Incrementer de step_duration par step.
	 */
	private Date date = new Date(0d);
	/**
	 * File de priorité contenant les Evenement.
	 */
	private Queue<Evenement> l = new PriorityQueue<Evenement>();
	/**
	 * Donnée de simulation.
	 */
	private DonneesSimulation data;
	/**
	 * hash contenant les éléments à updater à chaque step.
	 */
	private Set<WorldElement> hash = new HashSet<WorldElement>();

	/**
	 * Manager associé au simulateur.
	 */
	private Manager manager;

	/**
	 * Créé un nouveau simulateur avec les DonneesSimulation data.
	 * @param data les doneeSimualtion.
	 */
	public Simulateur(DonneesSimulation data) {
		this.data = data;
	}


	public DonneesSimulation getData() {
		return data;
	}

	/**
	 * Change le temps d'increment à chaque step.
	 * @param d
	 */
	public void setSimulationStepDuration(double d) {
		this.step_duration = d;
	}

	/**
	 * Change le Manager. Appelle manage() sur le manager.
	 * @param manager le nouveau manager.
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
		manage();
	}

	/**
	 * Appelle manage() sur le Manager.
	 */
	public void manage() {
		this.manager.manage();
	}

	/**
	 * Ajoute un Evenement au simulateur. L'evement sera executé lorsque sa Date sera onférieur à celle du simulateur.
	 * @param e l'evement à executer.
	 */
	public void addEvenement(Evenement e){
		e.setData(data);
		l.add(e);
	}

	/**
	 * Incremente la date courante de step_duration et execute les Evenement ayant une date inférieurs à la date courante.
	 * @return l'ensemble des WorldElement dont l'etat à changer.
	 */
	public Set<WorldElement> step() {
		date.increment(step_duration);
		hash.clear();
		while(true) {
			Evenement e = l.peek();
			try {
				if(e != null && e.getDate().getDate() < date.getDate()) {
					e = l.poll();
					hash = e.execute(hash);
				} else {
					break;
				}
			} catch (ExecutionException e1) {
				System.out.println("Evenement non rÃ©alisable " + e.getDate());
				break;
			}
		}
		return hash;
	}

	/**
	 * On enlï¿½ve les rï¿½fï¿½rences cycliques.
	 */
	public void clear() {
		hash.clear();
		l.clear();
		manager = null;
	}
}
