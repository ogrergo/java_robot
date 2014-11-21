package simulation;

import java.io.FileNotFoundException;

import donnees.DonneesSimulation;
import donnees.ExceptionFormatDonnees;
import donnees.LecteurDonnees;
/**
 * <b><code>SimulationModel</code> gère le chargement des donnéesSimulation </b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class SimulationModel {
	private DonneesSimulation data;
	private String path;
	
	/**
	 * Change le fichier source des données.
	 * @param path
	 */
	public void setFile(String path) {
		this.path = path;
	}
	
	/**
	 * Ouvre le fichier source et charge les données en memoire.
	 * @throws FileNotFoundException
	 * @throws ExceptionFormatDonnees
	 */
	public void load() throws FileNotFoundException, ExceptionFormatDonnees {
		data = LecteurDonnees.lire(path);
		data.getCarte().init();
	}
	
	public DonneesSimulation getData() {
		return data;
	}
}
