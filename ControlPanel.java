import java.util.Scanner;

public class ControlPanel {
	
	private Scanner scanner;
	
	public void start(SpaceShip ship){
		ship.showOptions();
		scanner.nextInt();
		
		
	}

	public ControlPanel() {
		super();
		this.scanner = new Scanner(System.in);
	}
	
	public ControlPanel(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	

}

