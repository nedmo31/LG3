package golf3;

/**
 * Write a description of class Course here.
 *
 * @author Nathan Edmondson
 * @version (a version number or a date)
 */
public class Course {
	// instance variables - replace the example below with your own
	public static int halfPar, fullPar, halfScore, fullScore;
	private Hole[] holes;
	private String name;
	private int par, strokeOut;
	private boolean showScorecard;

	/**
	 * Constructor for objects of class Course
	 */
	public Course(String name, int par, Hole a, Hole b, Hole c, Hole d, Hole e, Hole f, Hole g, Hole h, Hole i, Hole j,
			Hole k, Hole l, Hole m, Hole n, Hole o, Hole p, Hole q, Hole r) {
		holes = new Hole[] { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r };
		this.name = name;
		this.par = par;
		pre();
	}

	public Course() {
		this("rando", 72, new Hole(), new Hole(), new Hole(), new Hole(), new Hole(), new Hole(), new Hole(),
				new Hole(), new Hole(), new Hole(), new Hole(), new Hole(), new Hole(), new Hole(), new Hole(),
				new Hole(), new Hole(), new Hole());
		strokeOut = 12;
		showScorecard = true;
	}

	public Course(boolean Tournament) {
		this("Tournament", 72, new Hole(50, 450, 25, 1, 0), new Hole(50, 1000, 80, 1, 2), new Hole(50, 700, -100, 0, 0),
				new Hole(50, 750, -25, 2, 0), new Hole(50, 1050, 5, 0, 1), new Hole(50, 400, -120, 0, 0),
				new Hole(50, 550, 50, 1, 1), new Hole(50, 650, 35, 2, 1), new Hole(50, 1100, 120, 2, 2),
				new Hole(50, 450, 150, 1, 0), new Hole(50, 750, -50, 0, 1), new Hole(50, 750, -15, 1, 1),
				new Hole(50, 600, 0, 0, 0), new Hole(50, 600, 15, 1, 1), new Hole(50, 470, 90, 1, 1),
				new Hole(50, 550, -100, 0, 0), new Hole(50, 850, 35, 1, 1), new Hole(50, 950, 125, 2, 2));
		showScorecard = true;
		strokeOut = 12;
	}

	public Course(String Driving) {
		this("Driving Range", 18, new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300),
				new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300),
				new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300),
				new Hole(300, 300), new Hole(300, 300), new Hole(300, 300), new Hole(300, 300));
		showScorecard = false;
		strokeOut = 1;
	}

	public Course(String Putting, int n) {
		this("Putting Practice", 36, new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50),
				new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50),
				new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50), new Hole(0, 50),
				new Hole(0, 50), new Hole(0, 50));
		showScorecard = true;
		strokeOut = 4;
	}

	public String getName() {
		return name;
	}

	public int getPar() {
		return par;
	}

	public Hole[] getHoles() {
		return holes;
	}

	public int currentHoleNumber() {
		for (int i = 0; i < 18; i++) {
			if (holes[i].equals(Play.current))
				return i;
		}
		return -1;
	}

	public void pre() {
		halfPar = fullPar = fullScore = halfScore = 0;
		for (int u = 0; u < 18; u++) {
			if (u < 9) {
				halfPar += holes[u].getPar();
			}
			fullPar += holes[u].getPar();
		}
	}

	public void playCourse() {
		playCourse(18);
	}

	public void playCourse(int n) {
		int b = 0;
		try {
			System.out.println("Starting course " + this);
			while (b < Play.holesToPlay && !Play.exitCourse) {
				Play.current = holes[b];
				fullScore += holes[b].playHole();
				if (b < 9)
					halfScore += holes[b].getStrokes();
				b++;
			}
			if (showScorecard)
				Play.graphicsStage = 3;
			else
				Play.graphicsStage = 0;

		} catch (GolfException e) {
			System.out.println("Course Exited");
		} finally {
			Play.currentPlayer.savePlayer();
			System.out.println("Done with course" + this);
			Play.exitCourse = false;
		}
	}

	public int getStrokeOut() {
		return strokeOut;
	}

	public String toString() {
		return "" + this.hashCode() + name + " " + strokeOut;
	}
}