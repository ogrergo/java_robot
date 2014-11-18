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
	
	public boolean removeIncendie(Incendie e) {
		return incendies.remove(e);
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

	public Incendie getIncendieAtCase(Case c) {
		for(Incendie inc : incendies) {
			if(inc.getCase() == c)
				return inc;
		}
		return null;
	}
	
	public Collection<Robot> getRobots() {
		return robots;
	}
	
	public Robot getRobotbyId(int id) {
		return robots.get(id);
	}
	
	public Incendie getIncendiebyId(int id) {
		return incendies.get(id);
	}
}
