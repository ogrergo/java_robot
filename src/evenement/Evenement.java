package evenement;

import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;

public abstract class Evenement implements Comparable<Evenement>{
	private Date date;
	protected DonneesSimulation data;
	protected Simulateur simulateur;
	
	public Evenement(Date date, Simulateur s) {
		this.date = new Date(date);
		this.simulateur = s;
	}
	
	public void setData(DonneesSimulation donneesProvider){
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
