package golf3;

/**
 * Ball Object represents the golf ball
 *
 * @author Nathan Edmondson
 * @version 7/19/21
 */
public class Ball {
	public static double gravity = 8.0;
	private double x, y;

	/**
	 * Constructor for objects of class Ball
	 */
	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void hit(double pow, double angle, Club club, Hole hole, boolean backspin)
	/*
	 * A quadratic function will be formed for the first hit and any consecutive
	 * bounce. Each tick the position will be calculated with the function and it
	 * will be updated and the frame will be repainted. Power will be from 0 - 100,
	 * angle will be from 0 - 90 Initial Velocity will be calculated from power and
	 * club choice
	 * 
	 * y = -4.9 * time^2 + vi * Math.sin(angle) * time + ball.getY() x = (-xVelocity
	 * / 25) * time ^ 2 + vi * Math.cos(angle) * time + ball.getX()
	 */
	{
		Play.p.playSoundEffect("ballHit1.wav");
		if (pow > 100)
			pow = 100;
		double v = (Play.currentPlayer.getPower() / 50 + .8) * club.getDist() * pow / 110.0;
		double initialx = Play.ball.getX();
		double initialy = Play.ball.getY();
		double fuckedUp = 4 / Play.currentPlayer.getConsist() * .1 * (1 - (Math.random() * 2));
		angle = Play.currentClub.getAngle();
		if (Play.backwards)
			angle = Math.PI - angle;
		angle += fuckedUp;
		double yVelocity = v * Math.sin(angle);
		double xVelocity = v * Math.cos(angle);
		boolean freefall = true;
		if (Play.inSand && !Play.currentClub.getName().equals("Sand Wedge")) {
			yVelocity /= 2;
			xVelocity /= 2;
		}
		double iyv = yVelocity;
		double ixv = xVelocity;

		double startTime;
		double time, loopTime;
		boolean willBounce = true;
		boolean hit = false;
		double startTime3 = System.nanoTime();
		while (willBounce) {
			hit = false;
			startTime = System.nanoTime();
			while (!hit) {
				time = (System.nanoTime() - startTime) / Math.pow(10, 9) * 8;
				loopTime = (System.nanoTime() - startTime3) / Math.pow(10, 9) * 8;
				startTime3 = System.nanoTime();
				this.setX(xVelocity * loopTime + this.getX());
				this.setY(yVelocity * loopTime + this.getY());
				Play.f.repaint();
				Play.f.repaint();
				xVelocity += Play.current.getWind() * loopTime;
				yVelocity = iyv - gravity * time;
				if (this.getY() <= hole.getYatX(this.getX()) - 1) {
					System.out.println("Something went wrong in Bounce.");
					this.setY(hole.getYatX(this.getX()));
					xVelocity = 0;
					yVelocity = 0;
					freefall = false;
				} else if (this.getY() <= hole.getYatX(this.getX())) {
					hit = true;
					if (backspin)
						v = .35 * Math.sqrt(yVelocity * yVelocity + xVelocity * xVelocity);

					else
						v = .56 * Math.sqrt(yVelocity * yVelocity + xVelocity * xVelocity);

					double va = Math.atan(yVelocity / xVelocity);
					if (xVelocity > 0 && yVelocity < 0) {
						// I think it works
					} else if (xVelocity < 0 && yVelocity < 0) {
						va += (Math.PI);
					} else if (xVelocity > 0 && yVelocity > 0) {

					} else if (xVelocity < 0 && yVelocity > 0) {
						va += (Math.PI);
					}

					double bounceAng = -va + 2 * hole.getAngatX(this.getX());
					if (Play.current.sandCheck(Play.ball.getX(), Play.ball.getY()))
						bounceAng = 0;
					iyv = v * Math.sin(bounceAng);
					ixv = v * Math.cos(bounceAng);
					yVelocity = iyv;
					xVelocity = ixv;
					// System.out.println("Bounced: v ="+v);
					this.setY(hole.getYatX(this.getX()));
					if (v < .05) {
						// System.out.println("Stopping bounce, v ="+v);
						yVelocity = 0.0;
						willBounce = false;
						this.setY(hole.getYatX(this.getX()));
						freefall = false;
					}
				}
				if (hole.waterCheck(this.getX(), this.getY())) {
					Play.outputPrint("Water Hazard!"); // System.out.println("Water Hazard!");
					this.setX(initialx);
					hit = true;
					this.setY(initialy);
					freefall = false;
					willBounce = false;
					xVelocity = 0;
					yVelocity = 0;
				}
				if (Play.ball.getX() > 1200 || Play.ball.getX() < -1) {
					Play.outputPrint("Out of bounds!"); // System.out.println("Out of Bounds!");
					this.setX(initialx);
					hit = true;
					this.setY(initialy);
					freefall = false;
					willBounce = false;
					xVelocity = 0;
					yVelocity = 0;
				}
			}
		}
		startTime = System.nanoTime();

		while ((Math.abs(xVelocity) > .1 || Math.abs(yVelocity) > .1 || freefall)
				&& !Play.current.sandCheck(Play.ball.getX(), Play.ball.getY())) {
			time = (System.nanoTime() - startTime) / Math.pow(10, 9);
			this.setX(xVelocity * time + this.getX());
			xVelocity *= .99;
			if (this.getY() <= hole.getYatX(this.getX()) - 1) {
				System.out.println("Something went wrong in roll");
				this.setY(hole.getYatX(this.getX()));
				xVelocity = 0;
				yVelocity = 0;
				freefall = false;
			}
			if (this.getY() < hole.getYatX(this.getX())) {
				freefall = true;
			} // THIS DOESN'T DO ANYTHING
			if (this.getY() > hole.getYatX(this.getX())) {
				hit = true;
				ixv *= .85;
				yVelocity = 0;
				xVelocity = ixv;
				if (yVelocity < 1.5) {
					yVelocity = 0.0;
					willBounce = false;
					freefall = false;
					// this.setY(hole.getYatX(this.getX()));
				}
			}
			if (freefall) {
				yVelocity = -2.6;
				this.setY(yVelocity * time + this.getY());
			}
			if (hole.waterCheck(Play.ball.getX(), Play.ball.getY())) {
				Play.outputPrint("Water Hazard!"); // System.out.println("Water Hazard!");
				this.setX(initialx);
				hit = true;
				this.setY(initialy);
				freefall = false;
				willBounce = false;
				xVelocity = 0;
				yVelocity = 0;
				if (Play.ball.getX() > 1200 || Play.ball.getX() < -1) {
					Play.outputPrint("Out of bounds!"); // System.out.println("Out of Bounds!");
					this.setX(initialx);
					hit = true;
					this.setY(initialy);
					freefall = false;
					willBounce = false;
					xVelocity = 0;
					yVelocity = 0;
				}
			}
		}
		Play.currentTool.unuse();
	}
	/*
	 * public void putt(double pow, double ang) {
	 * 
	 * double yVelocity = pow * Math.sin( ang ); double xVelocity = pow * Math.cos(
	 * ang ); boolean overHole = false; boolean inHole = false; double startTime =
	 * System.nanoTime();
	 * 
	 * while (((Math.abs(xVelocity) > .5 || Math.abs(yVelocity) > .5)) && !inHole){
	 * double loopTime = (System.nanoTime() - startTime) / Math.pow(10, 9) * 8;
	 * Play.f.repaint(); startTime = System.nanoTime(); this.setX( xVelocity *
	 * loopTime + this.getX() ); this.setY( yVelocity * loopTime + this.getY() );
	 * xVelocity *= .99999; yVelocity *= .99999; System.out.println(12 +
	 * Play.current.getGreen().getX() + "  vs.  " + this.getX());
	 * System.out.println(12 + Play.current.getGreen().getY() + "  vs.  " +
	 * this.getY()); if (Math.abs(12 + Play.current.getGreen().getX() - this.getX())
	 * <= 10 && Math.abs(12 + Play.current.getGreen().getY() - (600-this.getY())) <=
	 * 10) { overHole = true; } if (overHole && xVelocity < 4 && yVelocity < 4) {
	 * Play.putting = false; inHole = true; } }
	 */
}
