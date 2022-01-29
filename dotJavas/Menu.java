package golf3;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu() {
	addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) { 
            Play.f.requestFocusInWindow();
        } 

    });
    addMouseListener(new MouseAdapter() {                
        public void mouseReleased(MouseEvent e) {
            
            }       
    });
    
    
    addMouseMotionListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
            //Play.graphicsStage = 1;
            System.out.println(Play.graphicsStage);
        }
    });
    
    
    Play.f.addKeyListener(new KeyAdapter()  {
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            //System.out.println("KEY PRESSED"); 
            if (keyCode == 39) { //right arrow
            	Play.graphicsStage = 1;
            }
            else if (keyCode == 37) { //left arrow
            }
            else if (keyCode == 38) { //up arrow
            	Play.backwards = false;
            }
            else if (keyCode == 40) { //down arrow
            	Play.backwards = true;
            }
            else if (keyCode == 10) {
                //Play.go = true;
                System.out.println(Play.go);
            }
            //repaint();
            //repaint();
        }
        //public void keyReleased(KeyEvent e) {}
        //public void keyTyped(KeyEvent e ) {}
    }) ;
}
	public Dimension getPreferredSize() {
        return new Dimension(800,650);
    }
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		g.drawImage(Play.menuPic,0,0,null);

	}
}
