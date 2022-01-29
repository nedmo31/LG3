package golf3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;


public class Mouse extends MyPanel
{
    
	public static int pointsLeft = 3;
	public static int pow = 5, con = 5, put = 5, money = 100;
	public static double X, Y;
	private static final long serialVersionUID = 1L;

	
    public Mouse()
    {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
            	if (e.getButton() == MouseEvent.BUTTON1) {
            		
					if (Play.graphicsStage == 0) {
						if (Mouse.X >= 50 && Mouse.X <= 200 && Mouse.Y >= 175 && Mouse.Y <= 210) {// Clicked Start on
																									// menu
							Play.exitCourse = true;
							Play.currentCourse = new Course(true);
							Play.graphicsStage = 1;

							
						}

						else if (Mouse.X >= 50 && Mouse.X <= 200 && Mouse.Y >= 225 && Mouse.Y <= 260) { // Click New
																										// Player on
																										// menu
							Play.graphicsStage = 2;
						} else if (Mouse.X >= 50 && Mouse.X <= 200 && Mouse.Y >= 275 && Mouse.Y <= 310) { // Click Shop
																											// on menu
							Play.graphicsStage = 4;
						} else if (Mouse.X >= 50 && Mouse.X <= 200 && Mouse.Y >= 325 && Mouse.Y <= 360) { // Click
																											// Driving
																											// Range on
																											// menu
							Play.exitCourse = true;
							Play.currentCourse = new Course("Driving");
							System.out.println("Clicked, current course" + Play.currentCourse);
							MyPanel.currentTextBox = new TextBox("Welcome to the driving range! Try to get on the green in 1 hit");
							Play.graphicsStage = 1;
						} else if (Mouse.X >= 50 && Mouse.X <= 200 && Mouse.Y >= 375 && Mouse.Y <= 410) { // Click
																											// Putting
																											// Green on
																											// menu
							Play.exitCourse = true;
							Play.currentCourse = new Course("Putting", 0);
							System.out.println("Clicked, current course" + Play.currentCourse);
							Play.graphicsStage = 1;
						}
						
					}
            	else if (Play.graphicsStage == 2) {
            		
            		if (Mouse.X <= 500 && Mouse.X >= 425 && Mouse.Y >= 100 && Mouse.Y <= 160
            				&& pow < 10 && pointsLeft > 0)
            			pow++;
                	
                	if (Mouse.X <= 650 && Mouse.X >= 545 && Mouse.Y >= 100 && Mouse.Y <= 160
                		&& con < 10 && pointsLeft > 0)
                		con++;
                	
                	if (Mouse.X <= 800 && Mouse.X >= 695 && Mouse.Y >= 100 && Mouse.Y <= 160
                		&& put < 10 && pointsLeft > 0)
                		put++;
                	
                	if (Mouse.X <= 550 && Mouse.X >= 395 && Mouse.Y >= 250 && Mouse.Y <= 310) {
                		Play.currentPlayer = new Player(Play.toName, pow, con, put, 0, 0, 0, 0);
                		Play.currentPlayer.savePlayer();
                		Play.graphicsStage = 0;
                	}
                	
                	if (Mouse.X <= 800 && Mouse.X >= 645 && Mouse.Y >= 250 && Mouse.Y <= 310) {
                		Play.graphicsStage = 0; pow = con = put = 5;
                	}

            		pointsLeft = 18 - pow - con - put;
                      
                }
            	else if (Play.graphicsStage == 3) {
            		Play.graphicsStage = 0;
            	}
            	else if (Play.graphicsStage == 4) {
            		if (Mouse.X <= 420 && Mouse.X >= 320 && Mouse.Y >= 100 && Mouse.Y <= 160
            				&& Play.currentPlayer.getMoney() >= 20) {
            			Play.currentPlayer.addMoney(-20);
            			Play.currentPlayer.setGravHamCharges(1+Play.currentPlayer.getGravHamCharges());
            		}
            			
                		
                	
                	if (Mouse.X <= 650 && Mouse.X >= 545 && Mouse.Y >= 100 && Mouse.Y <= 160
                			&& Play.currentPlayer.getMoney() >= 10) {
                		Play.currentPlayer.addMoney(-10);
                		Play.currentPlayer.setWindWakCharges(1+Play.currentPlayer.getWindWakCharges());
                	}
                	
                	if (Mouse.X <= 845 && Mouse.X >= 745 && Mouse.Y >= 100 && Mouse.Y <= 160
                			&& Play.currentPlayer.getMoney() >= 15) {
                		Play.currentPlayer.addMoney(-20);
            			Play.currentPlayer.setFlattenerCharges(1+Play.currentPlayer.getFlattenerCharges());
                	}
                		
                	if (Mouse.X <= 800 && Mouse.X >= 645 && Mouse.Y >= 250 && Mouse.Y <= 310)
                		Play.graphicsStage = 0;
            	}
            	else if (Play.graphicsStage == 1) {
            		if (MyPanel.currentTextBox != null) {
            			MyPanel.currentTextBox = null;
            		}
            	}
            	
            	
                Play.clickOne.setX(e.getX()); Play.clickOne.setY(e.getY());
                Play.firstClick=false;
                Play.f.requestFocusInWindow();
            	}
            	else { //Right click
            		
            		if (Play.graphicsStage == 2) {
            			if (Mouse.X <= 500 && Mouse.X >= 425 && Mouse.Y >= 100 && Mouse.Y <= 160
                				&& pow > 1) 
                			pow--;
                    	
                    	if (Mouse.X <= 650 && Mouse.X >= 545 && Mouse.Y >= 100 && Mouse.Y <= 160
                    		&& con > 1)
                    		con--;
                    	
                    	if (Mouse.X <= 800 && Mouse.X >= 695 && Mouse.Y >= 100 && Mouse.Y <= 160
                    		&& put > 1)
                    		put--;
                		pointsLeft = 18 - pow - con - put;
            		}
            		
            	}
            } 

        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) { 
            	X = e.getX(); Y = e.getY();
            	//MyPanel.output.setLocation((int)Mouse.X, (int)Mouse.Y);
            } 

        });
        addMouseListener(new MouseAdapter() {                
            public void mouseReleased(MouseEvent e) {
            	if (Play.graphicsStage == 1) {
            	Play.clickTwo.setX(e.getX()); Play.clickTwo.setY(e.getY());
                Play.firstClick = true;
                double pow = .5 * Math.sqrt( Math.pow(Play.clickOne.getX() - Play.clickTwo.getX(), 2)
                + Math.pow(Play.clickTwo.getY() - Play.clickOne.getY(), 2));
                if (pow > 0) {
                //double x = Play.clickOne.getX() - Play.clickTwo.getX();
                /*Play.ang = Math.atan( (Play.clickOne.getY() - Play.clickTwo.getY())
                / (Play.clickOne.getX() - Play.clickTwo.getX() ) );
                if (x>0) {
                	Play.ang*=-1;
                }
                else if (x<0) {
                	Play.ang = Math.PI - Play.ang;
                } */
                Play.ang = -Math.atan2(Play.clickOne.getY()-Play.clickTwo.getY(),
                		Play.clickOne.getX()-Play.clickTwo.getX());
                //Play.ang = Play.currentClub.angleFix(Play.ang);
                Play.pow = pow; Play.go = true;
                //System.out.println("Play.go is "+Play.go+"\tPower is "+Play.pow+"\tAngle is "+Play.ang);
                if (Play.pow > 100) {
                    Play.pow = 100; pow = 100;}
                repaint();
                //Play.ball.hit(pow, ang, Play.currentClub, Play.current);
                }
            	}
            }
            
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
            	Play.clickTwo.setX(e.getX()); Play.clickTwo.setY(e.getY());
                Play.pow = .5 * Math.sqrt( Math.pow(Play.clickOne.getX() - Play.clickTwo.getX(), 2)
                + Math.pow(Play.clickTwo.getY() - Play.clickOne.getY(), 2)) ;
                if (Play.pow > 100) {
                    Play.pow = 100; pow = 100;}
                double x = Play.clickOne.getX() - Play.clickTwo.getX();
                Play.ang = Math.atan( (Play.clickOne.getY() - Play.clickTwo.getY())
                / (Play.clickOne.getX() - Play.clickTwo.getX() ) );
                if (x>0) {
                	Play.ang*=-1;
                }
                else if (x<0) {
                	Play.ang = Math.PI - Play.ang;
                }
                //Play.ang = Play.currentClub.angleFix(Play.ang);
                Play.f.repaint(); Play.f.repaint();
                
            }
        });
        Play.f.addKeyListener(new KeyAdapter()  {
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                //System.out.println("KEY PRESSED"); 
                if (Play.graphicsStage == 0) {
                	Play.f.repaint();
                	 if (keyCode == 38 && Play.holesToPlay < 18) { //up arrow
                		 Play.holesToPlay++;
                     } else if (keyCode == 38 && Play.holesToPlay >= 18) { //up arrow
                		 Play.holesToPlay = 1;
                     }
                     else if (keyCode == 40 && Play.holesToPlay > 1) { //down arrow
                    	 Play.holesToPlay--;
                     } else if (keyCode == 40 && Play.holesToPlay <= 1) { //down arrow
                    	 Play.holesToPlay = 18;
                     } 
                }
                if (Play.graphicsStage == 2 || Play.graphicsStage == 4) {
                	if (keyCode == 38 && Play.holesToPlay < 18) { //up arrow
               		 Play.holesToPlay++;
                    } else if (keyCode == 38 && Play.holesToPlay >= 18) { //up arrow
               		 Play.holesToPlay = 1;
                    }
                    else if (keyCode == 40 && Play.holesToPlay > 1) { //down arrow
                   	 Play.holesToPlay--;
                    } else if (keyCode == 40 && Play.holesToPlay <= 1) { //down arrow
                   	 Play.holesToPlay = 18;
                    } 
            	   if (keyCode == 8) { //BACKSPACE
            		   if (Play.toName.length() > 0)
            			   Play.toName = Play.toName.substring(0, Play.toName.length()-1);
            	   }
            	   else if (keyCode == 65) {
            		   Play.toName += "a";
            	   }
            	   else if (keyCode == 66) {
            		   Play.toName += "b";
            	   }
            	   else if (keyCode == 67) {
            		   Play.toName += "c";
            	   }
            	   else if (keyCode == 68) {
            		   Play.toName += "d";
            	   }
            	   else if (keyCode == 69) {
            		   Play.toName += "e";
            	   }
            	   else if (keyCode == 70) {
            		   Play.toName += "f";
            	   }
            	   else if (keyCode == 71) {
            		   Play.toName += "g";
            	   }
            	   else if (keyCode == 72) {
            		   Play.toName += "h";
            	   }
            	   else if (keyCode == 73) {
            		   Play.toName += "i";
            	   }
            	   else if (keyCode == 74) {
            		   Play.toName += "j";
            	   }
            	   else if (keyCode == 75) {
            		   Play.toName += "k";
            	   }
            	   else if (keyCode == 76) {
            		   Play.toName += "l";
            	   }
            	   else if (keyCode == 77) {
            		   Play.toName += "m";
            	   }
            	   else if (keyCode == 78) {
            		   Play.toName += "n";
            	   }
            	   else if (keyCode == 79) {
            		   Play.toName += "o";
            	   }
            	   else if (keyCode == 80) {
            		   Play.toName += "p";
            	   }
            	   else if (keyCode == 81) {
            		   Play.toName += "q";
            	   }
            	   else if (keyCode == 82) {
            		   Play.toName += "r";
            	   }
            	   else if (keyCode == 83) {
            		   Play.toName += "s";
            	   }
            	   else if (keyCode == 84) {
            		   Play.toName += "t";
            	   }
            	   else if (keyCode == 85) {
            		   Play.toName += "u";
            	   }
            	   else if (keyCode == 86) {
            		   Play.toName += "v";
            	   }
            	   else if (keyCode == 87) {
            		   Play.toName += "w";
            	   }
            	   else if (keyCode == 88) {
            		   Play.toName += "x";
            	   }
            	   else if (keyCode == 89) {
            		   Play.toName += "y";
            	   }
            	   else if (keyCode == 90) {
            		   Play.toName += "z";
            	   }

            	   
               }
                else if (Play.graphicsStage == 1) {
                	if (keyCode >= 49 && keyCode <= 51) {
                		System.out.println("Key: "+keyCode);
                		Play.currentTool = Play.tools[keyCode-49];
                	}
                	if (keyCode == 70) {
             		   Play.currentTool.use();
             		   
             	   	}
                	else if (keyCode == 68) {
                		Play.currentTool.unuse();
                	}
                	if (keyCode == 69) {
             		   Play.windWak.unuse();
                	}
                	else if (keyCode == 82) {
             		   Play.windWak.use();
                	}
                	if (keyCode == 39) { //right arrow
                	Play.currentClubID++; 
                	if (Play.currentClubID > Play.currentPlayer.getClubs().length-1)
                		Play.currentClubID = 0;
                	Play.currentClub = Play.currentPlayer.getClubs()[Play.currentClubID];
                }
                else if (keyCode == 37) { //left arrow
                    	Play.currentClubID--; 
                    	if (Play.currentClubID < 0)
                    		Play.currentClubID = Play.currentPlayer.getClubs().length-1;
                    	//System.out.println(Play.currentClubID);
                        Play.currentClub = Play.currentPlayer.getClubs()[Play.currentClubID]; 
                    	
                }
                else if (keyCode == 38) { //up arrow
                	Play.backspin = false;
                }
                else if (keyCode == 40) { //down arrow
                	Play.backspin = true;
                }
                //repaint();
                //repaint();
                }
            }
            //public void keyReleased(KeyEvent e) {}
            //public void keyTyped(KeyEvent e ) {}
        }) ;
        
    
    Play.f.addKeyListener(new KeyAdapter()  {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == 65) {
            	if (Player.xVelocity > -30)
      			   Player.xVelocity -= 10;
      		   else 
      			   Player.xVelocity = -30;
     	   }
     	   else if (keyCode == 68) {
     		   if (Player.xVelocity < 30)
     			   Player.xVelocity += 10;
     		   else 
     			   Player.xVelocity = 30;
     	   }
     	   else if (keyCode == 32) {
     		   //if (Player.yVelocity > 1)
     			   Player.yVelocity = -50;
     		   
     			   
     	   }
        }
    }) ; }
    
    /* Failed project
    public static Polygon arrow(int x1, int y1, int x2, int y2,
    		int width, int length) {
    	Polygon p = new Polygon(); 
    	double ang = 1;
    	if (x1 != x2)
    		ang = Math.atan((y2-y1) / (x2-x1));
        if (x1 >= x2) {
        	ang*=-1;
        }
        else {
        	ang = Math.PI - ang;
        }
        Point left = new Point(x1 - (int)((width/2)*Math.cos(Math.PI-ang)), y1 - (int)((width/2)*Math.sin(Math.PI-ang)));
        Point right = new Point(x1 + (int)((width/2)*Math.cos(Math.PI-ang)), y1 + (int)((width/2)*Math.sin(Math.PI-ang)));
        p.addPoint(left.x, left.y); p.addPoint(right.x, right.y);
        left.translate((int)(length*.8*Math.cos(ang)), (int)(length*.8*Math.sin(ang)));
        right.translate((int)(length*.8*Math.cos(ang)), (int)(length*.8*Math.sin(ang)));
        p.addPoint(right.x, right.y); 
        right.translate((int)(width*.3*Math.cos(Math.PI-ang)), (int)(width*.3*Math.sin(Math.PI-ang)));
        p.addPoint(right.x, right.y); 
        if (x1 >= x2) 
        	p.addPoint(x1 + (int)((length)*Math.cos(ang)), y1 - (int)((length)*Math.cos(ang)));
        else 
        	p.addPoint(x1 + (int)((length)*Math.cos(ang)), y1 + (int)((length)*Math.cos(ang)));
        Point left2 = new Point(left.x, left.y);
        left.translate(-(int)(width*.3*Math.cos(Math.PI-ang)), -(int)(width*.3*Math.sin(Math.PI-ang)));
        p.addPoint(left.x, left.y);
        p.addPoint(left2.x, left2.y);
        
    	return p;
    } */
    
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); 

        if (Play.graphicsStage == 0) {
        	
        	
        	
        }
        else if (Play.graphicsStage == 2) {
        	
        }
        else if (Play.graphicsStage == 3) {
        	
        }
        else if (Play.graphicsStage == 4) {
        	
        }
        else {
        
        //g.setColor(Color.BLACK);
        //g.drawOval((int)Play.clickOne.getX()-8,(int)Play.clickOne.getY()-8, 15, 15);
        //g.setColor(powCol);
        g.setColor(new Color(240, 51, 51));
        if (Play.putting) {
        	g.drawLine((int)Play.current.getGreen().getBallX(), (int)Play.current.getGreen().getBallY(),
        			(int)Play.current.getGreen().getBallX() + (int)(Play.pow*Math.cos(Play.ang)), 
        			(int)Play.current.getGreen().getBallY() - (int)(Play.pow*Math.sin(Play.ang)));
        }
        //int tipX=(int)((int)Play.clickOne.getX()*2 - (int)Play.clickTwo.getX());
        //int tipY=(int)((int)Play.clickOne.getY()*2 - (int)Play.clickTwo.getY());
        
        
        //g.drawOval((int)Play.clickOne.getX() - 92,-92+ (int)Play.clickOne.getY(), 200, 200);
        //g.drawLine((int)Play.clickTwo.getX(), (int)Play.clickTwo.getY(),
        //(int)Play.clickOne.getX()+8, (int)Play.clickOne.getY()+8);
        //g.drawLine((int)Play.clickOne.getX(), (int)Play.clickOne.getY(),
        //tipX,tipY);
        /*Polygon arrowhead = new Polygon();
        arrowhead.addPoint(tipX,tipY);
        arrowhead.addPoint(*/
        
        //Hitting info
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        //String powerInfo = String.format("%5.2f %8s",Play.pow, "% power");
        //g.drawString(powerInfo,40, 90);
        //String angleInfo = String.format("%5.2f %2s", Math.toDegrees(Play.ang), "°");
        //g.drawString(angleInfo, 40, 115);
        g.drawString("Hole "+(Play.currentCourse.currentHoleNumber()+1), 500, 50);
        g.drawString("Par " + Play.current.getPar(),500,100);
        g.drawString(Play.current.getStrokes()+ " strokes",500,75);
        if (Play.current.getWind() < 0) {
        	g.drawString("Wind: "+(int)(-10*Play.current.getWind())+" mph left", 500, 125);
        } else if (Play.current.getWind() > 0) {
        	g.drawString("Wind: "+(int)(10*Play.current.getWind())+" mph right", 500, 125);
        } else {
        	g.drawString("No Wind", 500, 125);
        }
        
        //g.drawString(Play.currentPlayer.getPutting()+ " putting stat",500,150);
        
        //swinging graphics 
        /*if (!Play.putting) {
        swingPos = 3*Play.pow;
        g.setColor(powCol);
        g.fillRect((int)Mouse.X, (int)Mouse.Y-30, (int)swingPos, 30);
        g.setColor(Color.black);
        g.drawRect((int)Mouse.X, (int)Mouse.Y-30, 150, 30); g.drawRect((int)Mouse.X+150, (int)Mouse.Y-30, 150, 30);
        }*/
        
        
      //to show available angles
        if (Play.backspin) 
        	g.setColor(new Color(153, 0, 0));
        else 
        	g.setColor(Color.BLUE);
        if (!Play.go && !Play.putting) {
        if (Play.backwards) {
        	for(Point p : Play.currentClub.trajectory(5 + (int)Play.currentPlayer.getConsist())) {
        		g.fillOval(2*(int)Play.ball.getX()-p.x, 590-p.y, 5, 5);
        	}
        }
        else {
        	for(Point p : Play.currentClub.trajectory(5 + (int)Play.currentPlayer.getConsist())) {
        		g.fillOval(p.x, 590-p.y, 5, 5);
        	}
        }
        }
        /*
         * int endX = (int)Play.ball.getX() + (int)(2* Play.pow * Math.sin(Math.PI/2 - Play.currentClub.getAngle()));
        	int endY = (int)Play.ball.getY() + (int)(2* Play.pow * Math.cos(Math.PI/2 - Play.currentClub.getAngle()));
         */
        	
        }
    }  
    }


