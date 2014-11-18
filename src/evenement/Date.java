package evenement;

public class Date implements Comparable<Date>{
	private long date;
	
	public Date(long timestamp) {
		this.date = timestamp;
	}

	public long getDate() {
		return date;
	}

	public int compareTo(Date d) {
		return (int) (date - d.date);
	}

	public void increment(long inc) {
		date += inc;
	}
}
