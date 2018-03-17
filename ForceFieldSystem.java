
public class ForceFieldSystem extends SpaceShipSystem {

	public ForceFieldSystem(SpaceShip ship) {
		this.setName("Force Field System");
		this.setPowerRequired(30);
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
		this.setAttackSystem(false);
		this.setAtackPower(0);
		this.setDefensePower(10);
	}

}
