import java.io.FileNotFoundException;

import simulation.SimulationModel;
import simulation.SimulationWindow;
import donnees.ExceptionFormatDonnees;
import evenement.ManagerTestAstar;

public class TestAstar {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java Main <nomDeFichier>");
			System.exit(1);
		}
		SimulationModel model = new SimulationModel();
		try {
			model.setFile(args[0]);
			model.load();
			System.out.println("Robot doit se rendre à la case (0,0) en utilisant le chemin le plus court");
			new SimulationWindow(model, ManagerTestAstar.class);
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (ExceptionFormatDonnees e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}

}
