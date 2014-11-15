package evenement;

import donnees.Direction;

public class ManagerScenario extends Manager {
	@Override
	public void manage() {
		simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData(), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(100L), simulateur.getData(), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(1000L), simulateur.getData(), simulateur.getData().getRobotbyId(0), Direction.NORD));
		simulateur.addEvenement(new EvenementDeplacement(new Date(10000L), simulateur.getData(), simulateur.getData().getRobotbyId(0), Direction.NORD));
	}

}
