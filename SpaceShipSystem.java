import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class SpaceShipSystem {
	
	private Integer powerRequired;
	private Integer missilesRequired;
	private Integer robotsRequired;
	private Integer timeRequired;
	private Integer counter;
	private Integer power;
	private SpaceShipItem item;
	private Integer health;
	private String name;
	private String status = "stoped";
	private Integer load;
	private SpaceShip ship;
	private Integer robot;
	private Integer missiles;
	private SpaceShipRoom target;
	private boolean attackSystem;
	private Integer atackPower;
	private Integer defensePower;
	
	
	
	public SpaceShipItem getItem() {
		return item;
		
	}
	public void setItem(SpaceShipItem item) {
		this.item = item;
	}
	public Integer getPowerRequired() {
		return powerRequired;
	}
	public void setPowerRequired(Integer powerRequired) {
		this.powerRequired = powerRequired;
	}
	
	public Integer getMissilesRequired() {
		return missilesRequired;
	}
	public void setMissilesRequired(Integer missilesRequired) {
		this.missilesRequired = missilesRequired;
	}
	public Integer getRobotsRequired() {
		return robotsRequired;
	}
	public void setRobotsRequired(Integer robotsRequired) {
		this.robotsRequired = robotsRequired;
	}
	public Integer getTimeRequired() {
		return timeRequired;
	}
	public void setTimeRequired(Integer timeRequired) {
		this.timeRequired = timeRequired;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer status) {
		this.health = status;
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLoad() {
		return load;
	}
	public void setLoad(Integer load) {
		this.load = load;
	}
	
	public SpaceShip getShip() {
		return ship;
	}
	public void setShip(SpaceShip ship) {
		this.ship = ship;
	}
	public Integer getRobot() {
		return robot;
	}
	public void setRobot(Integer robot) {
		this.robot = robot;
	}
	public Integer getMissiles() {
		return missiles;
	}
	public void setMissiles(Integer missiles) {
		this.missiles = missiles;
	}
	public SpaceShipRoom getTarget() {
		return target;
	}
	public void setTarget(SpaceShipRoom target) {
		this.target = target;
	}
	public boolean isAttackSystem() {
		return attackSystem;
	}
	public void setAttackSystem(boolean attackSystem) {
		this.attackSystem = attackSystem;
	}
	public Integer getAtackPower() {
		return atackPower;
	}
	public void setAtackPower(Integer atackPower) {
		this.atackPower = atackPower;
	}
	public Integer getDefensePower() {
		if (load>=100){
			return defensePower;
		}
		return 0;
	}
	public void setDefensePower(Integer defensePower) {
		this.defensePower = defensePower;
	}
	public void showSystemOptions() {
		if (status=="stoped"){
			initialOptions();
		}else if (this.getLoad()>0 && this.getLoad()<100){
			System.out.println("Loading..." + this.getLoad() +"%");
			readyOptions();
		}else{
			readyOptions();
		}		
	}
	
	public void showOverallStatus() {
		System.out.println(this.getName()+": S: "+status+", H: "+this.getHealth()+", L: "+this.getLoad()+", P: "+
				this.getPower()+", CT: "+this.getTimeRequired()+", PR: "+this.getPowerRequired()
				+", MR: "+this.getMissilesRequired()+", M: "+this.getMissiles()
				+", AP: "+this.getAtackPower()+", D: "+this.getDefensePower());
	}
	
	public boolean isReadyToStart() {
		boolean r = true;
		if(this.getHealth()<100){
			System.out.println(this.getName()+" damaged!");
			r = false;
		}else if(this.getShip().getPower()<this.getPowerRequired()){
			System.out.println("Insuficient power!");
			r = false;
		}else if(this.getShip().getRobots()<this.getRobotsRequired()){
			System.out.println("Insuficient robots!");
			r = false;
		}else if(this.getShip().getMissiles()<this.getMissilesRequired()){
			System.out.println("Insuficient missiles!");
			r = false;
		}
		return r;
		
	}
	
	void readyOptions() {
		int option = -1;
		while(!(option==0) && status=="started"){
			System.out.println(this.getName()+" menu:");
			System.out.println("1 - Stop System");
			if (attackSystem){
				System.out.println("2 - Select Target");
				System.out.println("3 - Fire");
			}
			System.out.print(">");
			option = this.getShip().readCommand();
			if (option==1) {
				this.stopSystem();
				break;
			}else if (option==2 && attackSystem){
				if(this.getShip().getBattleMode()){
					this.selectTarget();
				}else{
					System.out.println("No enemy nearby");
				}
				
			}else if (option==3 && load<100){
					System.out.println("System not ready");
					break;
			}else if (option==3 && attackSystem){
				if(this.isTargetSelected()){
					this.fire();
					break;
				}else{
					System.out.println("Select a target first");
					this.selectTarget();
					break;
				}
			}
		}
	}
	private boolean isTargetSelected() {
		
		return !(this.getTarget() == null);
	}
	private void fire() {
		int finalAtackPower = atackPower - ship.getEnemy().getDefence();
		if (finalAtackPower<0){
			finalAtackPower=0;
		}
		ship.setMissiles(ship.getMissiles()-missilesRequired);
		ship.setRobots(ship.getRobots()-robotsRequired);
		ship.getEnemy().setHull(ship.getEnemy().getHull() - finalAtackPower);
		target.getSystem().setHealth(target.getSystem().getHealth()-finalAtackPower);
		
		if(isReadyToStart()==false){
			stopSystem();
		}else{
			setLoad(0);
		}
		
		
	}
	private void selectTarget() {
		int option = -1;
		while (!(option==0)) {
			System.out.println("Target list:");
			for (SpaceShipRoom room : this.getShip().getEnemy().getRoomList()) {
				System.out.println(this.getShip().getEnemy().getRoomList().indexOf(room)+1+" - "+ room.getSystem().getName());
			}
			if (isTargetSelected()){
				System.out.println("Current target: ["+target.getSystem().getName()+"]");
			}
			else{
				System.out.println("Current target: []");
			}
			option = this.getShip().readCommand();
			if (option > 0 && option <= this.getShip().getEnemy().getRoomList().size()){
				this.setTarget(this.getShip().getEnemy().getRoomList().get(option-1));
				break;
			}
		}
		
	}
	void initialOptions() {
		int option = -1;
		while(!(option==0) && status=="stoped"){
			System.out.println(this.getName()+" menu:");
			System.out.println("1 - START");
			System.out.print(">");
			option = this.getShip().readCommand();
			if (option==1 && this.isReadyToStart()) {
				this.startSystem();
				break;
			}
		}
		
	}
	
	
	void startSystem() {
			status="started";
			power=this.getPowerRequired();
			this.getShip().setPower(this.getShip().getPower() - this.getPowerRequired());
			System.out.println(this.getName()+" started.");
			
		
		
	}
	
	
	void stopSystem() {
			status="stoped";
			this.setPower(0);
			this.getShip().setPower(this.getShip().getPower() + this.getPowerRequired());
			this.setLoad(0);
			System.out.println(this.getName()+" stoped.");
		
	}
	
	void nextTurn(){
		if (status == "started"){
			load = load + (100 / timeRequired);
			if(load>100){
				load=100;
			}
		}
	}

}
