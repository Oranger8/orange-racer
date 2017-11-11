package orangeracer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener {
    
    public Timer timer = new Timer(20, this);
    Image image;
    public Car car;
    public Obstacle obstacle[] = new Obstacle[20];
    int points = 0, score;
    Image[] crash = new Image[52];
    AffineTransform af;
    
    public Road() {
        image = new ImageIcon(getClass().getResource("images/road.jpg")).getImage();
        car = new Car();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(car.auto == 0) {
                    if(key == KeyEvent.VK_RIGHT) car.turn += 3;
                    if(key == KeyEvent.VK_LEFT) car.turn += -3;
                }
                if(key == KeyEvent.VK_ESCAPE) System.exit(0);
            }
        });
        for(int i = 0; i < 52; i++) {
            crash[i] = new ImageIcon(getClass().getResource("images/bang/exp" + (i + 2) + ".png")).getImage();
        }
        setFocusable(true);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image, 0, car.road1, null);
        g2.drawImage(image, 0, car.road2, null);
        try {
            af = AffineTransform.getRotateInstance(Math.toRadians(car.turn), car.x + 12, car.y + 27);
            g2.setTransform(af);
            g2.drawImage(car.image, car.x, car.y, null);
            af = AffineTransform.getRotateInstance(Math.toRadians(0), car.x + 12, car.y + 27);
            g2.setTransform(af);
        } catch(NullPointerException ex) {}
        for(int i = 0; i < obstacle.length; i++) {
            try {
                g2.drawImage(obstacle[i].image, obstacle[i].x, obstacle[i].y, null);
            } catch(NullPointerException ex) {}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int r = (int)(100 * Math.random());
        if(r >= 0 && r < 5) {
            for(int i = 0; i < obstacle.length; i++) {
                if(obstacle[i] == null) {
                    obstacle[i] = new Obstacle();
                    break;
                }
            }
        }
        car.move();
        for(int i = 0; i < obstacle.length; i++) {
            try {
                if(!obstacle[i].move()) {
                    obstacle[i] = null;
                    points++;
                }
                if(areIntersect(new Point(obstacle[i].x, obstacle[i].y), getOpposite(obstacle[i]), new Point(car.x, car.y), getOpposite(car))) {
                    score = points;
                    crash(i);
                }
                if(car.auto == 1) {
                    if(areIntersect(car.vision.getLocation(), getOpposite(car.vision), new Point(obstacle[i].x, obstacle[i].y), getOpposite(obstacle[i]))) {
                        if(car.vision.x < obstacle[i].x && car.vision.x + car.image.getWidth(null) > obstacle[i].x) {
                            car.turn += -3;
                        } else {
                            car.turn += 3;
                        }
                    }/* else {
                        car.turn = 0;
                    }*/
                }
            } catch(NullPointerException ex) {}
        }
        repaint();
    }
    
    public boolean areIntersect(Point p1, Point p2, Point p3, Point p4) {
        if(p1.x > p4.x || p2.x < p3.x || p1.y > p4.y || p2.y < p3.y) {
            return false;
        } else {
            return true;
        }
    }
    
    public Point getOpposite(Obstacle obstacle) {
        return new Point(obstacle.x + obstacle.image.getWidth(null), obstacle.y + obstacle.image.getWidth(null));
    }
    
    public Point getOpposite(Car car) {
        return new Point(car.x + car.image.getWidth(null), car.y + car.image.getWidth(null));
    }
    
    public Point getOpposite(Rectangle rect) {
        return new Point(car.vision.x + car.vision.width, car.vision.y + car.vision.height);
    }
    
    public void crash(int c) {
        obstacle[c] = null;
        car.speed = 0;
        car.image = null;
        Graphics g = getGraphics();
        for (int i = 0; i < 52; i++) {
            g.drawImage(crash[i], car.x - 40, car.y - 40, null);
        }
    }
}