/**
 * The Swing frame used by the Monitor application.
 * 
 * @author dhelia
 * @author jtk
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class ViewFrame extends JFrame implements ActionListener {
    private JCheckBoxMenuItem requestsCheckbox;
    private JCheckBoxMenuItem volunteersCheckbox;

    public ViewFrame(View v) {
        JMenuBar menubar = new JMenuBar();
        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_F);

        // create the requests check box and set its action listener to
        // "the instance of View"
        requestsCheckbox = new JCheckBoxMenuItem("Show Requests", true);
        requestsCheckbox.addActionListener(this);
        // create the volunteers check box and set its action listener to
        // "the instance of View"
        volunteersCheckbox = new JCheckBoxMenuItem("Show Volunteers", true);
        volunteersCheckbox.addActionListener(this);

        view.add(requestsCheckbox);
        view.add(volunteersCheckbox);

        menubar.add(view);
        setJMenuBar(menubar);

        setTitle("SafeWalk Monitor Spring 2014");
        setSize(1583/2, 1612/2);  // TODO Associate this size with the actual image size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean vCheckboxIsSelected() {
	return volunteersCheckbox.isSelected();
    }

    public boolean rCheckboxIsSelected() {
         return requestsCheckbox.isSelected();
    }

    /**
     * Event listener for the check boxes, if either of the check boxes was changed, repaint.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == requestsCheckbox || e.getSource() == volunteersCheckbox) {
            repaint();
        }
    }
}