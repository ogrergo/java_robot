package evenement;

import donnees.Direction;
import donnees.InvalidCaseException;
import strategie.ActionMove;
import strategie.Strategie;

public class ManagerScenario0 extends Manager {
	boolean first_time = true;
	
	@Override
	public void manage() {
		if(!first_time)
			return;
		
		first_time = false;
		
		try {
			Strategie str = new Strategie();
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
							simulateur.getData().getRobotbyId(0),
							simulateur.getData().getRobotbyId(0).getCase(),
							simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(0).getCase(),Direction.NORD)),
					Direction.NORD));
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
							simulateur.getData().getRobotbyId(0),
							simulateur.getData().getRobotbyId(0).getCase(),
							simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(0).getCase(),Direction.NORD)),
					Direction.NORD));
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
							simulateur.getData().getRobotbyId(0),
							simulateur.getData().getRobotbyId(0).getCase(),
							simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(0).getCase(),Direction.NORD)),
					Direction.NORD));
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
							simulateur.getData().getRobotbyId(0),
							simulateur.getData().getRobotbyId(0).getCase(),
							simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(0).getCase(),Direction.NORD)),
					Direction.NORD));
			simulateur.getData().getRobotbyId(0).doStrategie(str, simulateur);
		} catch (InvalidCaseException e) {
			e.printStackTrace();
		}
	}
}
