import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws IOException {
		
		
		SpaceShip falcon = new SpaceShip();
		falcon.setName("Falcon");
		SpaceShip falconEnemy = new SpaceShip();
		falconEnemy.setName("Enemy");
		SpaceShipRoom room1 = new SpaceShipRoom();
		SpaceShipRoom room2 = new SpaceShipRoom();
		SpaceShipRoom room3 = new SpaceShipRoom();
		SpaceShipRoom room4 = new SpaceShipRoom();
		SpaceShipRoom eRoom1 = new SpaceShipRoom();
		SpaceShipRoom eRoom2 = new SpaceShipRoom();
		SpaceShipRoom eRoom3 = new SpaceShipRoom();
		SpaceShipRoom eRoom4 = new SpaceShipRoom();
		OxygemSystem oxigemSystem = new OxygemSystem(falcon);
		LaserSystems laserSystem = new LaserSystems(falcon);
		MissileSystem missileSystem = new MissileSystem(falcon);
		ForceFieldSystem forceFieldSystem = new ForceFieldSystem(falcon);
		OxygemSystem eOxigemSystem = new OxygemSystem(falconEnemy);
		LaserSystems eLaserSystem = new LaserSystems(falconEnemy);
		MissileSystem eMissileSystem = new MissileSystem(falconEnemy);
		ForceFieldSystem eForceFieldSystem = new ForceFieldSystem(falconEnemy);
		room1.setSystem(oxigemSystem);
		room2.setSystem(laserSystem);
		room3.setSystem(missileSystem);
		room4.setSystem(forceFieldSystem);
		falcon.roomList.add(room1);
		falcon.roomList.add(room2);
		falcon.roomList.add(room3);
		falcon.roomList.add(room4);
		eRoom1.setSystem(eOxigemSystem);
		eRoom2.setSystem(eLaserSystem);
		eRoom3.setSystem(eMissileSystem);
		eRoom4.setSystem(eForceFieldSystem);
		falconEnemy.roomList.add(eRoom1);
		falconEnemy.roomList.add(eRoom2);
		falconEnemy.roomList.add(eRoom3);
		falconEnemy.roomList.add(eRoom4);
		
		falcon.setEnemy(falconEnemy);
		for (SpaceShipRoom r : falconEnemy.getRoomList()) {
			r.getSystem().startSystem();
		}
		int turn=0;
		while(turn<10){
			turn++;
			falcon.nextTurn();
			falconEnemy.nextTurn();
			System.out.println("Turn: "+turn);
			falcon.showOverallStatus();
			falconEnemy.showOverallStatus();
			falcon.showOptions();
			
		}
		falcon.getScanner().close();
		falconEnemy.getScanner().close();

		

	}
	
	

}
