import java.util.ArrayList;
import java.util.Scanner;

public class SpaceShip {
	
	private String name;
	private Integer fuel;
	private Integer hull;
	private Integer power;
	private Integer robots;
	private Integer missiles;
	private Boolean battleMode = false;
	private Scanner scanner;
	private SpaceShip enemy;
	public ArrayList <SpaceShipRoom> roomList = new ArrayList<SpaceShipRoom>();
	public ArrayList <Person> crew = new ArrayList<Person>();
	
	
	public Integer getFuel() {
		return fuel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFuel(Integer fuel) {
		this.fuel = fuel;
	}
	public Integer getHull() {
		return hull;
	}
	public void setHull(Integer hull) {
		this.hull = hull;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getRobots() {
		return robots;
	}
	public void setRobots(Integer robots) {
		this.robots = robots;
	}
	public Integer getMissiles() {
		return missiles;
	}
	public void setMissiles(Integer missiles) {
		this.missiles = missiles;
	}
	public Boolean getBattleMode() {
		return !(enemy == null);
	}
	public void setBattleMode(Boolean battleMode) {
		this.battleMode = battleMode;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	


	public SpaceShip getEnemy() {
		return enemy;
	}
	public void setEnemy(SpaceShip enemy) {
		this.enemy = enemy;
	}
	public ArrayList<SpaceShipRoom> getRoomList() {
		return roomList;
	}
	public void setRoomList(ArrayList<SpaceShipRoom> roomList) {
		this.roomList = roomList;
	}
	public SpaceShip() {
		super();
		this.fuel = 50;
		this.hull = 100;
		this.power = 200;
		this.scanner = new Scanner(System.in);
		this.robots = 0;
		this.missiles = 10;
	}
	
	public SpaceShip(Integer fuel, Integer hull, Integer power, Scanner scanner) {
		super();
		this.fuel = fuel;
		this.hull = hull;
		this.power = power;
		this.scanner = scanner;
	}
	
	
	public void showOptions(){
		int option = -1;
		while (!(option == 0)){
			System.out.println("Main menu:");
			System.out.println("1 - Systems");
			System.out.println("2 - Crew");
			System.out.print(">");
			option = readCommand();
			if (option == 1){
				showSystems();
				option = -1;
			}else if (option == 2){
				showCrewOptions();
				option = -1;
			}else if (option == 9){
				showOverallStatus();
				option = -1;
			}
		}
		
		
		
	}
	private void showCrewOptions() {
		int option = -1;
		while (!(option==0)){
			printCrewMessage();
			option = readCommand();
			if (option <= crew.size()+1 && option > 0){
				crew.get(option).personOptions();
			}
		}
	}
	private void printCrewMessage() {
		System.out.println("Crew:");
		for(Person person : crew){
			System.out.println(crew.indexOf(person) + 1 + " - " + person.getName());
		}
		
	}
	private void showSystems() {
		int option = -1;
		while (!(option==0)){
			printSystemMessage();
			option = readCommand();
			if (option <= roomList.size() && option > 0){
				roomList.get(option-1).getSystem().showSystemOptions();
			}
		}
		
	}
	private void printSystemMessage() {
		System.out.println("Systems:");
		for(SpaceShipRoom room : roomList){
			System.out.println(roomList.indexOf(room)+ 1 + " - " + room.getSystem().getName());
		}
		System.out.print(">");
	}
	public int readCommand() {
		int in = -1;
		if (scanner.hasNextInt()){
			in = scanner.nextInt();
		}
		return in;
		
	}
	public void showOverallStatus(){
		System.out.println("------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("SHIP("+name+"): Hull="+hull+", Fuel="+fuel+", Power="+power+", Missiles="+missiles);
		System.out.println("------------------------------------------------------------------------");
		for (SpaceShipRoom room : roomList) {
			room.getSystem().showOverallStatus();
			System.out.println("------------------------------------------------------------------------");
		}
		
	}
	public void nextTurn(){
		for (SpaceShipRoom room : roomList) {
			room.getSystem().nextTurn();
		}
	}

	public int getDefence(){
		int defense=0;
		for(SpaceShipRoom room: roomList){
			defense = defense + room.getSystem().getDefensePower();
		}
		
		return defense;
	}
}
