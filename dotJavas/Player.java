package golf3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player {

	private String name;
	private double playPower, playConsist, playPutting;
	private Club [] clubs;
	public static double x, y, xVelocity, yVelocity;
	private int money, gravHamCharges, windWakCharges, flattenerCharges;
	
	public Player() {
		name = "joebiden";
		playPower = 5; playConsist = 5;
		playPutting = 5;
		clubs = new Club[6];
		x = 0; y = 549;
		money = 100;
		gravHamCharges = 1; windWakCharges = 2; flattenerCharges = 3;

	}
	
	public Player(String n, double pow, double con, double putt, int money,
			int grav, int wind, int flat) {
		name = n; playPower = pow; playConsist = con;
		playPutting = putt;
		clubs = new Club[0];
		addAllClubs();
		this.money = money; this.windWakCharges = wind; 
		this.gravHamCharges = grav; this.flattenerCharges = flat;
		
	}
	public String getName() {
		return name;
	}
	public Club[] getClubs() {
		return clubs;
	}
	public double getPower() {
		return playPower;
	}
	public double getConsist() {
		return playConsist;
	}
	public double getPutting() {
		return playPutting;
	}
	public void addPower(int x) {
		playPower+=x;
	}
	public void addConsist(int x) {
		playConsist+=x;
	}
	public void addPutting(int x) {
		playPutting += x;
	}
	public void addAllClubs() {
		Club[] c = new Club[6];
		c[0] = Play.Driver;
    	c[1] = Play.Wood3;
    	c[2] = Play.Iron5;
    	c[3] = Play.Iron7;
    	c[4] = Play.PW;
    	c[5] = Play.SW;
    	clubs = c;
	}
    
	public void savePlayer() {
		try {
		PrintWriter outFile = new PrintWriter(new File("playerdata.txt"));
		outFile.println(this.name);
		outFile.println(playPower);
		outFile.println(playConsist);
		outFile.println(playPutting);
		outFile.println(money);
		outFile.println(gravHamCharges);
		outFile.println(windWakCharges);
		outFile.println(flattenerCharges);
		outFile.close();
		}
		catch (FileNotFoundException e) {
			Play.outputPrint("Something went wrong saving player");
		}
		
	}
	public Player(String name) {
		try {
		File fileName = new File("playerdata.txt");
		Scanner inFile = new Scanner(fileName);
		
		while (inFile.hasNext()) {
			this.name = inFile.next();
			this.playPower = inFile.nextDouble();
			this.playConsist = inFile.nextDouble();
			this.playPutting = inFile.nextDouble();
			this.money = inFile.nextInt();
			this.gravHamCharges = inFile.nextInt();
			this.windWakCharges = inFile.nextInt();
			this.flattenerCharges = inFile.nextInt();
					
		}
		inFile.close();
		}
		catch (FileNotFoundException e) {
			Play.outputPrint("Can't continue with no player");
			this.name = "null";
		}
		addAllClubs();
		Play.gravHam.setCharges(gravHamCharges);
		Play.windWak.setCharges(windWakCharges);
		Play.flattener.setCharges(flattenerCharges);
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int money) {
		this.money += money;
	}

	public int getGravHamCharges() {
		return gravHamCharges;
	}

	public void setGravHamCharges(int gravHamCharges) {
		this.gravHamCharges = gravHamCharges;
	}

	public int getWindWakCharges() {
		return windWakCharges;
	}

	public void setWindWakCharges(int windWakCharges) {
		this.windWakCharges = windWakCharges;
	}

	public int getFlattenerCharges() {
		return flattenerCharges;
	}

	public void setFlattenerCharges(int flattenerCharges) {
		this.flattenerCharges = flattenerCharges;
	}
}
