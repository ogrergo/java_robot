package evenement;

import java.util.Set;

import donnees.DonneesSimulation;
import donnees.WorldElement;

/**
 * <b><code>Evenement</code> est la classe abstraite des evenements possibles.
 * Cette classe implémente l'interface générique Comparable</b>
 * <p>
 * Un evenement est caractérisé par :
 * <ul>
 * <li>une date</li>
 * <li>les données de la simulation</li>
 * <li>le simulateur</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public abstract class Evenement implements Comparable<Evenement>{
	/**
	 * la date à  laquelle à  lieu l'evenement
	 */
	private Date date;
	
	/**
	 * les données de la simulation
	 */
	protected DonneesSimulation data;
	
	/**
	 * le simulateur 
	 */
	protected Simulateur simulateur;
	
	/**
	 * Constructeur d'un evenement
	 * 
	 * @param date
	 * 			la date à  laquelle à  lieu l'evenement
	 * @param s
	 * 			le simulateur
	 */
	public Evenement(Date date, Simulateur s) {
		this.date = new Date(date);
		this.simulateur = s;
	}
	
	/**
	 * Permet de modifier data
	 * 
	 * @param donneesProvider
	 * 			les données de la simulation
	 */
	public void setData(DonneesSimulation donneesProvider){
		this.data = donneesProvider;
	}
	
	/**
	 * Retourne la date de l'evenement
	 * 
	 * @return la date de l'evenement
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Compare deux evenement
	 * 
	 * @param e
	 * 			un evenement
	 * 
	 * @return 1 si la date associé à  e est plus grand que this.date, faux sinon
	 */	
	public int compareTo(Evenement e) {
		return date.compareTo(e.date);
	}

	/**
	 * Fonction abstraite permettant d'executer les fonctions associées à  cet Evenement
	 * 
	 * @param s
	 * 			un ensemble de WordlElement 
	 * 
	 * @return un ensemble de WorldElement
	 * @throws ExecutionException : si l'exception InvalidCaseException est levée
	 */
	public abstract Set<WorldElement> execute(Set<WorldElement> s) throws ExecutionException;
}
