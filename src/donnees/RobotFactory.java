package donnees;

/**
 * <b><code>RobotFactory</code> est la classe "fabricant" les robots.</b>
 * <p>
 * 
 * RobotFactory crée les robots en fonction de leur type.
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class RobotFactory {
	public static Robot createRobot(RobotType type, Case c, Carte carte) {
		Robot robot = null;
		switch(type) {
			case DRONE: robot = new RobotDrone(c); break;
			case ROUES: robot = new RobotRoues(c); break;
			case PATTES:robot = new RobotPattes(c);break;
			case CHENILLES:robot = new RobotChenilles(c);break;
		}
		return robot;
	}
}
