package java_sketch;

import java.awt.Panel;

import javax.swing.BoxLayout;

/**
 * 
 * 
 *
 */

public class Info extends Panel {

	processing.core.PApplet sketch = null;
	public Info(){
        

    	
        setBounds(20, 20, 600, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }
	
	@Override
	public void setVisible(boolean aFlag) {

		super.setVisible(aFlag);
		
	}
}