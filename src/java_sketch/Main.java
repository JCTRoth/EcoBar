package java_sketch;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import content.MainApplet;

/**
 * 
 * @author Jonas Roth
 *
 */

public class Main {

	public JFrame startWindow = new JFrame();

	public Menu menu;

	public static Staticstics HomeWindow = new Staticstics();
	public static Info infoWindow = new Info();
	public static Conf ConfWindow = new Conf();
	public static javax.swing.JPanel panel;

	public static void main(String[] args) {

		new Main();

	}

	public Main() {
		startWindow.setSize(600, 600); // The window Dimensions

		// exit on exit button
		startWindow
				.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// empty panel

		JPanel panel = (JPanel) startWindow.getContentPane();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		MainApplet sketch1 = new MainApplet();
		sketch1.init();

		menu = new Menu(sketch1);

		panel.add(menu);

		startWindow.add(sketch1);
		panel.add(infoWindow);
		panel.add(HomeWindow);
		panel.add(ConfWindow);
		panel.setBackground(Color.WHITE);

		menu.setVisible(true);
		startWindow.setVisible(true);

	}

}
