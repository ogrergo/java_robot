package evenement;

/**
 * <b><code>Date</code> est la classe repr�sentant une date.
 * Cette classe impl�mente l'interface g�n�rique Comparable</b>
 * <p>
 * La strat�gie est caract�ris�e par :
 * <ul>
 * <li>un nombre r�el</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Date implements Comparable<Date>{
	/**
	 * Nombre r�el repr�sentant une date
	 */
	private double date;
	
	/**
	 * Constructeur1 d'une date 
	 * 
	 * @param timestamp
	 * 				un nombre r�el
	 */
	public Date(double timestamp) {
		this.date = timestamp;
	}

	/**
	 * Constructeur2 d'une date 
	 * 
	 * @param d
	 * 			une date
	 */
	public Date(Date d) {
		date = d.date;
	}

	/**
	 * Retourne la date
	 * 
	 * @return la date de l'objet courant
	 */
	public double getDate() {
		return date;
	}

	/**
	 * Compare deux dates
	 * 
	 * @param d
	 * 			une date
	 * 
	 * @return 1 si d est plus grand que this, faux sinon
	 */
	public int compareTo(Date d) {
		return (date - d.date > 0 ? 1 : -1);
	}

	/**
	 * Incr�mente la date d'une valeur donn�e
	 * 
	 * @param step_duration
	 * 				valeur de l'incr�mentation
	 */
	public void increment(double step_duration) {
		date += step_duration;
	}
	
	/**
	 * Fonction static qui convertie en m/min une valeur �tant en km/h
	 * 
	 * @param vitesse
	 * 			une vitesse en km/h
	 * @return une vitesse en m/min
	 */
	public static double toMpMin(double vitesse) {
		return vitesse * 1000. / 60.;
	}
}
