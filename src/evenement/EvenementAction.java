package evenement;

import java.util.Set;

import strategie.Action;
import donnees.InvalidCaseException;
import donnees.Robot;
import donnees.WorldElement;

public class EvenementAction extends Evenement {

	private Action action;
	private Robot robot;
	
	public EvenementAction(Date date, Simulateur s, Action a, Robot r) {
		super(date, s);
		action = a;
		robot = r;
	}

	@Override
	public Set<WorldElement> execute(Set<WorldElement> s)
			throws ExecutionException {
		
		try {
			robot.doAction(action, simulateur);
			s.add(robot);
		} catch (InvalidCaseException e) {
			throw new ExecutionException(this, e.getMessage());
		}
		return s;
	}

}
