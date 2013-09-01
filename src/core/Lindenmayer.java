package core;

import gui.Frame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements rule parsing and application for L-Systems.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class Lindenmayer {

	/** GUI frame. */
	private static Frame frame;
	/** Turtle graphics controller. */
	private static Turtle turtle;

	/**
	 * Creates GUI and Turtle graphics controller.
	 * 
	 * @param args
	 *            Program args, ignored.
	 */
	public static void main(final String[] args) {
		frame = new Frame();
		turtle = new Turtle(frame.getCanvas());
	}

	/**
	 * Calculates and draws an L-System.
	 * 
	 * @param start
	 *            Axiom.
	 * @param rules
	 *            Rules to apply.
	 * @param iterations
	 *            Number of iterations to calculate.
	 * @param angle
	 *            Turning angle.
	 * @param lineLength
	 *            Length of each drawn line.
	 * @param startPosition
	 *            Starting position (1 center, 2 corner, 3 bottom).
	 * @param vertical
	 *            True if the starting angle is vertical.
	 */
	public static void calculateLSystem(final String start,
			final String[] rules, final int iterations, final int angle,
			final int lineLength, final int startPosition,
			final boolean vertical) {
		String state = start;
		Map<Character, char[]> parsedRules = parseRules(rules);

		for (int i = 0; i < iterations; i++) {
			state = applyRules(parsedRules, state);
		}

		turtle.setProperties(state, angle, lineLength, startPosition, vertical);
		turtle.draw(2);
	}

	/**
	 * Parses user input and returns a map of rules to apply.
	 * 
	 * @param rules
	 *            Rules input.
	 * @return Map of productions.
	 */
	private static Map<Character, char[]> parseRules(final String[] rules) {
		Map<Character, char[]> parsedRules = new HashMap<Character, char[]>();

		for (String rule : rules) {
			rule = rule.replaceAll("[:\\s]", "");
			char[] parsedRule = rule.toCharArray();
			char constant = parsedRule[0];
			char[] production = Arrays.copyOfRange(parsedRule, 1,
					parsedRule.length);
			parsedRules.put(constant, production);

		}

		return parsedRules;
	}

	/**
	 * Calculates another iteration of the system.
	 * 
	 * @param rules
	 *            System rules.
	 * @param state
	 *            Current state of the system.
	 * @return Next iteration of the system.
	 */
	private static String applyRules(final Map<Character, char[]> rules,
			final String state) {
		String newState = "";

		for (char item : state.toCharArray()) {
			if (rules.get(item) != null) {
				String p = new String(rules.get(item));
				newState += p;
			} else {
				newState += item;
			}
		}

		return newState;
	}

	/**
	 * Empty constructor.
	 */
	private Lindenmayer() {

	}
}
