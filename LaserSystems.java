
public class LaserSystems extends SpaceShipSystem {

	public LaserSystems(SpaceShip ship) {
		this.setName("Laser 1 System");
		this.setPowerRequired(25);
		this.setLoad(0);
		this.setCounter(0);
		this.setMissilesRequired(0);
		this.setPower(0);
		this.setRobotsRequired(0);
		this.setHealth(100);
		this.setTimeRequired(2);
		this.setShip(ship);
		this.setRobot(0);
		this.setMissiles(0);
		this.setAttackSystem(true);
		this.setAtackPower(15);
		this.setDefensePower(0);
	}

}
