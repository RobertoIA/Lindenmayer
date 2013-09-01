package core;

import gui.Frame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lindenmayer {

	static Frame frame;
	static Turtle turtle;

	public static void main(String[] args) {
		frame = new Frame();
		turtle = new Turtle(frame.getCanvas());
	}

	public static void calculateLSystem(String start, String[] rules,
			int iterations, int angle, int lineLength, boolean centered) {
		String state = start;
		Map<Character, char[]> parsedRules = parseRules(rules);

		for (int i = 0; i < iterations; i++) {
			state = applyRules(parsedRules, state);
		}

		turtle.setProperties(state, angle, lineLength, centered);
		turtle.draw(3);
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
}
