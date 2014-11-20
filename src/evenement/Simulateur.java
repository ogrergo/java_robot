package evenement;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;


public class Simulateur {

	private double step_duration;
	private Date date = new Date(0d);
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
	
	public void setSimulationStepDuration(double d) {
		this.step_duration = d;
	}

	public void setManager(Manager manager) throws ExecutionException {
		this.manager = manager;
		this.manager.manage();
	}

	public void addEvenement(Evenement e){
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
		//System.out.println("Date " + date.getDate());
		while(true) {
			Evenement e = l.peek();
			try {
				if(e != null && e.getDate().getDate() < date.getDate()) {
					//System.out.println("Exec lsize" + l.size() + " temps ecoulé " + date.getDate());
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
}
