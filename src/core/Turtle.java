package core;

import gui.Canvas;

import java.awt.geom.Line2D;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * Implements simple turtle graphics.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class Turtle {

	/** Minimum distance from the border to start drawing from. */
	private static final int OFFSET = 10;
	/** Vertical starting angle. */
	private static final int V_ANGLE = 180;
	/** Horizontal starting angle. */
	private static final int H_ANGLE = 90;
	/** Factor to convert degrees to radians. */
	private static final double TO_RADIANS = Math.PI / 180;

	/** Current turtle state. */
	private TurtleState currentState;
	/** States stack. */
	private Stack<TurtleState> stack;
	/** Canvas to draw on. */
	private Canvas canvas;
	/** State to draw. */
	private String state;
	/** Turning angle. */
	private int angle;
	/** Drawing length for each line. */
	private int lineLength;

	/** Thread for drawing. */
	private Thread drawingThread;

	/**
	 * Constructor.
	 * 
	 * @param canvas
	 *            Canvas to draw on.
	 */
	public Turtle(final Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Changes the properties, used to start a new drawing.
	 * 
	 * @param state
	 *            System state to draw.
	 * @param angle
	 *            Turning angle.
	 * @param lineLength
	 *            Drawing length for each line.
	 * @param startPosition
	 *            Starting position (1 center, 2 corner, 3 bottom).
	 * @param vertical
	 *            True if the starting angle is vertical.
	 */
	public final void setProperties(final String state, final int angle,
			final int lineLength, final int startPosition,
			final boolean vertical) {
		double canvasWidth = canvas.getSize().width - canvas.getInsets().left
				- canvas.getInsets().right;
		double canvasHeight = canvas.getSize().height
				- canvas.getInsets().bottom - canvas.getInsets().top;
		int startAngle = vertical ? V_ANGLE : H_ANGLE;

		if (startPosition == 1) // Center
			this.currentState = new TurtleState(canvasWidth / 2,
					canvasHeight / 2, startAngle);
		else if (startPosition == 2) // Corner
			this.currentState = new TurtleState(OFFSET, canvasHeight
					+ canvas.getInsets().bottom - OFFSET, startAngle);
		else
			// Bottom
			this.currentState = new TurtleState(canvasWidth / 2, canvasHeight
					+ canvas.getInsets().bottom - OFFSET, startAngle);

		this.stack = new Stack<TurtleState>();
		this.state = state;
		this.angle = angle;
		this.lineLength = lineLength;
	}

	/**
	 * Draws the current system, tick by tick.
	 * 
	 * @param delay
	 *            Time between ticks.
	 */
	public final void draw(final int delay) {
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
											* TO_RADIANS));
							double nextY = currentState.y
									+ (lineLength * Math.cos(currentState.angle
											* TO_RADIANS));
							canvas.addLine(new Line2D.Double(currentState.x,
									currentState.y, nextX, nextY));
							currentState = new TurtleState(nextX, nextY,
									currentState.angle);

							TimeUnit.MILLISECONDS.sleep(delay);
						} else if (item == '+') {
							int nextAngle = currentState.angle + angle;
							currentState = new TurtleState(currentState.x,
									currentState.y, nextAngle);
						} else if (item == '-') {
							int nextAngle = currentState.angle - angle;
							currentState = new TurtleState(currentState.x,
									currentState.y, nextAngle);
						} else if (item == '[') {
							stack.push(currentState);
						} else if (item == ']') {
							currentState = stack.pop();
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

	/**
	 * Stores the state of the turtle in a given moment.
	 * 
	 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo
	 *         Amo</a>
	 * 
	 */
	private final class TurtleState {

		/** X coordinate. */
		public double x;
		/** Y coordinate. */
		public double y;
		/** Angle. */
		public int angle;

		/**
		 * Constructor.
		 * 
		 * @param x
		 *            X coordinate.
		 * @param y
		 *            Y coordinate.
		 * @param angle
		 *            Angle.
		 */
		private TurtleState(final double x, final double y, final int angle) {
			this.x = x;
			this.y = y;
			this.angle = angle;
		}
	}
}
