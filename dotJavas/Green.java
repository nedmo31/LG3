package golf3;

public class Green {
	
	private double holeX, holeY, ballX, ballY, xAcc, yAcc;
	private String slope;
	
	public Green() {
		holeX = 1000 + (2*Math.random()-1) * 100;
		holeY = 200 + (2*Math.random()-1) * 50;
		double s = (int)(5*Math.random());
		if (s==0) {
			slope = "flat";
			xAcc = 0; yAcc = 0;
		}
		else if (s==1) {
			slope = "right";
			xAcc = 5; yAcc = 0;
		}
		else if (s==2) {
			slope = "left";
			xAcc = -5; yAcc = 0;
		}
		else if (s==3) {
			slope = "up";
			xAcc = 0; yAcc = -5;
		}
		else if (s==4) {
			slope = "down";
			xAcc = 0; yAcc = 5;
		}
	}
	
	public void flattenSlope() {
		slope = "flat";
		xAcc = 0; yAcc = 0;
	}
	
	public double getX() { return holeX; }
	public double getY() { return holeY; }
	public double getBallX() { return ballX; }
	public double getBallY() { return ballY; }
	public String getSlope() { return slope; }
	
	public boolean inHole(double x, double y) {
		return Math.abs(holeX - x) < (8 + .4*Play.currentPlayer.getPutting()) && 
				Math.abs(holeY - y) < (8 + .4*Play.currentPlayer.getPutting());
	}
	public boolean reallyInHole(double x, double y) {
		return Math.abs(holeX - x) < (5 + .4*Play.currentPlayer.getPutting()) && 
				Math.abs(holeY - y) < (5 + .4*Play.currentPlayer.getPutting());
	}
	
	public void putt(double pow, double ang) {
		double v = pow*.75;
        double yVelocity = -v * Math.sin( ang );
        double xVelocity = v * Math.cos( ang ); 
        //ballX += xVelocity; 
        //ballY += yVelocity;
        double time = System.nanoTime(), looptime;
        while (Math.abs(v) > .1 ) {
        	looptime = (System.nanoTime() - time)  / Math.pow(10, 9) * 8;
        	time = System.nanoTime();
        	Play.f.repaint();
        	ballX += (xVelocity * looptime);
        	ballY += (yVelocity * looptime);
        	if (v > 0)
        		v -= 7*looptime;
        	else
        		v += 7*looptime;
        	yVelocity = -v * Math.sin( ang );
            xVelocity = v * Math.cos( ang );
            xVelocity += xAcc;
            yVelocity += yAcc;
            if (inHole(ballX, ballY) && v < (5 + .4*Play.currentPlayer.getPutting())
            		|| reallyInHole(ballX, ballY) && v < (14 + .2*Play.currentPlayer.getPutting())) {
            	break;
            }
        }

        /* Old deccelaration
         * 
         * xVelocity += xAcc * looptime;
        	yVelocity += yAcc * looptime;
         * if (xVelocity > 0) {
        		xAcc = -5 - xVelocity / 20 ;
        	} else if (xVelocity == 0) {
        		xAcc = 0;
        	} else {
        		xAcc = 5 + xVelocity / 20 ;
        	}
        	if (yVelocity > 0) {
        		yAcc = -5 - yVelocity / 20 ;
        	} else if (yVelocity == 0) {
        		yAcc = 0;
        	} else {
        		yAcc = 5 + yVelocity / 20 ;
        	}
         */
	}
	
	public int playGreen(double x, double y) throws GolfException{
		ballX = x; ballY = y;
		int putts = 0;
		while (!inHole(ballX, ballY)) {
			Play.f.repaint();
			if (Play.exitCourse)
				throw new GolfException();
            if (Play.go) {
            	putt(Play.pow, Play.ang);
            	putts++;
            	Play.current.addStroke();
            	Play.go=false;
            }
		}
		System.out.println("Done putting!");
		Play.putting = false;
		return putts;
	}
}
