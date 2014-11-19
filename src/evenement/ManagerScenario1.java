package evenement;

import strategie.ActionMove;
import strategie.ActionRemplissage;
import strategie.ActionVidage;
import strategie.Strategie;
import donnees.Direction;


public class ManagerScenario1 extends Manager {
	
	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		Strategie str = new Strategie();
		str.addAction(new ActionMove(10, Direction.NORD));
		str.addAction(new ActionVidage(50, 50));
		str.addAction(new ActionMove(100, Direction.OUEST));
		str.addAction(new ActionMove(150, Direction.OUEST));
		str.addAction(new ActionRemplissage(200));
		str.addAction(new ActionMove(250, Direction.EST));
		str.addAction(new ActionMove(300, Direction.EST));
		str.addAction(new ActionVidage(350, 50));
		
		simulateur.getData().getRobotbyId(1).doStrategie(str, simulateur);
	}

}
