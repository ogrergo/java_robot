package evenement;

//import java.util.Collection;
import java.util.Iterator;

import strategie.Strategie;
import donnees.Incendie;
import donnees.Robot;
import donnees.State;


public class ManagerTestGenerationScenario extends Manager {

	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(60);
		/*Strategie str = simulateur.getData().getRobotbyId(0).getBestStrategie(
				simulateur.getData().getIncendiebyId(0), 
				simulateur.getData());
		Strategie str1 = simulateur.getData().getRobotbyId(1).getBestStrategie(
				simulateur.getData().getIncendiebyId(1), 
				simulateur.getData());
		Strategie str2 = simulateur.getData().getRobotbyId(2).getBestStrategie(
				simulateur.getData().getIncendiebyId(2), 
				simulateur.getData());
		simulateur.getData().getRobotbyId(0).doStrategie(str, simulateur);
		simulateur.getData().getRobotbyId(1).doStrategie(str1, simulateur);
		simulateur.getData().getRobotbyId(2).doStrategie(str2, simulateur);
		 */
		//A DEBUGGUER

		Strategie str;
		Iterator<Incendie> incendies = simulateur.getData().getIncendies().iterator();
		System.out.println("NbrIncendies : " + simulateur.getData().getIncendies().size());
		Incendie incendiecour ;
		int compteur = 0;
		while (incendies.hasNext()){
			Iterator<Robot> itr = simulateur.getData().getRobots().iterator();
			//boolean continu = true;
			while (itr.hasNext() && incendies.hasNext()) {
				incendiecour = incendies.next();
				
				System.out.println(" incendie n° : " + compteur + " : "+incendiecour.getLitreEau());
				Robot robotcourant = itr.next();
				System.out.println(robotcourant.getType() + " " + robotcourant.getState().toString());
				str = robotcourant.getBestStrategie(incendiecour,simulateur.getData());
				if (robotcourant.getState() == State.AVAILABLE && (str != null)) {
					// continu = false;
					robotcourant.doStrategie(str, simulateur);
					
					compteur ++;
				}
			}
		}

	}
}


