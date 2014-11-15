package donnees;

public class RobotFactory {
	public static Robot createRobot(RobotType type, Case c) {
		Robot robot = null;
		switch(type) {
			case DRONE: robot = new RobotDrone(); break;
			case ROUES: robot = new RobotRoues(); break;
			case PATTES:robot = new RobotPattes();break;
			case CHENILLES:robot = new RobotChenilles();break;
		}
		
		robot.setPosition(c);
		return robot;
	}
}
