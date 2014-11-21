package evenement;

import strategie.ActionMove;
import strategie.ActionRemplissage;
import strategie.ActionVidage;
import strategie.Strategie;
import donnees.Direction;
import donnees.InvalidCaseException;


public class ManagerScenario1 extends Manager {
	boolean first_time = true;

	@Override
	public void manage() {
		
		if(!first_time)
			return;
		
		first_time = false; 
		Strategie str = new Strategie();
		try {
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
					simulateur.getData().getRobotbyId(1),
					simulateur.getData().getRobotbyId(1).getCase(),
					simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(1).getCase(),Direction.NORD)), Direction.NORD));
		
			str.addAction(new ActionVidage(simulateur.getData().getRobotbyId(1).getEauTempsVidage() * 50, 50));
			str.addAction(new ActionMove(
							simulateur.getData().getCarte().tempsDeplacement(
							simulateur.getData().getRobotbyId(1),
							simulateur.getData().getRobotbyId(1).getCase(),
							simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(1).getCase(),Direction.OUEST))
					, Direction.OUEST));
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
					simulateur.getData().getRobotbyId(1),
					simulateur.getData().getRobotbyId(1).getCase(),
					simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(1).getCase(),Direction.OUEST))
			, Direction.OUEST));
			str.addAction(new ActionRemplissage(simulateur.getData().getRobotbyId(1).getEauTempsRemplissage()));
			str.addAction(new ActionMove(
					simulateur.getData().getCarte().tempsDeplacement(
					simulateur.getData().getRobotbyId(1),
					simulateur.getData().getRobotbyId(1).getCase(),
					simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(1).getCase(),Direction.EST))
			, Direction.EST));
	str.addAction(new ActionMove(
			simulateur.getData().getCarte().tempsDeplacement(
			simulateur.getData().getRobotbyId(1),
			simulateur.getData().getRobotbyId(1).getCase(),
			simulateur.getData().getCarte().getCase(simulateur.getData().getRobotbyId(1).getCase(),Direction.EST))
	, Direction.EST));
			str.addAction(new ActionVidage(simulateur.getData().getRobotbyId(1).getEauTempsVidage() * 50, 50));
			simulateur.getData().getRobotbyId(1).doStrategie(str, simulateur);

		} catch (InvalidCaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
