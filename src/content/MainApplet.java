package content;

import java.awt.Color;

import g4p_controls.GEditableTextControl;
import g4p_controls.GEvent;
import g4p_controls.GTextField;
import processing.core.PApplet;
import processing.core.PImage;
import processing.serial.Serial;

/**
 * @author Jonas Roth
 * 
 *
 */

public class MainApplet extends PApplet {

	// the first shown page
	public int currentApplet = 2;

	// swicht size of default Info Layers
	int objectDefaultSize = 75;

	// mouse values
	boolean overBox = false;
	boolean locked = false;

	// Spited in 2 strings for a better formation.
	String confText = "With the box at the end of the page (under the blue line) its possible to change the timer of your power outlet.";
	String hardConf = "Its also possible to change the sensitivity of the sensor. For this purpose you have to adjust the sensor with a screwdriver.";

	// Spited in 3 strings for a better formation
	String homeTextOne = "Thats your Eco_Bar menu.";
	String homeTextTwo = "To configure your power oulet -> Conf.";
	String homeTextThree = "For infromation to your procduct -> Info.";

	//printed if arduino is not available
	String hardwareWaring = "";
	
	// Arduino output text
	String textPrint = "Info Source";

	//
	PImage powerOutletPic;
	PImage confPIC;

	// arduino value -> input field

	GTextField valueInputField;

	// used for Program Arduino com.
	Serial port;

	// Program initialization
	public void setup() {
		
		size(600, 600);
		frameRate(10);
		changeApplet(currentApplet);
		
		//if serial port available port -> port setup
		//else set string for visible warning 
		if(Serial.list().length == 33){
		port = new Serial(this, Serial.list()[32], 9600);
		
			}
		else
			hardwareWaring = "Please check the Eco_Bar connection, then restart the program.";
	}
	

	// used to switch between different pages (setup)
	public void changeApplet(int applet) {

		
		// load pics in (preload is necessary)
		
		confPIC = loadImage(getClass().getResource("/confPic.jpg").toString());
		powerOutletPic = loadImage(getClass().getResource("/power_outlet.jpg").toString());
		
		
		currentApplet = applet;
		removeAll();
		
		
		switch (currentApplet) {

		case 1: // home

			break;

		case 2: // info

			stroke(255);
			background(255, 255, 255);


			//Text Field used for: processing -> arduino 
			valueInputField = new GTextField(this, 250, 450, 100, 40);
			valueInputField.setDefaultText("Set Timer Value");

			break;

		case 3:

			break;
		}
	}

	public void draw() {

		// draws over old content
		stroke(255);
		fill(255, 255, 255, 255);
		rect(0, 0, 600, 600);
		
		
		//if arduino is not connected - return Warning Text on page
		fill(204,0,0);
		textSize(15);
		text(hardwareWaring, 44, 432);
		
		
		// used to switch between different pages (draw/loop)
		switch (currentApplet) {
		case 0:// conf

			image(confPIC, 90, 180, 350, 230);

			fill(0, 102, 153);
			textSize(17);
			text(confText, 30, 10, 450, 150);
			text(hardConf, 30, 80, 450, 150);

			menuBoarders();

			break;

		case 1: // info page

			// draw image
			image(powerOutletPic, 60, 20, 470, 370);

			// draws some info layers
			drawColoredIfInBounds(242, 154, 142, 100, "Verteiler Kasten",
					new Color(255, 0, 0, 96));
			
			//layer for arduino
			drawColoredIfInBounds(310, 260, 55, 70, "Das Microcontroller-Board (Arduino).", new Color(255,
					255, 0, 90));
			
			//layer for power outlet
			drawColoredIfInBounds(110, 50, 395, 70, "Dies ist die Sensor gesteuerte Steckdose.", new Color(0,102,153,90));
			
			//layer for sensor
			drawColoredIfInBounds(455, 130, 40, 40, "Der Bewegunssensor oder auch PIR-Sensor.", new Color(255,182,193,80));
			
			//layer for input box
			drawColoredIfInBounds(78, 141, 115, 105, "Hier können sie ihren Computer anschließen.", new Color(255,127,80,85));
			
			//layer for cable
			drawColoredIfInBounds(108, 260, 20, 130, "Das ist ihr Zuführendes Wandkabel.", new Color(65,105,225, 90));
			
			//layer for breadboard
			drawColoredIfInBounds(264, 262, 45, 70, "Das Breadboard wird vom Controller benötigt.", new Color(0,250,154, 90));
			
			strokeWeight(0);
			textSize(16);
			fill(0, 102, 153);
			text(textPrint, 145, 406);
            
			//used to get axes data (info window)
			//String axes = this.mouseX + "  " + this.mouseY;
			//text(axes, 200, 425);
			//println(axes); //terminal
			
			menuBoarders();

			break;
		case 2: // home

			frameRate(10);

			fill(0, 102, 153);
			textSize(29);
			text("Welcome", 210, 65);
			textSize(19);
			text(homeTextOne, 160, 90, 400, 300);
			text(homeTextTwo, 110, 130, 400, 300);
			text(homeTextThree, 110, 170, 400, 300);

			menuBoarders();

			break;

		default:

			break;
		}
		fill(0, 0, 0, 255);

	}

	// textFid listener (arduino time value)
	//if value input and enter -> write value to arduino
	public void handleTextEvents(GEditableTextControl textcontrol, GEvent event) {
		if (textcontrol == valueInputField && event == GEvent.ENTERED) {

			
			port.write(Integer.parseInt(trim(textcontrol.getText())));
			
		}
	}

	/**
	 * 
	 * @param xAxisValue
	 * @param yAxisValue
	 * @param langthOfObject
	 * @param wideOfObject
	 * @param outString
	 * @param color
	 */
	// produce square info layers

	public void drawColoredIfInBounds(double xAxisValue, double yAxisValue,
			double langthOfObject, double wideOfObject, String outString,
			Color color) {
		if (isWithinSquare(xAxisValue, yAxisValue, langthOfObject, wideOfObject)) {
			overBox = true;
			if (!locked) {

				stroke(255);
				fill(color.getRGB());
				textPrint = outString;
			}
		} else {
			stroke(255, 255, 255, 96);
			fill(255, 255, 255, 96);
			overBox = false;
		}

		rect((float) xAxisValue, (float) yAxisValue, (float) langthOfObject,
				(float) wideOfObject);

	}

	/**
	 * 
	 * @param xAxisValue
	 * @param yAxisValue
	 * @param langthOfObject
	 * @param wideOfObject
	 * @return
	 */
	
	private boolean isWithinSquare(double xAxisValue, double yAxisValue, double langthOfObject, double wideOfObject) {
		return this.mouseX > xAxisValue && this.mouseX < xAxisValue + langthOfObject
				&& this.mouseY > yAxisValue && this.mouseY < yAxisValue + wideOfObject;
				
	}




// Draws menuBoarders

	void menuBoarders() {

		// up
		stroke(0, 102, 153);
		strokeWeight(8);
		line(0, 0, 600, 0);

		// down
		stroke(0, 102, 153);
		strokeWeight(6);
		line(0, 440, 600, 445);

	}

}
