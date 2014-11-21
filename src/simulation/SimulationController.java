package simulation;

import ihm.Simulable;

import java.io.FileNotFoundException;
import java.util.Set;

import donnees.ExceptionFormatDonnees;
import donnees.WorldElement;
import evenement.Manager;
import evenement.Simulateur;

/**
 * <b><code>SimulationController</code> implement Simulable, gère les appelle de next et restart </b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class SimulationController implements Simulable {

	private SimulationModel data;
	private SimulationWindow window;
	/**
	 * la classe de manager à instancier au chargment.
	 */
	private Class<? extends Manager> manager_class;
	
	private Simulateur simulateur;
	private Manager manager;
	
	private boolean first = true;
	/**
	 * Creer un nouveau SimulationController avec les données spécifié, la vue et la classe de manager.
	 * @param data2 les données
	 * @param simulationWindow la vue.
	 * @param m le manager.
	 */
	public SimulationController(SimulationModel data2, SimulationWindow simulationWindow, Class<? extends Manager> m) {
		manager_class = m;
		this.data = data2;
		this.window = simulationWindow;
	}

	/**
	 * Appelle step sur le simulateur, et update les WorldElement necessaire.
	 */
	@Override
	public void next() {
		Set<WorldElement> s = simulateur.step();
		for (WorldElement c : s) {
			window.update(c);
		}
	}

	/**
	 * Recharge les données.
	 * @throws FileNotFoundException
	 * @throws ExceptionFormatDonnees
	 */
	public void reload() throws FileNotFoundException, ExceptionFormatDonnees {
		data.load();
	}
	
	/**
	 * Recharge les données, et le manager/simulateur. Enfin update la vue.
	 */
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
