package evenement;

public class Date implements Comparable<Date>{
	private double date;
	
	public Date(double timestamp) {
		this.date = timestamp;
	}
	
	public Date(Date d) {
		date = d.date;
	}

	public double getDate() {
		return date;
	}

	public int compareTo(Date d) {
		return (date - d.date > 0 ? 1 : -1);
	}

	public void increment(double step_duration) {
		date += step_duration;
	}
	
	public static double toMpMin(double vitesse) {
		return vitesse * 1000. / 60.;
	}
}
