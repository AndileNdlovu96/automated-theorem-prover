package za.ac.uj.acsse.csc3a.gui;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author Andile Ndlovu
 *
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	public Frame(String title) {
		super(title);
		
		Container container = new Container();
		container.setBackground(Color.BLACK);
		add(container);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		setResizable(true);
	}
	
	
}
