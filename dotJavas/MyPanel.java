package golf3;

/**
 * Write a description of class Graphics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
  
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MyPanel extends JPanel  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static double pow, ang;
    public static boolean hit = false;
    
    public static TextBox currentTextBox;
    /*public static boolean firstClick = true;
   /* public static void print(String s) {
    	output.append(s+"\n");
    	Play.f.repaint();
    	
    }*/
    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        //this.add(output);
        
        /*addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!Play.firstClick) {
                    Play.clickTwo.setX(e.getX()); Play.clickTwo.setY(e.getY());
                    Play.firstClick = true;
                    double pow = Math.sqrt( Math.pow(Play.clickOne.getX() - Play.clickTwo.getX(), 2)
                    + Math.pow(Play.clickTwo.getY() - Play.clickOne.getY(), 2)) / 5;
                    double ang = Math.atan( (Play.clickTwo.getY() - Play.clickOne.getY())
                    / (Play.clickOne.getX() - Play.clickTwo.getX() ) );
                    Play.hit(pow, Math.abs(ang), new Club(5, 85, 100, ""), Play.h1); //FIX
                    System.out.println("CLICKED SECOND, POWER" +pow+" "+ang);
                    hit = true;
                }
                else if (Play.firstClick) {
                    Play.clickOne.setX(e.getX()); Play.clickOne.setY(e.getY());
                    Play.firstClick=false;
                    System.out.println("CLICKED FIRST MYPANEL CLASS");
                }
                
            }
        }); */

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                repaint2();
            }
        });
        
    }
    
        /*
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                repaint2();
            }
        });
        */
    
    
    public void repaint2() {
        //if ((Play.ball.getX()!=x) || (Play.ball.getY()!=y)) {
            repaint();
            repaint();
        //}
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1200,775);
    }
    /*
     * Graphics stages
     * 0: Main Menu
     * 1: Playing the game
     * 2: New Player Menu
     * 3: Scorecard
     * 4: Shop
     */
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); 
        if (Play.graphicsStage == 0 || Play.graphicsStage == 2 || Play.graphicsStage == 4) {
        	g.drawImage(Play.sky,0,0,null);
            for (int i = 0; i < Play.current.getChunks().length; i++) {
                Polygon p = new Polygon();
                Polygon d = new Polygon();
                Polygon e = new Polygon();
                p.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12());
                p.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22());
                p.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
                p.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
                
                d.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12()+25);
                d.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22()+25);
                d.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
                d.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
                
                e.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12()+50);
                e.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22()+50);
                e.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
                e.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
                
                if (Play.current.getChunks()[i].getType().equals("hole")) {
                	g.setColor(new Color(50, 200, 50));
                    g.fillPolygon(p);
                    g.setColor(new Color(150,75,0));
                    g.fillPolygon(d);
                }
                	
                else if (Play.current.getChunks()[i].getType().equals("sand")) {
                    g.setColor(Color.YELLOW);
                    g.fillPolygon(p);
                    g.setColor(new Color(150,75,0));
                    g.fillPolygon(e);
                }
                else if (Play.current.getChunks()[i].getType().equals("water")) {
                    g.setColor(Color.BLUE);
                    g.fillPolygon(p);
                    g.setColor(new Color(150,75,0));
                    g.fillPolygon(e);
                }
                else {
                    g.setColor(Color.GREEN);
                    g.fillPolygon(p);
                    g.setColor(new Color(150,75,0));
                    g.fillPolygon(d);
                    
                }
                
                //g.setColor(Color.BLACK);
                //g.drawPolygon(p);
            }
            
            //To MAKE IT BROWN UNDER THE HOLE
            g.setColor(new Color(150,75,0));
            g.fillRect((int)Play.current.getX() - 13, 600 - (int)Play.current.getY() + 25, 30, 30);
            
            //ALL AROUND THE HOLE TO MAKE IT FANCY
            int midHole = (int)Play.current.getX(); int holeHeight = 600 - (int)Play.current.getY();
            g.setColor(Color.RED);
            g.fillRect(midHole + 1, holeHeight-3 - 15, 6, 23);
            g.fillRect(midHole + 1, holeHeight-49- 15, 6, 23);
            g.setColor(Color.WHITE);
            g.fillRect(midHole + 1, holeHeight-26- 15, 6, 23);
            g.fillRect(midHole + 1, holeHeight-72- 15, 6, 23);
            int [] flagX = new int[] {midHole+7, midHole + 47, midHole + 7};
            int [] flagY = new int[] {holeHeight-72- 15, holeHeight - 57- 15, holeHeight - 42- 15};
            g.setColor(Play.current.getFlagColor()); g.fillPolygon(new Polygon(flagX, flagY, 3)); 
        }
        if (Play.graphicsStage == 0) {
        	
            //Actual Menu that overlays the background
            g.setColor(Color.black);
        	g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        	g.drawString("Linear Golf 3!", 50, 100);
        	
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Player: "+Play.currentPlayer.getName()+"  $ "+Play.currentPlayer.getMoney(), 800, 100);
        	
        	if (Mouse.X >= 50 && Mouse.X <= 200 &&
        		Mouse.Y >= 175 && Mouse.Y <= 210) 
        		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        	else 
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Start - "+Play.holesToPlay+" holes", 50, 200);
        	if (Mouse.X >= 50 && Mouse.X <= 200 &&
            		Mouse.Y >= 225 && Mouse.Y <= 260) 
            		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            	else 
            		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("New Player", 50, 250);
        	if (Mouse.X >= 50 && Mouse.X <= 200 &&
            		Mouse.Y >= 275 && Mouse.Y <= 310) 
            		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            	else 
            		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Shop", 50, 300);
        	if (Mouse.X >= 50 && Mouse.X <= 200 &&
            		Mouse.Y >= 325 && Mouse.Y <= 360) 
            		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            	else 
            		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Driving Range", 50, 350);
        	if (Mouse.X >= 50 && Mouse.X <= 200 &&
            		Mouse.Y >= 375 && Mouse.Y <= 410) 
            		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            	else 
            		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Putting Green", 50, 400);
        	/*g.drawImage(Play.menuPic, 0,0,null);
        	g.setColor(Color.darkGray);
        	
        	if (Mouse.X >= 63 && Mouse.X <= 247
        	&& Mouse.Y >= 348 && Mouse.Y <= 500)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        	else 
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        	g.drawString("Free", 100, 410);
        	g.drawString("Play", 100, 460);
        	g.drawString(""+Play.holesToPlay, 15, 430);
        	
        	if (Mouse.X >= 298 && Mouse.X <= 483
                && Mouse.Y >= 348 && Mouse.Y <= 500)
                g.setFont(new Font("TimesRoman", Font.BOLD, 40));
            else 
                g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        	g.drawString("Continue", 310, 430);
        	
        	if (Mouse.X >= 535 && Mouse.X <= 720
            && Mouse.Y >= 348 && Mouse.Y <= 500)
                g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            else 
                g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        	g.drawString("New", 580, 410);
        	g.drawString("Player", 560, 460);
        	
        	if (Mouse.X >= 535 && Mouse.X <= 720
            && Mouse.Y >= 164 && Mouse.Y <= 316)
                g.setFont(new Font("TimesRoman", Font.BOLD, 50));
           else 
                g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        	g.drawString("Other", 570, 250);
        	
        	if (!Play.musicOn) {
        		g.setColor(Color.RED);
        		g.drawLine(687, 113, 769, 46);
        		g.drawLine(687, 46, 769, 113);
        	}*/
        }
        else if (Play.graphicsStage == 2) {
        	
        	g.setColor(Color.black);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Player Name: " + Play.toName, 450, 90);
        	g.drawString("Skills: ", 325, 150);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Left Click - increase skill", 50, 100);
        	g.drawString("Right Click - decrease skill", 50, 150);
        	g.drawString("Type to name yourself", 50, 200);
        	g.drawString("Points Remaining: "+Mouse.pointsLeft, 470, 210);
        	if (Mouse.X <= 500 && Mouse.X >= 425 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Power", 430, 130);
        	g.drawString(""+Mouse.pow, 450, 155);
        	
        	if (Mouse.X <= 650 && Mouse.X >= 545 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Consistency", 550, 130);
        	g.drawString(""+Mouse.con, 590, 155);
        	
        	if (Mouse.X <= 800 && Mouse.X >= 695 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Putting", 700, 130);
        	g.drawString(""+Mouse.put, 720, 155);
        	
        	if (Mouse.X <= 550 && Mouse.X >= 395 && Mouse.Y >= 250 && Mouse.Y <= 310)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Save Player", 400, 280);
        	
        	if (Mouse.X <= 800 && Mouse.X >= 645 && Mouse.Y >= 250 && Mouse.Y <= 310)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Return to Menu", 650, 280);
        	/*g.drawImage(Play.newPlayerMenu, 0,0,null);
        	g.setColor(Color.black);
        	g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        	g.drawString(""+Play.currentPlayer.getPower(), 67, 385);
        	g.drawString(""+Play.currentPlayer.getConsist(), 286, 385);
        	g.drawString(""+Play.currentPlayer.getPutting(), 490, 385);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        	g.drawString("Power", 67, 225);
        	g.drawString("Consistency", 249, 225);
        	g.drawString("Putting", 480, 225);
        	g.drawString("Name: "+Play.toName, 100, 50);
        	g.drawString(Mouse.pointsLeft + " points left to add", 50, 100); */
        	
        }
        else if (Play.graphicsStage == 3) {
        	g.drawImage(Play.scorecard,0,0,null);
        	g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        	for (int r = 0; r < 18; r++) {
        		if (r<9) {
        			g.drawString(""+Play.currentCourse.getHoles()[r].getPar(), 85 + 73*r, 290);
        			g.drawString(""+Play.currentCourse.getHoles()[r].getStrokes(), 85 + 73*r, 220);
        		}
        		else {
        			g.drawString(""+Play.currentCourse.getHoles()[r].getPar(), 85 + 73*(r-9), 510);
        			g.drawString(""+Play.currentCourse.getHoles()[r].getStrokes(), 85 + 73*(r-9), 440);
        		}
        		g.drawString(""+Course.fullScore,735,440);
        		g.drawString(""+Course.halfPar,735,290);
        		g.drawString(""+Course.fullPar,735,510);
        		g.drawString(""+Course.halfScore, 735, 220);
        	}
        }
        else if (Play.graphicsStage == 4) {
        	g.setColor(Color.black);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        	g.drawString("Shop", 575, 90);
        	g.drawString("Tools: ", 250, 150);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Left Click - buy", 50, 100);
        	g.drawString("Money Remaining: "+Play.currentPlayer.getMoney(), 470, 210);
        	if (Mouse.X <= 420 && Mouse.X >= 320 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Gravity Hammer $20", 325, 130);
        	g.drawString(Play.currentPlayer.getGravHamCharges()+" owned", 345, 155);
        	
        	if (Mouse.X <= 650 && Mouse.X >= 545 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Wind Waker $10", 550, 130);
        	g.drawString(Play.currentPlayer.getWindWakCharges()+" owned", 570, 155);
        	
        	if (Mouse.X <= 845 && Mouse.X >= 745 && Mouse.Y >= 100 && Mouse.Y <= 160)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Green Flattener $15", 750, 130);
        	g.drawString(Play.currentPlayer.getFlattenerCharges()+" owned", 770, 155);
        	if (Mouse.X <= 800 && Mouse.X >= 645 && Mouse.Y >= 250 && Mouse.Y <= 310)
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        	else
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        	g.drawString("Exit Shop", 650, 280);
        }
        else if (Play.graphicsStage == 1){
    	g.drawImage(Play.sky,0,0,null);
        //g.drawString("Hole One, 180 yds",10,20);       
        
        
        g.setColor(Color.gray);
        if (Play.putting)
        	g.setColor(Color.red);
        g.fillRect(799, 0, 401, 401);
        g.setColor(new Color(50, 250, 50));
        g.fillRect(800, 0, 400, 400);
        g.setColor(new Color(50, 200, 50));
        g.fillOval(800, 0, 400, 400);
        g.setColor(Color.BLACK);
        g.fillOval((int)Play.current.getGreen().getX()-10, (int)Play.current.getGreen().getY()-10, 20, 20);
        g.setColor(Color.WHITE);
        if (Play.putting) {
        	g.fillOval((int)Play.current.getGreen().getBallX()-6, (int)Play.current.getGreen().getBallY()-6, 12, 12);
        }
        g.setColor(Color.BLACK);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        g.drawString("Green slope: "+Play.current.getGreen().getSlope(), 925, 20);
        /*int [] x = new int [3];
        int [] y = new int [3];
        if (Play.current.getGreen().getSlope().equalsIgnoreCase("down")) {        
          x[0]=880; x[1]=890; x[2]=900; y[0]=80; y[1]=110; y[2]=80;       
          g.fillPolygon(x, y, 3); 
          x[0]=1100; x[1]=1110; x[2]=1120; y[0]=80; y[1]=110; y[2]=80;       
          g.fillPolygon(x, y, 3); 
          x[0]=880; x[1]=890; x[2]=900; y[0]=300; y[1]=330; y[2]=300;       
          g.fillPolygon(x, y, 3); 
          x[0]=1100; x[1]=1110; x[2]=1120; y[0]=300; y[1]=330; y[2]=300;       
          g.fillPolygon(x, y, 3); 
       }
       else if (Play.current.getGreen().getSlope().equalsIgnoreCase("up")) {        
          x[0]=120; x[1]=130; x[2]=140; y[0]=120; y[1]=90; y[2]=120;       
          g.fillPolygon(x, y, 3); 
          x[0]=470; x[1]=480; x[2]=490; y[0]=120; y[1]=90; y[2]=120;       
          g.fillPolygon(x, y, 3); 
          x[0]=120; x[1]=130; x[2]=140; y[0]=480; y[1]=450; y[2]=480;       
          g.fillPolygon(x, y, 3); 
          x[0]=470; x[1]=480; x[2]=490; y[0]=480; y[1]=450; y[2]=480;       
          g.fillPolygon(x, y, 3); 
      } 
      else if (Play.current.getGreen().getSlope().equalsIgnoreCase("right")) {        
          x[0]=120; x[1]=150; x[2]=120; y[0]=120; y[1]=130; y[2]=140;       
          g.fillPolygon(x, y, 3); 
          x[0]=480; x[1]=510; x[2]=480; y[0]=120; y[1]=130; y[2]=140;       
          g.fillPolygon(x, y, 3); 
          x[0]=120; x[1]=150; x[2]=120; y[0]=480; y[1]=490; y[2]=500;       
          g.fillPolygon(x, y, 3); 
          x[0]=480; x[1]=510; x[2]=480; y[0]=480; y[1]=490; y[2]=500;       
          g.fillPolygon(x, y, 3); 
    }   
      else if (Play.current.getGreen().getSlope().equalsIgnoreCase("left")) {        
          x[0]=120; x[1]=90; x[2]=120; y[0]=120; y[1]=130; y[2]=140;       
          g.fillPolygon(x, y, 3); 
          x[0]=480; x[1]=450; x[2]=480; y[0]=120; y[1]=130; y[2]=140;       
          g.fillPolygon(x, y, 3); 
          x[0]=120; x[1]=90; x[2]=120; y[0]=480; y[1]=490; y[2]=500;       
          g.fillPolygon(x, y, 3); 
          x[0]=480; x[1]=450; x[2]=480; y[0]=480; y[1]=490; y[2]=500;       
          g.fillPolygon(x, y, 3); 
    }*/
        for (int i = 0; i < Play.current.getChunks().length; i++) {
            Polygon p = new Polygon();
            Polygon d = new Polygon();
            Polygon e = new Polygon();
            p.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12());
            p.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22());
            p.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
            p.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
            
            d.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12()+25);
            d.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22()+25);
            d.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
            d.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
            
            e.addPoint(Play.current.getChunks()[i].getx12(), Play.current.getChunks()[i].gety12()+50);
            e.addPoint(Play.current.getChunks()[i].getx22(), Play.current.getChunks()[i].gety22()+50);
            e.addPoint(Play.current.getChunks()[i].getx42(), Play.current.getChunks()[i].gety42());
            e.addPoint(Play.current.getChunks()[i].getx32(), Play.current.getChunks()[i].gety32());
            
            if (Play.current.getChunks()[i].getType().equals("hole")) {
            	g.setColor(new Color(50, 200, 50));
                g.fillPolygon(p);
                g.setColor(new Color(150,75,0));
                g.fillPolygon(d);
            }
            	
            else if (Play.current.getChunks()[i].getType().equals("sand")) {
                g.setColor(Color.YELLOW);
                g.fillPolygon(p);
                g.setColor(new Color(150,75,0));
                g.fillPolygon(e);
            }
            else if (Play.current.getChunks()[i].getType().equals("water")) {
                g.setColor(Color.BLUE);
                g.fillPolygon(p);
                g.setColor(new Color(150,75,0));
                g.fillPolygon(e);
            }
            else {
                g.setColor(Color.GREEN);
                g.fillPolygon(p);
                g.setColor(new Color(150,75,0));
                g.fillPolygon(d);
                
            }
            
            //g.setColor(Color.BLACK);
            //g.drawPolygon(p);
        }
        
        //To MAKE IT BROWN UNDER THE HOLE
        g.setColor(new Color(150,75,0));
        g.fillRect((int)Play.current.getX() - 13, 600 - (int)Play.current.getY() + 25, 30, 30);
        
        //ALL AROUND THE HOLE TO MAKE IT FANCY
        int midHole = (int)Play.current.getX(); int holeHeight = 600 - (int)Play.current.getY();
        //g.setColor(Color.GRAY); 
        //g.fillRect(midHole - 13, holeHeight, 27, 20);
        //g.setColor(Color.BLACK);
        //g.fillRect(midHole - 10, holeHeight, 20, 17); old hole graphics
        // - 15 is to move the flag up, just used a constant translation instead of doing the math
        g.setColor(Color.RED);
        g.fillRect(midHole + 1, holeHeight-3 - 15, 6, 23);
        g.fillRect(midHole + 1, holeHeight-49- 15, 6, 23);
        g.setColor(Color.WHITE);
        g.fillRect(midHole + 1, holeHeight-26- 15, 6, 23);
        g.fillRect(midHole + 1, holeHeight-72- 15, 6, 23);
        int [] flagX = new int[] {midHole+7, midHole + 47, midHole + 7};
        int [] flagY = new int[] {holeHeight-72- 15, holeHeight - 57- 15, holeHeight - 42- 15};
        g.setColor(Play.current.getFlagColor()); g.fillPolygon(new Polygon(flagX, flagY, 3)); 
        
        
        //Polygon groundLevel = new Polygon();
        //groundLevel.addPoint(0, 600); groundLevel.addPoint(1200,600);
        g.setColor(Color.WHITE);
        g.fillOval((int)Play.ball.getX(), 590-(int)Play.ball.getY(), 10, 10);
        g.setColor(Color.BLACK);
        g.drawOval((int)Play.ball.getX(), 590-(int)Play.ball.getY(), 10, 10);
        //g.drawPolygon(groundLevel);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        
        //Show clubs on the top left of the frame
        for (int i = 0; i < Play.currentPlayer.getClubs().length; i++) {
        	Club c = Play.currentPlayer.getClubs()[i];
        	if (c.getName().equals(Play.currentClub.getName())) {
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        		g.drawLine(30, 35+i*50, 80, 35 + i*50);
        	}
        	g.drawString(c.getName(),35,25 + i*50);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        }
        
        //Show tools on the top left of the frame
        for (int i = 0; i < Play.tools.length; i++) {
        	Tool t = Play.tools[i];
        	
        	if (t.getName().equals(Play.currentTool.getName())) {
        		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        		g.drawLine(120, 35+i*50, 170, 35 + i*50);
        	}
        	g.drawString(t.getName()+" - "+t.getCharges(),125,25 + i*50);
        	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        }
        
        //Show the text box
        if (currentTextBox != null) {
        	//g.setColor(new Color(250, 243, 190));
        	//g.fillRect(300, 550, 600, 200);
        	g.setColor(Color.black);
        	g.drawRect(300, 550, 600, 200);
        	g.drawString(currentTextBox.getText(), 325, 575);
        }
        }
        
    }  
}
