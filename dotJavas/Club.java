package golf3;

import java.awt.Point;

/**
 * Write a description of class Club here.
 *
 * @author Nathan Edmondson
 * @version (a version number or a date)
 */
public class Club
{
    // instance variables - replace the example below with your own
    private double angle, distance;
    private String name; private int dispDist;

    /**
     * Constructor for objects of class Club
     */
    public Club(double a, double dist, String name, int dispDist)
    {
        angle = a; distance = dist; this.name = name;
        this.dispDist = (int)(dispDist * (Play.currentPlayer.getPower()/50 + .8));
    }
    public double getAngle() { return angle; }
    public double getDist() { return distance; } 
    public String getName() { return name; }
    public double getDispDist() { return dispDist; }
    public Point[] trajectory(int n) {
    	Point[] p = new Point[n];
    	
    	double v = (Play.currentPlayer.getPower()/50 + .8) * distance * 1.25*Play.pow / 110.0;
        double initialx = Play.ball.getX(); double initialy = Play.ball.getY();
        double yVelocity = v * Math.sin( angle );
        double xVelocity = v * Math.cos( angle ); 
        
        for(int i = 0; i < n; i++) {
        	p[i] = new Point((int)(initialx + xVelocity * (.3*i)), // constant velocity in x-dir
        			(int)(initialy + yVelocity * (.3*i) + -1 * Ball.gravity * Math.pow((i*.3), 2))); //constant acceleration in y-dir
        }
    	
    	return p;
    }
    
    
}
