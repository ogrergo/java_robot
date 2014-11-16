package evenement;

import donnees.Direction;

public class ManagerScenario0 extends Manager {
	@Override
	public void manage() {
		simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(100L), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(1000L), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(10000L), simulateur.getData().getRobotbyId(0), Direction.NORD));
	}

}
