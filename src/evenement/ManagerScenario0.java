package evenement;

import donnees.InvalidCaseException;

public class ManagerScenario0 extends Manager {
	
	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		try {
			simulateur.getData().getRobotbyId(1).moveto(simulateur.getData().getCarte().getCase(0, 0), simulateur);
		} catch (InvalidCaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//simulateur.addEvenement(new EvenementDeplacement(new Date(10L), simulateur.getData().getRobotbyId(1), Direction.NORD, simulateur));
		//simulateur.addEvenement(new EvenementDeplacement(new Date(110L), simulateur.getData().getRobotbyId(0), Direction.NORD, simulateur));
	}

}
