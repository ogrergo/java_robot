import java.io.FileNotFoundException;

import simulation.SimulationModel;
import simulation.SimulationWindow;
import donnees.ExceptionFormatDonnees;


public class Main {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}
		SimulationModel model = new SimulationModel();
		try {
			model.load(args[0]);
			new SimulationWindow(model);
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (ExceptionFormatDonnees e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}

}
