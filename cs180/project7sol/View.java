/**
 * The view part of this application consists of a canvas and a menu bar with a View menu. The canvas contains the map
 * of the campus overlayed with the requests and volunteers at their respective locations.
 * 
 * @author dhelia
 * @author jtk
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class View extends JPanel {
    Model model;
    ViewFrame frame;

    final static float dash1[] = { 10.0f };
    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
	    0.0f);

    private Image mapImage;
    private Image volImage;
    private Image reqImage;

    final static int X_SCOREBOARD = 25;
    final static int Y_SCOREBOARD = 25;
    final static int DIAMETER = 16; // diameter of circle at each location

    Graphics2D g2;
    float scaleWidth;
    float scaleHeight;

    /**
     * Constructor: Initializes the UI, starting the Event Dispatch Thread (EDT).
     * 
     * @param model
     *            The model associated with the View.
     */
    public View(Model model) {
	this.model = model;

	mapImage = loadImage("CampusCropped-Faded.jpg");
	volImage = loadImage("volunteer.jpg");
	reqImage = loadImage("request.jpg");

	// Create and initialize the top-level frame...
	frame = new ViewFrame(this);

	// Create the main panel with a BorderLayout...
	JPanel mainPanel = new JPanel(new BorderLayout());

	// Add the canvas (this) to the main panel at the CENTER...
	mainPanel.add(this, BorderLayout.CENTER);

	// Add the main panel to the frame...
	frame.add(mainPanel);
	JScrollPane pane = new JScrollPane(this);
	frame.add(pane);
	frame.getContentPane().add(pane);

	// Set visible and away we go...
	frame.setVisible(true);
    }

    /**
     * Loads images from resources, allowing use in .jar files.
     * 
     * @param name
     *            file name where the icon is located.
     * @return the Image found in the file.
     */
    private Image loadImage(String name) {
	URL url = getClass().getResource(name);
	if (url == null)
	    throw new RuntimeException("Could not find " + name);
	return new ImageIcon(url).getImage();
    }

    /**
     * Called on the Event Dispatch Thread (EDT) in response to a call to repaint(). Accesses the model to decide what
     * to paint. Since the model is also being accessed and updated on a different thread (by the Controller when
     * messages arrive), must get the lock before accessing the model.
     */
    public void paintComponent(Graphics gr) {
	g2 = (Graphics2D) gr;
	scaleWidth = getWidth() / (float) mapImage.getWidth(null);
	scaleHeight = getHeight() / (float) mapImage.getHeight(null);

	// Draw the map first...
	g2.drawImage(mapImage, 0, 0, getWidth(), getHeight(), null);

	// Get the lock before accessing the model...
	synchronized (model.lock) {
	    // Update the locations on the display
	    for (Location location : model.getLocations()) {
		drawLocation(location);
	    }

	    // Update the score board and draw the current locations of the in-transit volunteers...
	    if (frame.vCheckboxIsSelected()) {
		drawScoreboard();
		for (Volunteer volunteer : model.getVolunteers())
		    if (volunteer.getCurrentLocation() == null) // null -> moving
			drawIntransit(volunteer);
	    }
	}
    }

    private void drawLocation(Location location) {
	// Convert the location (x, y) into an image (x, y).
	// TODO Transformation could be more complex.
	double[] xy = location.getXY();
	int x = (int) xy[0];
	int y = (int) xy[1];

	// Draw overlapping circle for the building location...
	g2.setColor(Color.black);
	g2.fillOval(Math.round(x * scaleWidth), Math.round(y * scaleHeight), DIAMETER, DIAMETER);
	g2.setColor(Color.yellow);
	g2.fillOval(Math.round(x * scaleWidth) + 2, Math.round(y * scaleHeight) + 2, DIAMETER - 4, DIAMETER - 4);
	g2.setColor(Color.BLACK);

	g2.setFont(new Font("Arial", Font.PLAIN, 12));

	int dy = g2.getFontMetrics().getHeight() * 2;
	y += dy / 2; // fudge the y coordinate a bit to draw the requests and volunteers

	// If requests are being shown, draw them...
	if (frame.rCheckboxIsSelected()) {
	    for (Request request : location.getRequests()) {
		String s = String.format("%s (%d)", request.getName(), request.getValue());
		y += dy;
		drawName(reqImage, s, x, y);
	    }
	}

	// If volunteers are being shown, draw them...
	if (frame.vCheckboxIsSelected()) {
	    for (Volunteer volunteer : location.getVolunteers()) {
		y += dy;
		drawName(volImage, volunteer.getName(), x, y);
	    }
	}
    }

    private void drawName(Image image, String name, int x, int y) {
	int gap = g2.getFontMetrics().getHeight();

	g2.drawImage(image, Math.round(x * scaleWidth) - gap, Math.round(y * scaleHeight), 12, 12, null);
	g2.drawString(name, Math.round(x * scaleWidth), Math.round(y * scaleHeight) + gap - 3);
    }

    private void drawScoreboard() {
	int xScore = X_SCOREBOARD;
	int yScore = Y_SCOREBOARD;
	g2.setFont(new Font("Arial", Font.BOLD, 16));
	int gap = g2.getFontMetrics().getHeight();

	int c = model.getVolunteers().size() + 2;

	g2.setColor(Color.LIGHT_GRAY);
	g2.fill3DRect(xScore, yScore, 225, gap * c, true);

	g2.setColor(Color.BLACK);
	g2.drawString("Volunteer Score Board", xScore += 10, yScore += gap);

	yScore += gap + gap / 2;

	for (Volunteer volunteer : model.getVolunteers()) {
	    g2.drawString(volunteer.getName() + ": " + volunteer.getScore(), 10 + xScore, yScore);
	    yScore += gap;
	}
    }

    private void drawIntransit(Volunteer volunteer) {
	double[] xy = volunteer.getCurrentPosition();
	int x = (int) Math.round(xy[0]);
	int y = (int) Math.round(xy[1]);

	// Draw volunteer name and anyone being walked...
	g2.setFont(new Font("Arial", Font.PLAIN, 16));
	int gap = g2.getFontMetrics().getHeight();
	g2.setColor(Color.BLACK);
	String s = volunteer.getName();
	if (volunteer.getRequester() != null)
	    s += " [walking " + volunteer.getRequester() + "]";
	g2.drawString(s, Math.round(x * scaleWidth) + gap, Math.round(y * scaleHeight) + gap);

	// Draw diagonal dashed line between start and end positions...
	double[] xyStart = volunteer.getStart();
	int xStart = (int) Math.round((float) xyStart[0]);
	int yStart = (int) Math.round((float) xyStart[1]);

	double[] xyEnd = volunteer.getDestination();
	int xEnd = (int) Math.round((float) xyEnd[0]);
	int yEnd = (int) Math.round((float) xyEnd[1]);

	g2.setStroke(dashed);
	g2.drawLine(Math.round(xStart * scaleWidth) + DIAMETER / 2, Math.round(yStart * scaleHeight) + DIAMETER / 2,
		Math.round(xEnd * scaleWidth) + DIAMETER / 2, Math.round(yEnd * scaleHeight) + DIAMETER / 2);

	// Draw filled circle at volunteer location on dashed line...
	g2.setColor((volunteer.getRequester() == null) ? Color.RED : Color.GREEN);
	g2.fillOval(Math.round(x * scaleWidth), Math.round(y * scaleHeight), DIAMETER, DIAMETER);
    }
}
