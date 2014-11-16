package evenement;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;


public class Simulateur {

	private int step_duration;
	private Date date = new Date(0l);
	private Queue<Evenement> l = new PriorityQueue<Evenement>();
	private DonneesSimulation data;
	private Set<WorldElement> hash = new HashSet<WorldElement>();
	
	private Manager manager;


	public Simulateur(DonneesSimulation data) {
		this.data = data;
	}

	public DonneesSimulation getData() {
		return data;
	}
	
	public void setSimulationStepDuration(int stepduration) {
		this.step_duration = stepduration;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
		this.manager.manage();
	}

	public void addEvenement(Evenement e) {
		e.setData(data);
		l.add(e);
	}

	public void incrementeDate(long inc) {
		date.increment(inc);
	}

	public boolean isSimulationOver() {
		return l.isEmpty();
	}

	public Set<WorldElement> step() {
		date.increment(step_duration);
		hash.clear();
		while(true) {
			Evenement e = l.peek();
			if(e != null && e.getDate().getDate() < date.getDate()) {
				e = l.poll();
				try {
					hash = e.execute(hash);
				} catch (ExecutionException e1) {
					e1.printStackTrace();
				}
			} else {
				break;
			}
		}
		return hash;
	}
}
