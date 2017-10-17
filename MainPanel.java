import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Graphics;

public class MainPanel extends JPanel {
    private JButton cancelBtn = new JButton("Cancel");
    private JButton enterBtn = new JButton("Enter");
    private JTextField nameInput = new JTextField(25);
    private JPasswordField passInput = new JPasswordField(25);
    private JLabel userNameLab = new JLabel("User Name");
    private JLabel passWordLab = new JLabel("Password:");
    
	public MainPanel() {
		setPreferredSize(new Dimension(1200,600));
        // add Listeners and JElements
        // TODO: use gridbag to organize
        add(userNameLab);
        add(nameInput);
        add(passWordLab);
        add(passInput);
        add(cancelBtn);
        add(enterBtn);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
        }

/***********************************************************************
 * public void methods here to change the screen
***********************************************************************/
/*
 *public void mainView(args) {
 *  // add/remove/edit buttons, listeners, labels, etc...
 *  // display data
 *  revalidate();
 *  repaint();
 * }
 * public void removeMainViewComponents() {
 * 
 * }
 * public void detailedView(args) {
 * 
 * }
 * public void removeDetailedViewComponents() {
 * 
 * }
 * public void removeLoginComponents() {
 * 
 * }
 * public void logIn() {
 * 
 * }
 * 
 * 
 */
/***********************************************************************
 * Template for controller classes i.e. implementing listeners (public
 * classes instead of private of course)
***********************************************************************/
/*
    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // retrieve relevant data from view
            // pass data to model if necessary
            // pass necessary model info to view in call to update view
        }
    }
*/
}
