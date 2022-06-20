package golf3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Has static main method
 *
 * @author Nathan Edmondson
 * @version 7/19/21
 */

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import golf3.Tool.GravityHammer;
import golf3.Tool.GreenFlattener;
import golf3.Tool.WindWaker;

public class Play extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static MusicPlayer DJRoomba;

	public static void main(String[] args) {
		// Graphics
		p.graphicsThread();
		outputBox();

		currentPlayer = new Player("playerdata.txt");
		if(currentPlayer.getName() == "null") 
			MyPanel.currentTextBox = new TextBox("Welcome new Player!", "Click new player to make your golfer!");
		
		// Music
		try {
			Play.DJRoomba = new MusicPlayer("LinearGolf.wav");
			Play.DJRoomba.playLooped();
		} catch (Exception muse) {
		}
		
		
		// Game
		tools = new Tool[] { gravHam, windWak, flattener };
		while (true) {
			Course c = currentCourse;
			currentCourse.playCourse();
			if (c.equals(currentCourse) && 
				(c.getName().equals("Driving Range") 
				|| c.getName().equals("Putting Practice")))
				currentCourse = new Course();
		}
	}

	public static void outputBox() {
		obf.setSize(300, 400);

		ta.setBackground(Color.lightGray);
		ta.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		ta.setEditable(false);
		obf.add(ta);
		obf.setLocation(1200, 0);
		obf.setVisible(true);
		outputPrint("\nHello!\n" + "Click, drag, and release to shoot\n" + "Use the LR arrow keys to switch clubs\n"
				+ "When the ball lands on the green\n" + "you start putting\n" + "Click and drag in the opposite\n"
				+ "direction you want to putt\n" + "Use number keys to select a tool\n" + "press f to use a tool\n");
	}

	public static void outputPrint(String s) {
		ta.append(s + "\n");
	}

	public void graphicsThread() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				p.createAndShowGUI();
			}
		});
	}

	public void playSoundEffect(String filePath) {
		try {
			MusicPlayer mp = new MusicPlayer(filePath);
			mp.play();
		} catch (Exception e1) { // System.out.println("Caught exception in sound effect");

		}
	}

	public void createAndShowGUI() {

		// f.setCursor(getToolkit().createCustomCursor(club, new Point(2,22), ""));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1200, 800);
		menuItemMusic.addActionListener(p);
		menuItemReturn.addActionListener(p);
		menuItemExit.addActionListener(p);
		menuItemOutput.addActionListener(p);
		jm.add(menuItemMusic);
		jm.add(menuItemReturn);
		jm.add(menuItemExit);
		jm.add(menuItemOutput);
		scard.addActionListener(p);
		jmb.add(jm);
		jmb.add(scard);
		f.add(new MyPanel());
		f.add(new Mouse());
		f.setFocusable(true);
		f.setJMenuBar(jmb);
		f.pack();
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equalsIgnoreCase("Toggle Music") && !Play.musicOn) {
			musicOn = true;
			DJRoomba.playLooped();

		} else if (e.getActionCommand().equalsIgnoreCase("Toggle Music")) {
			musicOn = false;
			try {
				DJRoomba.stop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Return to Menu")) {
			graphicsStage = 0;

		} else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			System.out.println("Killing");
			currentPlayer.savePlayer();
			System.exit(0);
		} else if (e.getActionCommand().equalsIgnoreCase("Toggle Output")) {
			showOutputBox = !showOutputBox;
			obf.setVisible(showOutputBox);
			/*
			 * MyPanel.output.setLocation(1199, 0);
			 * MyPanel.output.setBackground(Color.lightGray); MyPanel.output.setFont(new
			 * Font("TimesRoman", Font.PLAIN, 20));
			 */
		} else if (e.getActionCommand().equalsIgnoreCase("Scorecard")) {
			if (Play.graphicsStage != 3)
				Play.graphicsStage = 3;
			else
				Play.graphicsStage = 1;
		}

	}

	// GRAPHICS STUFF
	public static int graphicsStage = 0;
	public static Image sky = Toolkit.getDefaultToolkit().getImage("sky.jpg");
	public static Image menuPic = Toolkit.getDefaultToolkit().getImage("lgmenu.jpg");
	public static Image newPlayerMenu = Toolkit.getDefaultToolkit().getImage("newplayermenu.jpg");
	public static Image club = Toolkit.getDefaultToolkit().getImage("clubpng.png");
	public static Image scorecard = Toolkit.getDefaultToolkit().getImage("scorecard.jpg");
	public static Image blankMenu = Toolkit.getDefaultToolkit().getImage("blankmenu.jpg");
	public static JFrame f = new JFrame();
	public static JFrame obf = new JFrame();
	public static TextArea ta = new TextArea("", 15, 30);
	public static JFrame ibf = new JFrame();
	public static JLabel l = new JLabel("INFO");
	public static JMenuBar jmb = new JMenuBar();
	public static JMenu jm = new JMenu("Tools");
	public static JMenuItem scard = new JMenuItem("Scorecard");
	public static JMenuItem menuItemReturn = new JMenuItem("Return to Menu");
	public static JMenuItem menuItemMusic = new JMenuItem("Toggle Music");
	public static JMenuItem menuItemExit = new JMenuItem("Exit");
	public static JMenuItem menuItemOutput = new JMenuItem("Toggle Output");
	public static boolean showOutputBox = true;

	// MUSIC STUFF
	public static boolean musicOn = true;

	public static Ball ball = new Ball(0, 0);
	public static Play p = new Play();
	public static Player joe = new Player();
	public static Player currentPlayer = joe;
	public static String toName = "name";
	public static TerrainChunk flat300 = new TerrainChunk(0, 299, 0, 299, 600, 600, 800, 800);
	public static TerrainChunk pinLeft = new TerrainChunk(299, 300, 299, 300, 600, 620, 800, 800);
	public static TerrainChunk pinRight = new TerrainChunk(325, 326, 325, 326, 620, 600, 800, 800);
	public static TerrainChunk pin1 = new TerrainChunk(300, 325, 600);
	public static TerrainChunk h1c3 = new TerrainChunk(326, 1200, 326, 1200, 600, 600, 800, 800);
	public static TerrainChunk[] h1chunks = new TerrainChunk[] { flat300, pinLeft, pin1, pinRight, h1c3 };
	public static Hole h1 = new Hole(312.5, 0, h1chunks);
	static Scanner in = new Scanner(System.in);
	public static boolean firstClick = true;
	public static Ball clickOne = new Ball(0, 0);
	public static Ball clickTwo = new Ball(0, 0);
	public static boolean inGame = true;
	public static boolean inSand = false;

	//
	public static int holesToPlay = 18;
	public static Course randoCourse = new Course();
	public static Course currentCourse = randoCourse;
	public static double pow = 0;
	public static boolean go = false;
	public static double ang = 0;
	public static boolean backwards = false;
	public static boolean backspin = false;
	public static boolean putting = false;
	public static boolean exitCourse = false;

	// BASIC CLUBS
	public static Club Driver = new Club(.55, 80, "Driver", 530);
	public static Club Iron7 = new Club(.9, 70, "7 Iron", 390);
	public static Club PW = new Club(1.1, 65, "Pitching Wedge", 325);
	// public static Club Putter = new Club(.3, 30,"Putter", 100);
	// OTHER CLUBS
	public static Club Wood3 = new Club(.65, 75, "3 Wood", 500);
	public static Club Iron5 = new Club(.8, 70, "5 Iron", 405);
	public static Club SW = new Club(1.2, 65, "Sand Wedge", 270);
	public static Club currentClub = Driver;
	public static int currentClubID = 0;

	// Tools
	public static boolean showTools = true;
	public static Tool tool = new Tool();
	public static Tool currentTool = tool;
	public static GravityHammer gravHam = tool.getHammer();
	public static WindWaker windWak = tool.getWaker();
	public static GreenFlattener flattener = tool.getFlat();
	public static Tool[] tools;

	public static Hole current = h1;
	
	//For the timed tournament
	public static long startTime, endTime;
}
