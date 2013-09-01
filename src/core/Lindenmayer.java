package core;

import gui.Canvas;
import gui.Frame;

import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lindenmayer {

	static Frame frame;

	public static void main(String[] args) {
		frame = new Frame();
	}

	public static void calculateLSystem(String start, String[] rules,
			int iterations, int angle, int lineLength, boolean centered) {
		String state = start;
		Map<Character, char[]> parsedRules = parseRules(rules);

		for (int i = 0; i < iterations; i++) {
			state = applyRules(parsedRules, state);
		}
		
		drawState(state, angle, lineLength, centered);
	}

	private static Map<Character, char[]> parseRules(String[] rules) {
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

	private static String applyRules(Map<Character, char[]> rules, String state) {
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

	private static void drawState(String state, int angle, int lineLength,
			boolean centered) {
		Canvas canvas = frame.getCanvas();
		int currAngle = 90;
		double currX;
		double currY;
		
		if(centered) {
			currX = (canvas.getSize().width -
					canvas.getInsets().left - canvas.getInsets().right) / 2;
			currY = (canvas.getSize().height -
					canvas.getInsets().bottom - canvas.getInsets().top) / 2;
		} else {
			currX = 10;
			currY = canvas.getSize().height -
					canvas.getInsets().bottom - canvas.getInsets().top - 10;
		}
		
		canvas.clear();

		for (char item : state.toCharArray()) {
			if (item == 'F' || item == 'G') {
				double nextX = currX +
						(lineLength * Math.sin(currAngle * Math.PI / 180));
				double nextY = currY +
						(lineLength * Math.cos(currAngle * Math.PI / 180));
				canvas.addLine(new Line2D.Double(
						currX, currY, nextX, nextY));
				currX = nextX;
				currY = nextY;
			} else if (item == '+') {
				currAngle += angle;
			} else if (item == '-') {
				currAngle -= angle;
			} else if (item == '[') {

			} else if (item == ']') {

			}
		}
		
		canvas.draw();
	}
}
