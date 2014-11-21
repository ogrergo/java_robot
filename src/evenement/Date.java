package evenement;

/**
 * <b><code>Date</code> est la classe représentant une date.
 * Cette classe implémente l'interface générique Comparable</b>
 * <p>
 * La stratégie est caractérisée par :
 * <ul>
 * <li>un nombre réel</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Date implements Comparable<Date>{
	/**
	 * Nombre réel représentant une date
	 */
	private double date;
	
	/**
	 * Constructeur1 d'une date 
	 * 
	 * @param timestamp
	 * 				un nombre réel
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
	 * Incrémente la date d'une valeur donnée
	 * 
	 * @param step_duration
	 * 				valeur de l'incrémentation
	 */
	public void increment(double step_duration) {
		date += step_duration;
	}
	
	/**
	 * Fonction static qui convertie en m/min une valeur étant en km/h
	 * 
	 * @param vitesse
	 * 			une vitesse en km/h
	 * @return une vitesse en m/min
	 */
	public static double toMpMin(double vitesse) {
		return vitesse * 1000. / 60.;
	}
}
