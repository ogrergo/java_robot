package simulation;

import java.io.FileNotFoundException;

import donnees.DonneesSimulation;
import donnees.ExceptionFormatDonnees;
import donnees.LecteurDonnees;

public class SimulationModel {
	private DonneesSimulation data;
	private String path;
	
	public void setFile(String path) {
		this.path = path;
	}
	
	public void load() throws FileNotFoundException, ExceptionFormatDonnees {
		data = LecteurDonnees.lire(path);
		data.getCarte().init();
	}
	
	public DonneesSimulation getData() {
		return data;
	}
}
