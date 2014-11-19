package evenement;

import strategie.Strategie;


public class ManagerTestGenerationScenario extends Manager {

	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		Strategie str = simulateur.getData().getRobotbyId(0).getBestStrategie(
				simulateur.getData().getIncendiebyId(0), 
				simulateur.getData());
		/*Strategie str1 = simulateur.getData().getRobotbyId(1).getBestStrategie(
				simulateur.getData().getIncendiebyId(1), 
				simulateur.getData());
		Strategie str2 = simulateur.getData().getRobotbyId(2).getBestStrategie(
				simulateur.getData().getIncendiebyId(2), 
				simulateur.getData());*/
		simulateur.getData().getRobotbyId(0).doStrategie(str, simulateur);
		//simulateur.getData().getRobotbyId(1).doStrategie(str1, simulateur);
		//simulateur.getData().getRobotbyId(2).doStrategie(str2, simulateur);
	}

}