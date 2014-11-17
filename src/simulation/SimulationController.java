package simulation;

import java.io.FileNotFoundException;
import java.util.Set;

import donnees.ExceptionFormatDonnees;
import donnees.WorldElement;
import evenement.ExecutionException;
import evenement.Manager;
import evenement.Simulateur;
import ihm.Simulable;

public class SimulationController implements Simulable {

	private SimulationModel data;
	private SimulationWindow window;
	
	private Class<? extends Manager> manager_class;
	
	private Simulateur simulateur;
	private Manager manager;
	
	public SimulationController(SimulationModel data2, SimulationWindow simulationWindow, Class<? extends Manager> m) {
		manager_class = m;
		this.data = data2;
		this.window = simulationWindow;
	}

	
	@Override
	public void next() {
		Set<WorldElement> s = simulateur.step();
		for (WorldElement c : s) {
			System.out.println("case to update ("+ c.getCase().getLigne()+ ", " + c.getCase().getColonne()+ ")");
			window.update(c);
		}
	}

	@Override
	public void restart() {
		//probleme si on change le fichier entre les 2 appels
		try {
			data.reload();
			simulateur = new Simulateur(data.getData());
			try {
				manager = manager_class.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return;
			}
			manager.setSimulateur(simulateur);
			simulateur.setManager(manager);
		} catch (FileNotFoundException | ExceptionFormatDonnees | ExecutionException e) {
			e.printStackTrace();
		}
		window.updateAll();
	}
	
	public void setManagerType(Class<? extends Manager> m) {
		manager_class = m;
		restart();
	}

}
