package java_sketch;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import content.MainApplet;

/**
 * 
 * 
 *
 */

public class Menu extends Panel {


	private JButton button1;
	
	private JButton button2;
	
	private JButton button3;
	
	MainApplet mainAppletObject;
	
	public Menu(final MainApplet ma){
		this.mainAppletObject = ma;
		button1 = new JButton("Conf");
		button2 = new JButton("Home");
		button3 = new JButton("Info");
		
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createRigidArea(new Dimension(40, 60)));
		add(button1);
		add(Box.createRigidArea(new Dimension(15, 0)));
		add(button2);
		add(Box.createRigidArea(new Dimension(15, 0)));
		add(button3);

		
		button1.addActionListener(new ActionListener() {
			
			
			//conf
			@Override
			public void actionPerformed(ActionEvent arg0) {
                   System.out.println("button1");

                   mainAppletObject.changeApplet(0);
                  
			}
		});
		
		//home
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
                   System.out.println("button2");	

                   mainAppletObject.changeApplet(2);
			}
		});
		
		
		//info
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
                   System.out.println("button3");	

                   mainAppletObject.changeApplet(1);
			}
		});
		
		
		
		setBackground(Color.WHITE);
	}
  
}