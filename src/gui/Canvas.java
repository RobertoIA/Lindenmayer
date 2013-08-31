package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	private List<Line2D> lines;

	public void addLine(Line2D line) {
		lines.add(line);
	}

	public void draw() {
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setColor(Color.blue);

		for (Line2D line : lines)
			graphics2D.drawLine((int) line.getX1(), (int) line.getY1(),
					(int) line.getX2(), (int) line.getY2());
	}
	
	public void clear() {
		lines = new ArrayList<Line2D>();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		lines = new ArrayList<Line2D>();
		draw();
	}
}
