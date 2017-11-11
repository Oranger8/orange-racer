import javax.swing.JOptionPane;
import orangeracer.Road;
import orangeracer.Track;

public class OrangeRacer {
    
    public static void main(String[] args) {
        OrangeRacer or = new OrangeRacer();
        or.start();
    }
    
    public void start() {
        Track track = new Track();
        
        Road road = new Road();
        track.add(road);
        
        track.pack();
        track.setLocationRelativeTo(null);
        track.setVisible(true);
        road.car.auto = JOptionPane.showConfirmDialog(road, "Хотите отключить автопилот?", "Transmition", JOptionPane.YES_NO_OPTION);
        road.timer.start();
    }
}