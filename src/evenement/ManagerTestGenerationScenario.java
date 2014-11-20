package evenement;

import strategie.Strategie;


public class ManagerTestGenerationScenario extends Manager {

	@Override
	public void manage() {
		simulateur.setSimulationStepDuration(1);
		Strategie str = simulateur.getData().getRobotbyId(3).getBestStrategie(
				simulateur.getData().getIncendiebyId(3), 
				simulateur.getData());
		Strategie str1 = simulateur.getData().getRobotbyId(1).getBestStrategie(
				simulateur.getData().getIncendiebyId(4), 
				simulateur.getData());
		Strategie str2 = simulateur.getData().getRobotbyId(2).getBestStrategie(
				simulateur.getData().getIncendiebyId(5), 
				simulateur.getData());
		if(str != null)
		simulateur.getData().getRobotbyId(3).doStrategie(str, simulateur);
		if(str1 != null)
		simulateur.getData().getRobotbyId(1).doStrategie(str1, simulateur);
		if(str2 != null)
		simulateur.getData().getRobotbyId(2).doStrategie(str2, simulateur);

	}

}