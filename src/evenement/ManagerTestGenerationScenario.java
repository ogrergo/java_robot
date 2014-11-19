package evenement;

import strategie.Strategie;


public class ManagerTestGenerationScenario extends Manager {

	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		Strategie str = simulateur.getData().getRobotbyId(0).getBestStrategie(
				simulateur.getData().getIncendiebyId(0), 
				simulateur.getData());
		simulateur.getData().getRobotbyId(0).doStrategie(str, simulateur);
	}

}