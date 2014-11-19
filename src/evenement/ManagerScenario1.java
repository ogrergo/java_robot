package evenement;


public class ManagerScenario1 extends Manager {
	
	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		/*simulateur.addEvenement(new EvenementDeplacement(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()),
								simulateur.getData().getRobotbyId(1),
								Direction.NORD, simulateur));
		simulateur.addEvenement(new EvenementEteindreFeu(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()), 
								simulateur.getData().getRobotbyId(1),
								simulateur.getData().getIncendiebyId(4), simulateur));
		simulateur.addEvenement(new EvenementDeplacement(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()),
								simulateur.getData().getRobotbyId(1),
								Direction.OUEST, simulateur));
		simulateur.addEvenement(new EvenementDeplacement(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()),
								simulateur.getData().getRobotbyId(1),
								Direction.OUEST, simulateur));
		simulateur.addEvenement(new EvenementRemplirReservoir(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()), 
								simulateur.getData().getRobotbyId(1), simulateur));
		simulateur.addEvenement(new EvenementDeplacement(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()),
								simulateur.getData().getRobotbyId(1),
								Direction.EST, simulateur));
		simulateur.addEvenement(new EvenementDeplacement(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()),
								simulateur.getData().getRobotbyId(1),
								Direction.EST, simulateur));
		simulateur.addEvenement(new EvenementEteindreFeu(new Date(simulateur.getData().getRobotbyId(1).getDernierEvent().getDate()), 
								simulateur.getData().getRobotbyId(1),
								simulateur.getData().getIncendiebyId(4), simulateur));*/
	}

}
