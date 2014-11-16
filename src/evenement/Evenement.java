package evenement;

import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;

public abstract class Evenement implements Comparable<Evenement>{
	private Date date;
	protected DonneesSimulation data;
	
	public Evenement(Date date) {
		this.date = date;
	}
	
	public void setData(DonneesSimulation donneesProvider) {
		this.data = donneesProvider;
	}
	public Date getDate() {
		return date;
	}
	
	public int compareTo(Evenement e) {
		return date.compareTo(e.date);
	}

	public abstract Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException;
	
}
