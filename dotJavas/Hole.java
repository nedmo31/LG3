package golf3;

import java.awt.Color;

/**
 * Hole class
 *
 * @author Nathan Edmondson
* @version 7/19/21
 */
public class Hole
{
    private double x, y, wind;
    private TerrainChunk [] chunks;
    private int strokes;
    private Green green;
    private int par;
    private Color flagColor;
    /**
     * Constructor for objects of class Hole
     */
    public Hole(double x, double y, TerrainChunk [] chunks)
    {
        this.x = x; this.chunks = chunks;
        this.y = y; this.green = new Green();
        strokes = 0;
    }
    /*
     * Blank Constructor to make an entirely random hole
     * because I'm tired of making them
     */
    public Hole() {
    	this(800, 300, (int)((1-(Math.random()*2))*125),
    			(int)(Math.random()*2), (int)(Math.random()*2));
    }
    public Hole(int n, int m) {
    	this(n, m, (int)((1-(Math.random()*2))*125),
    			(int)(Math.random()*2), (int)(Math.random()*2));
    }
    public Hole(int n, int m, int y, int waters, int sands) {
    	this.x = (int)((Math.random()*n) + m) + .5;
    	this.y = y;	
    	this.green = new Green(); strokes = 0;
    	wind = (1-Math.random()*2);
    	if (x > 650)
    		par = 4;
    	else if (x>450)
    		par = 3;
    	else 
    		par = 3;
    	if (y > 75)
    		par++;
    	/*
    	 * Randomly generate the number of sand, water and chunks
    	 */
    	//int waters = (int)(Math.random()*2); Now it can take it as an argument
    	//int sands = (int)(Math.random()*2);
    	int chunkCount = 8 + (int)(Math.random()*2*(x/300));
    	double remainingChunks = chunkCount - 1;
    	double landLeft = x-25;
    	double previousX = x+25; double previousY = 600-this.y;
    	this.chunks = new TerrainChunk[chunkCount];
    	/*
    	 * 
    	 * Create the chunks around the hole that need to be consistent
    	 */
    	TerrainChunk h = new TerrainChunk(x-25,x+25,600-y);
    	this.chunks[0] = h;

    	/*
    	 * Two chunks to the right
    	 */
    			double x2 = (1200 - previousX) / 2; x2 = previousX + x2 + ((Math.random() * 2)) * (x2 / 2);
    			double y2 = 600-this.y;
    			if (Math.random() > .34)
    				y2 += (2-(Math.random() * 2)) * 20;
    			else 
    				;//y2 stays the same because it's flat
    			chunks[1] = new TerrainChunk(previousX, x2, previousY, y2);
    			remainingChunks--; String blm = "grass";
    		
    		if (Math.random() < (sands+waters) / remainingChunks) {
    			if (waters > 0) {
    				waters--;
    				blm = "water";
    			}
    			else if (sands > 0) {
    				sands--;
    				blm = "sand";
    			}
    		}
    		chunks[2] = new TerrainChunk(x2, 1200, y2, 600, blm);
			remainingChunks--;
    		for (int i = 3; i < chunkCount; i++) {
    			double aprroxChunkSize = landLeft / remainingChunks;
    			double x3, y3; String type;
    			if (i==3) {
    				previousX = 0; previousY = 600;
    				x3 = previousX + aprroxChunkSize;// + (1-(Math.random() * 2)) * 30;
    				y3 = previousY; type = "grass";
    			}
    			else if (i==chunkCount - 1) {
    				x3 = this.x-25;
    				y3 = 600 - this.y; type = "grass";

    	    		}
    			
    			else {
    				if (Math.random() < (sands+waters) / (remainingChunks-1)) {
    	    			if (waters > 0) {
    	    				waters--; type = "water";
    	    			}
    	    			else if (sands > 0) {
    	    				sands--; type = "sand";
    	    			}
    	    			else 
    	    				type = "grass";
    	    		}
    				else 
    					type = "grass";
    				if (Math.random() > .5)
        				y3 = previousY + (1-(Math.random() * 2)) * 20;
        			else 
        				y3 = previousY;//y2 stays the same because it's flat
    				x3 = previousX + aprroxChunkSize + (1-(Math.random() * 2)) * 30;
    				
    			}
    			landLeft -= (x3-previousX);
    			this.chunks[i] = new TerrainChunk(previousX, x3, previousY, y3, type);
    			previousX = x3; previousY = y3;
    		}
    		x-=5;
    		
    		flagColor = new Color((int)(Math.random()*255),
    	    		(int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    public TerrainChunk[] getChunks() {
        return chunks;
    }
    public Green getGreen() { return green; }
    public double getX() {  return x; }
    public double getY() { return y; }
    public Color getFlagColor() {
    	return flagColor;
    }
    public double getYatX(double xx) {
        for (TerrainChunk t : chunks) {
            if (t.getx1() <= xx && t.getx2() >= xx && t.getSolid()) {
                return 600 - t.getTerrY(xx);
            }
        }  
        //System.out.println("Didn't find YatX");
        return 0;
    }
    public double getAngatX(double xx) {
        for (TerrainChunk t : chunks) {
            if (t.getx1() <= xx && t.getx2() >= xx && t.getSolid()) {
                return t.getTerrAng();
            }
        }  
        System.out.println("Didn't find TerrAng");
        return 0;
    }
    public boolean waterCheck(double xx, double y) {
        for (TerrainChunk t : this.chunks) {
                if (t.inDrink(xx, y)) {
                    return true;       }   
        }  
        return false;
    }
    public boolean sandCheck(double xx, double y) {
        for (TerrainChunk t : this.chunks) {
                if (t.inSandChunk(xx, y)) {
                    return true;       }   
        }  
        return false;
    }
    public void addStroke() {
        strokes++;
    }
    public int getStrokes() {
        return strokes;
    }
    public int getPar() {
    	return par;
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    
    public boolean inHole(double ballx, double bally) {
        if (ballx < x + 2 && ballx > x - 2)// && bally < y) 
          return true;
        else 
          return false;
    }
    public int playHole() throws GolfException {
    	Play.backwards = false;
        Play.ball.setX(39); Play.ball.setY(0);Player.x = Play.ball.getX()-60; Player.y = 549-Play.ball.getY();
        while (!inHole(Play.ball.getX(), Play.ball.getY())
        		&& !Play.putting) {
        	Play.f.repaint();
        	if (Play.ball.getX() < x + 25 && Play.ball.getX() > x - 25
            		&& !inHole(Play.ball.getX(), Play.ball.getY())
            		&& strokes < Play.currentCourse.getStrokeOut()) {
            	
            	Play.putting = true;
            	System.out.println("first one");
            	break;
            }
        	else if (strokes >= Play.currentCourse.getStrokeOut()) {
        		Play.putting = false;
        		System.out.println("second one");
        		break;
        	}
        	if (Play.exitCourse) {
        		Play.putting = false;
        		throw new GolfException();
        	}
            if (Play.go) {
                Play.ball.hit(Play.pow, Play.ang, Play.currentClub, Play.current, Play.backspin);
                addStroke(); 
                Play.go=false;
                Play.inSand = sandCheck(Play.ball.getX(), Play.ball.getY());
                Play.backwards = Play.ball.getX() > Play.current.x;
                //Play.currentPlayer.playerRun();
                
                if (Play.ball.getX() < x + 25 && Play.ball.getX() > x - 25
                		&& !inHole(Play.ball.getX(), Play.ball.getY())
                		&& strokes < Play.currentCourse.getStrokeOut()) {
                	
                	Play.putting = true;
                	break;
                }
            }      
            
        }
        if (Play.putting) {
        	System.out.println("Starting putting");
        	green.playGreen(1000 - (x - Play.ball.getX())*8, 200 + (int)((Math.random()-.5)*200));
        }
			Play.p.playSoundEffect("ballInHole.wav");
        /*if (Play.putting) {
        	Play.ball.setX(600+(Play.current.getX() - Play.ball.getX())*10); 
        	Play.ball.setY(375 + (2*Math.random()-1) * 200); 
        	System.out.println("CHANGED POSITION");
        }
        	
        while (Play.putting) 
        	{
        	Play.f.repaint();
        	if (Play.go) { 
        	    Play.ball.putt(Play.pow,  Play.ang);
        	    addStroke();  
        	    Play.go = false;} 
        	}
        Play.putting = false;*/
		int money = getPar()+1 - getStrokes();
		if (money > 0 && !Play.currentCourse.getName().equals("Driving Range")
				&& !Play.currentCourse.getName().equals("Putting Practice"))
			Play.currentPlayer.addMoney(money);
        return getStrokes();
    }
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
}
