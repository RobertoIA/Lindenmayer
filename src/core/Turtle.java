package core;

import gui.Canvas;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Turtle {

	TurtleState currentState;
	List<TurtleState> stack;
	Canvas canvas;
	String state;
	int angle;
	int lineLength;

	Thread drawingThread;

	public Turtle(Canvas canvas) {
		this.canvas = canvas;
	}

	public void setProperties(String state, int angle, int lineLength,
			boolean centered) {
		double canvasWidth = canvas.getSize().width - canvas.getInsets().left
				- canvas.getInsets().right;
		double canvasHeight = canvas.getSize().height
				- canvas.getInsets().bottom - canvas.getInsets().top;

		if (centered)
			this.currentState = new TurtleState(canvasWidth / 2,
					canvasHeight / 2, 90);
		else
			this.currentState = new TurtleState(10, canvasHeight
					+ canvas.getInsets().bottom - 10, 90);

		this.stack = new ArrayList<TurtleState>();
		this.state = state;
		this.angle = angle;
		this.lineLength = lineLength;
	}

	public void draw(final int delay) {
		if (drawingThread != null) {
			try {
				drawingThread.interrupt();
				drawingThread.join();
			} catch (InterruptedException e1) {
				return;
			}
		}

		drawingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					canvas.clear();

					for (char item : state.toCharArray()) {
						if (item == 'F' || item == 'G') {
							double nextX = currentState.x
									+ (lineLength * Math.sin(currentState.angle
											* Math.PI / 180));
							double nextY = currentState.y
									+ (lineLength * Math.cos(currentState.angle
											* Math.PI / 180));
							canvas.addLine(new Line2D.Double(currentState.x,
									currentState.y, nextX, nextY));
							currentState.x = nextX;
							currentState.y = nextY;
							
							TimeUnit.MILLISECONDS.sleep(delay);
						} else if (item == '+') {
							currentState.angle += angle;
						} else if (item == '-') {
							currentState.angle -= angle;
						} else if (item == '[') {

						} else if (item == ']') {

						}

						canvas.draw();
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		});

		drawingThread.start();
	}

	private class TurtleState {

		public double x;
		public double y;
		public int angle;

		private TurtleState(double x, double y, int angle) {
			this.x = x;
			this.y = y;
			this.angle = angle;
		}
	}
}
