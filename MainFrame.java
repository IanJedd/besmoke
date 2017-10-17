import javax.swing.JFrame;
import javax.swing.JPanel;
public class MainFrame {
    public static final String SOFTWARE_NAME = "PlaceHolder";
    public MainFrame(JPanel p) {
        JFrame f = new JFrame(SOFTWARE_NAME);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(p);
		f.pack();
		f.setVisible(true);
    }
    public static void main(String[] args) {
        // testing
        MainPanel m = new MainPanel();
        MainFrame f = new MainFrame(m);
    }
}
