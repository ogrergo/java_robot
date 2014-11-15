package donnees;

import java.util.ArrayList;
import java.util.Collection;

public class DonneesSimulation {
	private Carte carte;
	private ArrayList<Incendie> incendies = new ArrayList<Incendie>();
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	
	public DonneesSimulation(Carte carte) {
		this.carte = carte;
	}
	
	public void addIncendie(Incendie e) {
		incendies.add(e);
	}
	
	public void addRobot(Robot r) {
		robots.add(r);
	}
	
	public Carte getCarte() {
		return carte;
	}

	public Collection<Incendie> getIncendies() {
		return incendies;
	}
	
	public Collection<Robot> getRobots() {
		return robots;
	}
	
	public Robot getRobotbyId(int id) {
		return robots.get(id);
	}
}
