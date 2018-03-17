
public class MissileSystem extends SpaceShipSystem {

	public MissileSystem(SpaceShip ship) {
		this.setName("Missile 1 System");
		this.setPowerRequired(10);
		this.setLoad(0);
		this.setCounter(0);
		this.setMissilesRequired(1);
		this.setPower(0);
		this.setRobotsRequired(0);
		this.setHealth(100);
		this.setTimeRequired(3);
		this.setShip(ship);
		this.setRobot(0);
		this.setMissiles(0);
		this.setAttackSystem(true);
		this.setAtackPower(20);
		this.setDefensePower(0);
	}

}
