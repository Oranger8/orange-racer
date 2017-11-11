package orangeracer;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Car {
    
    public Image image;
    public int speed = 5, x = 288, y = 500, road1 = 0, road2 = -600, auto = 0;
    Rectangle vision;
    double turn = 0;
    public static final int LEFT_EDGE = 45, RIGHT_EDGE = 530;
    
    public Car() {
        image = new ImageIcon(getClass().getResource("images/car.png")).getImage();
        vision = new Rectangle(285, 400, image.getWidth(null) + 6, image.getHeight(null));
    }
    
    public void move() {
        if(x <= LEFT_EDGE) x = LEFT_EDGE;
        if(x >= RIGHT_EDGE) x = RIGHT_EDGE;
        if(road2 >= 0) {
            road1 = 0;
            road2 = -600;
        } else {
            road1 += speed;
            road2 += speed;
        }
        x += turn;
        vision.setLocation(x - 3, y - 100);
    }
}