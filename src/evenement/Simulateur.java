package evenement;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;
/**
 * <b><code>Simulateur</code> est la classe contenant la file de priorit� d'Evenement. Elle garde une date courant et execute les Evenements ant�rieur � cette date.</b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Simulateur {

	/**
	 * Temps par d�faut d'incr�ment d'une step de simulation.
	 */
	private double step_duration = 1;
	/**
	 * Date courante. Incrementer de step_duration par step.
	 */
	private Date date = new Date(0d);
	/**
	 * File de priorit� contenant les Evenement.
	 */
	private Queue<Evenement> l = new PriorityQueue<Evenement>();
	/**
	 * Donn�e de simulation.
	 */
	private DonneesSimulation data;
	/**
	 * hash contenant les �l�ments � updater � chaque step.
	 */
	private Set<WorldElement> hash = new HashSet<WorldElement>();

	/**
	 * Manager associ� au simulateur.
	 */
	private Manager manager;

	/**
	 * Cr�� un nouveau simulateur avec les DonneesSimulation data.
	 * @param data les doneeSimualtion.
	 */
	public Simulateur(DonneesSimulation data) {
		this.data = data;
	}


	public DonneesSimulation getData() {
		return data;
	}

	/**
	 * Change le temps d'increment � chaque step.
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
	 * Ajoute un Evenement au simulateur. L'evement sera execut� lorsque sa Date sera onf�rieur � celle du simulateur.
	 * @param e l'evement � executer.
	 */
	public void addEvenement(Evenement e){
		e.setData(data);
		l.add(e);
	}

	/**
	 * Incremente la date courante de step_duration et execute les Evenement ayant une date inf�rieurs � la date courante.
	 * @return l'ensemble des WorldElement dont l'etat � changer.
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
				System.out.println("Evenement non réalisable " + e.getDate());
				break;
			}
		}
		return hash;
	}

	/**
	 * On enl�ve les r�f�rences cycliques.
	 */
	public void clear() {
		hash.clear();
		l.clear();
		manager = null;
	}
}
