package evenement;

import donnees.Direction;

public class ManagerScenario1 extends Manager {

	@Override
	public void manage() {
		simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(1), Direction.NORD));
	}

}
