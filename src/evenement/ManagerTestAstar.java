package evenement;

import java.util.List;

import donnees.Astar;
import donnees.InvalidCaseException;
import strategie.ActionMove;
import strategie.Strategie;

public class ManagerTestAstar extends Manager {

	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);

		try {
			Strategie strat = new Strategie();

			List<ActionMove> l = Astar.getShortestPath(
					simulateur.getData().getRobotbyId(1).getCase(), 
					simulateur.getData().getCarte().getCase(0, 0), 
					simulateur.getData().getCarte(),
					simulateur.getData().getRobotbyId(1));
			strat.addAction(l);
			simulateur.getData().getRobotbyId(1).doStrategie(strat, simulateur);
		} catch (InvalidCaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
