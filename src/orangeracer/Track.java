package orangeracer;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Track extends JFrame {
    
    public Track() {
        setPreferredSize(new Dimension(600, 600));
        setTitle("OrangeRacer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
    }
}