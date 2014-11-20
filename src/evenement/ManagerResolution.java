package evenement;

import java.util.HashSet;
import java.util.Set;

import strategie.Strategie;
import donnees.Incendie;
import donnees.Robot;
import donnees.State;

public class ManagerResolution extends Manager {

	Set<Incendie> managed = new HashSet<Incendie>();

	@Override
	public void manage() throws ExecutionException {
		simulateur.setSimulationStepDuration(1);

		for(Incendie inc : simulateur.getData().getIncendies()) {
			if(managed.contains(inc))
				continue;

			Strategie best = null;
			Strategie n = null;
			double cout = Double.MAX_VALUE;
			double cout_n;
			Robot target = null;
			for(Robot r : simulateur.getData().getRobots()) {
				if(!r.isAvailable())
					continue;
				
				n = r.getBestStrategie(inc, simulateur.getData());
				if(n == null)
					continue;
				
				cout_n = n.getCout();
				if( cout_n < cout) {
					cout = cout_n;
					best = n;
					target = r;
				}
			}
			
			if(target == null)
				continue;
			
			managed.add(inc);
			target.doStrategie(best, simulateur);
		}

	}

}
