package strategie;

import java.util.ArrayList;
import java.util.List;

public class Strategie {
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	
	public void addAction(Action a) {
		actions.add(a);
	}
	
	public void addAction(List<? extends Action> a) {
		actions.addAll(a);
	}
	
	public int getCout() {
		int res = 0;
		for(Action a : actions) {
			res += a.getCout();
		}
		return res;
	}

	public int getNbActions() {
		return actions.size();
	}

	public Action getAction(int i) {
		return actions.get(i);
	}

}
