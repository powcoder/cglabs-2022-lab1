https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HelloWorld extends JComponent implements Runnable, ChangeListener {
	/**
	 * HelloWorld - a simple hello world example, with a swing slider control. 
	 * Originally created by Eric McCreath 2019
	 * Updated by Matthew Aitchison 2022
	 */

	static final int gap = 30;

	JFrame jframe;
	JSlider jslider;

	public HelloWorld() {
		SwingUtilities.invokeLater(this);
	}

	public static void main(String[] args) {
		new HelloWorld();
	}

	public Dimension getPreferredSize() {
		return new Dimension(640, 480);
	}

	public void run() {
		
		// Boiler plate to get the program up and running.		
		jframe = new JFrame("Hello World");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set up the slider
		jslider = new JSlider();
		jslider.setMaximum(100);
		jslider.setMinimum(0);
		jslider.addChangeListener(this);

		// make the main panel
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(this, BorderLayout.CENTER);
		mainpanel.add(jslider, BorderLayout.PAGE_END);

		// add panel to jframe and make viewable
		jframe.getContentPane().add(mainpanel);
		jframe.setVisible(true);
		jframe.pack();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {		
		/***
		 * 
		 * A very simple demo of the graphics class.
		 * 
		 * Task: 
		 * 		Modify this method (and others if needed) such that "Hello World" is drawn in the 
		 * 		center of the panel. The slider is used to set the font size of the 
		 * 		"Hello World" text, and the text should range from a font size of 10 to 150. 
		 * 		If the "Hello World" does not fit within the rectangle (which is 30 pixels in set), 
		 * 		then fill the image with a red color and do not draw the text or rectangle. 
		 */
		
		int value = jslider.getValue();
		
		super.paintComponent(g);
		Dimension dim = this.getSize();
		// fill the background
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		
		// draw the border
		g.setColor(Color.BLACK);
		g.drawRect(gap, gap, dim.width - 2*gap, dim.height - 2*gap);
		
		// draw a line		
		// remove this and replace it with the required solution.
		g.drawLine(gap, dim.height/2, gap + value * (dim.width-2*gap) / 100, dim.height/2);
		
		

	}
}
