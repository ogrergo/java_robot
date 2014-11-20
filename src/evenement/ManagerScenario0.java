package evenement;

import strategie.ActionMove;
import strategie.ActionRemplissage;
import strategie.ActionVidage;
import strategie.Strategie;
import donnees.Carte;
import donnees.Direction;
import donnees.InvalidCaseException;
import donnees.Robot;

public class ManagerScenario0 extends Manager {
	
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
