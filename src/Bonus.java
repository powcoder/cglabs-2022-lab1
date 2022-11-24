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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Bonus extends JComponent implements Runnable, ChangeListener {
	/**
	 * Starfield example. 
	 * Created by Matthew Aitchison 2022
	 * 
	 * This is completely optional... code up your own animation / picture
	 * using *only* java graphics (no Graphics2D). If we like it it may even be shown in the
	 * next lecture. For inspiration here is my take on a starfield animation.
	 *  
	 * Perhaps you could make the stars rotate as they fly past?
	 */

	static final int gap = 30;

	JFrame jframe;
	JSlider jslider;

	public Bonus() {
		SwingUtilities.invokeLater(this);
	}

	public static void main(String[] args) {
		new Bonus();
	}

	public Dimension getPreferredSize() {
		return new Dimension(640, 480);
	}

	public void run() {
		/**
		 * Boiler plate to get the program up and running.
		 */
		jframe = new JFrame("Bonus");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set up the slider
		jslider = new JSlider();
		jslider.setMaximum(100);
		jslider.setMinimum(1);
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
		
		initializeStars();	
		
		// This is a bit of magic to make the window automatically update (approximately) 60 times per second.
		Timer t = new Timer(1000/60, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	invalidate();		    	
		        repaint();		       
		        // I need this on Ubuntu in order to get a good frame rate.
		        getToolkit().sync();
		    }
		});
		t.start();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		//repaint();
	}
	
	// normally I would think about creating a class for this, but 
	// a series of arrays is fine for this demo.
	private static int stars = 1000;
	float starX[] = new float[stars];
	float starY[] = new float[stars];
	float starZ[] = new float[stars];
	float starV[] = new float[stars];
		
	private void initializeStars() {
		/**
		 * Setup the initial star locations to be random.
		 */
		for (int i = 0; i < stars; i++) {
			// stars exist in a cube from [-1 ... 1]
			starX[i] = (float)Math.random()*2.0f-1.0f;
			starY[i] = (float)Math.random()*2.0f-1.0f;
			starZ[i] = (float)Math.random()*2.0f-1.0f;
			// each star has a slightly different speed.
			starV[i] = (float)Math.random() * 0.001f + 0.004f;
		}		
	}
	
	private int[] project(float x, float y, float z) {
		/**
		 * Project from 3d down to 2d.
		 */
		
		Dimension dim = this.getSize();
		int[] result = new int[2];
		result[0] = (int)(x * 250 / (z + 1.0)) + dim.width/2;
		result[1] = (int)(y * 250 / (z + 1.0)) + dim.height/2;
		return result;
	}
	
	private void drawStar(Graphics g, float x, float y, float z, float length) {
		/**
		 * Draw a star with a given 'streak' length.
		 */
		 
		int[] p1 = project(x, y, z);		
		int[] p2 = project(x, y, z + length);
		float c = Math.max(0.0f,  Math.min(1.0f, - z + 0.5f));
		g.setColor(new Color(c, c, c));
		g.drawLine(p1[0], p1[1], p2[0], p2[1]);
	}

	@Override
	protected void paintComponent(Graphics g) {		
		/**
		 * Repaint the surface and perform our update. 
		 */
		
		int value = jslider.getValue();
		
		super.paintComponent(g);
		Dimension dim = this.getSize();
		
		// fill the background
		g.setColor(Color.black);
		g.fillRect(0, 0, dim.width, dim.height);

		// Speed is modified by the slider value.
		// If I was doing this properly I would calculate the amount of real time that has passed and 
		// incorporate this into the speed. This way the stars travel at the same speed irrespective of the frame rate.
		float speedFactor = (value * value) / 500f;
		
		// draw each star
		g.setColor(Color.white);
		for (int i = 0; i < stars; i++) {
			float speed = starV[i] * speedFactor;			
			drawStar(g, starX[i], starY[i], starZ[i], 0.5f * speed);
			// normally we should separate out the drawing and the updating, but for simplicity 
			// I'll put it all here.
			starZ[i] -= speed; 			
			if (starZ[i] < -1) {
				// when a star has gone outside of our bounds we but it back at the start.
				// I could re-randomize the star, but it looks ok as is.
				starZ[i] = 1;
			}			
			
		}
				
	}
}
