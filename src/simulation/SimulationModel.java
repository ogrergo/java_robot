package simulation;

import java.io.FileNotFoundException;

import donnees.DonneesSimulation;
import donnees.ExceptionFormatDonnees;
import donnees.LecteurDonnees;

public class SimulationModel {
	private DonneesSimulation data;
	private String path;
	
	public void load(String path) throws FileNotFoundException, ExceptionFormatDonnees {
		this.path = path;
		reload();
	}
	
	public void reload() throws FileNotFoundException, ExceptionFormatDonnees {
		data = LecteurDonnees.lire(path);
		data.getCarte().init();
	}
	
	public DonneesSimulation getData() {
		return data;
	}
}
