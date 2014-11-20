package simulation;

import ihm.Simulable;

import java.io.FileNotFoundException;
import java.util.Set;

import donnees.ExceptionFormatDonnees;
import donnees.WorldElement;
import evenement.Manager;
import evenement.Simulateur;

public class SimulationController implements Simulable {

	private SimulationModel data;
	private SimulationWindow window;
	
	private Class<? extends Manager> manager_class;
	
	private Simulateur simulateur;
	private Manager manager;
	
	private boolean first = true;
	
	public SimulationController(SimulationModel data2, SimulationWindow simulationWindow, Class<? extends Manager> m) {
		manager_class = m;
		this.data = data2;
		this.window = simulationWindow;
	}

	
	@Override
	public void next() {
		Set<WorldElement> s = simulateur.step();
		for (WorldElement c : s) {
			window.update(c);
		}
	}

	public void reload() throws FileNotFoundException, ExceptionFormatDonnees {
		data.load();
	}
	
	@Override
	public void restart() {
		
		
		try {
			if(first)
				first = false;
			else
				reload();
			
			if(simulateur != null) {
				simulateur.clear();
			}
			simulateur = new Simulateur(data.getData());
			
			manager = manager_class.newInstance();
			manager.setSimulateur(simulateur);
			simulateur.setManager(manager);
			window.updateAll();
		} catch (InstantiationException | IllegalAccessException | FileNotFoundException | ExceptionFormatDonnees e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	public void setManagerType(Class<? extends Manager> m) {
		manager_class = m;
		restart();
	}

}
