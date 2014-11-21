package evenement;

import strategie.Strategie;


public class ManagerTestGenerationScenario extends Manager {
	boolean first_time = true;
	
	@Override
	public void manage() {
		
		if(!first_time)
			return;
		
		first_time = false;
		
		simulateur.setSimulationStepDuration(1);
		Strategie str = simulateur.getData().getRobotbyId(0).getBestStrategie(
				simulateur.getData().getIncendiebyId(0), 
				simulateur.getData());
		Strategie str1 = simulateur.getData().getRobotbyId(1).getBestStrategie(
				simulateur.getData().getIncendiebyId(1), 
				simulateur.getData());
		Strategie str2 = simulateur.getData().getRobotbyId(2).getBestStrategie(
				simulateur.getData().getIncendiebyId(2), 
				simulateur.getData());
		if(str != null)
		simulateur.getData().getRobotbyId(0).doStrategie(str, simulateur);
		if(str1 != null)
		simulateur.getData().getRobotbyId(1).doStrategie(str1, simulateur);
		if(str2 != null)
		simulateur.getData().getRobotbyId(2).doStrategie(str2, simulateur);

	}

}