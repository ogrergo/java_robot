package evenement;

import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;

public abstract class Evenement implements Comparable<Evenement>{
	protected Date date;
	protected DonneesSimulation data;
	protected Simulateur simulateur;
	
	public Evenement(Date date, Simulateur s) {
		this.date = date;
		this.simulateur = s;
	}
	
	public void setData(DonneesSimulation donneesProvider) throws ExecutionException {
		this.data = donneesProvider;
		//Mise à jour de la date en fonction du temps que va prendre la prochaine action
		updateDate();
	}
	public Date getDate() {
		return date;
	}
	
	public int compareTo(Evenement e) {
		return date.compareTo(e.date);
	}

	public abstract Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException;
	
	public abstract void updateDate() throws ExecutionException;
}
