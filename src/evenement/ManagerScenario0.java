package evenement;

import donnees.Direction;

public class ManagerScenario0 extends Manager {
	
	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(2), Direction.NORD, simulateur));
		//simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(1), Direction.NORD, simulateur));
		simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(1), Direction.NORD, simulateur));
		//simulateur.addEvenement(new EvenementDeplacement(new Date(110L), simulateur.getData().getRobotbyId(0), Direction.NORD, simulateur));
	}

}
