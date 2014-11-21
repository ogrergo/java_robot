package evenement;

import java.util.HashSet;
import java.util.Set;

import strategie.Strategie;
import donnees.Incendie;
import donnees.Robot;

/**
 * <b><code>ManagerResolution</code> est la classe permettant d'�teindre tous les incendies
 * Cette classe h�rite de Manager</b>
 * <p>
 * Un ManagerResolution est caract�ris� par :
 * <ul>
 * <li>un ensemble d'incendies deja g�r�</li>
 * </ul>
 * </p>
 *  
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class ManagerResolution extends Manager {

	/**
	 * Ensemble d'incendies qui ont deja �t� g�r�s
	 */
	Set<Incendie> managed = new HashSet<Incendie>();

	/**
	 * Fonction qui permet d'�teindre tous les incendies de la carte en associant � chacun d'eux un robot 
	 */
	@Override
	public void manage() {
		//Pour tous les incendies de notre simulation
		for(Incendie inc : simulateur.getData().getIncendies()) {
			//on veut seulement ceux pas traiter
			if(managed.contains(inc))
				continue;

			Strategie best = null;
			Strategie n = null;
			double cout = Double.MAX_VALUE;
			double cout_n;
			Robot target = null;
			//on veut le robot qui va eteindre cette incendie le plus rapidemant possible
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
			//on ajoute l'incendie aux incendie traiter et on execute la strategie.
			managed.add(inc);
			target.doStrategie(best, simulateur);
		}

	}

}
