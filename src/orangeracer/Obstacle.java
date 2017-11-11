package orangeracer;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle {
    
    public int x, y = -100, speed;
    Image image;
    
    public Obstacle() {
        image = new ImageIcon(getClass().getResource("images/cars/car" + (int)(20 * Math.random()) + ".png")).getImage();
        x = (int)(485 * Math.random() + 45);
        speed = (int)(4 * Math.random() + 1);
    }
    
    public boolean move() {
        if(y <= 600) {
            y += speed + 5;
            return true;
        } else {
            return false;
        }
    }
}