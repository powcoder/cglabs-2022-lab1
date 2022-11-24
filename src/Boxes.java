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

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Boxes extends JComponent implements Runnable, ChangeListener {

	/**
	 * Boxes - a simple swing UI. 
	 * Created by Eric McCreath 2019,
	 * Edited by Matthew Aitchison 2022,
	 */

	static final int default_gap = 10;

	JFrame jframe;

	public Boxes() {
		SwingUtilities.invokeLater(this);
	}

	public static void main(String[] args) {
		new Boxes();
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public void run() {
		
		// Boiler plate to get us up and running.
		jframe = new JFrame("Boxes");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// make the main panel
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(this, BorderLayout.CENTER);
		
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
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		
		int gap = Boxes.default_gap;
				
		Graphics2D g = (Graphics2D) graph;
		Dimension dim = this.getSize();
		// fill the background
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.BLACK);

		// draw boxes		
		// ... delete this and replace with your code.
		g.drawRect(10, 10, 180, 180);		
	}
}
