package donnees;

public class RobotFactory {
	public static Robot createRobot(RobotType type, Case c, Carte carte) {
		Robot robot = null;
		switch(type) {
			case DRONE: robot = new RobotDrone(carte); break;
			case ROUES: robot = new RobotRoues(carte); break;
			case PATTES:robot = new RobotPattes(carte);break;
			case CHENILLES:robot = new RobotChenilles(carte);break;
		}
		
		robot.setPosition(c);
		return robot;
	}
}
