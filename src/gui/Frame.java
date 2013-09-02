package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import core.Lindenmayer;

/**
 * Implements the application's GUI.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {

	/** Canvas to draw on. */
	private Canvas canvasPanel;
	/** Combo box that contains the examples. */
	private JComboBox<String> examplesBox;
	/** Text field for axioms / starting conditions. */
	private JTextField startTextField;
	/** Text area for rules. */
	private JTextArea rulesTextArea;
	/** Text field for iterations. */
	private JTextField iterationsTextField;
	/** Text field for turning angle. */
	private JTextField angleTextField;
	/** Text field for line length. */
	private JTextField lineTextField;
	/** Button group for origin radio buttons. */
	private ButtonGroup originButtonGroup;
	/** Corner start radio button. */
	private JRadioButton cornerButton;
	/** Center start radio button. */
	private JRadioButton centerButton;
	/** Bottom start radio button. */
	private JRadioButton bottomButton;
	/** Check box for vertical or default start. */
	private JCheckBox verticalCheckBox;

	/** Examples list. */
	private String[] examples = { "Simple Tree", "Koch Curve I",
			"Koch Curve II", "Hilbert Curve", "Sierpinski Triangle", "Cube",
			"Crystal", "Hexagonal Gosper", "Rings", "Dragon Curve", "Seaweed",
			"Fractal Plant", "Penrose Tiling" };

	/**
	 * Constructor.
	 */
	public Frame() {
		setTitle("");

		initUI();

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Creates the UI and all the elements inside.
	 */
	public final void initUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel);

		JPanel menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 5)));

		canvasPanel = new Canvas();
		canvasPanel.setBackground(Color.white);
		canvasPanel.setPreferredSize(new Dimension(550, 550));
		canvasPanel.setBorder(new LineBorder(Color.black));

		GridBagConstraints constraints = new GridBagConstraints();

		JLabel titleLabel = new JLabel("Lindenmayer");
		JLabel examplesLabel = new JLabel("Examples");
		JLabel startLabel = new JLabel("Start");
		JLabel rulesLabel = new JLabel("Rules");
		JLabel iterationsLabel = new JLabel("Iterations");
		JLabel angleLabel = new JLabel("Angle");
		JLabel lineLabel = new JLabel("Line length");
		JLabel originLabel = new JLabel("Origin");

		examplesBox = new JComboBox<String>(examples);
		startTextField = new JTextField();
		// Number of rows overridden by the layout.
		rulesTextArea = new JTextArea(4, 10);
		iterationsTextField = new JTextField();
		angleTextField = new JTextField();
		lineTextField = new JTextField();
		cornerButton = new JRadioButton("corner");
		centerButton = new JRadioButton("center");
		bottomButton = new JRadioButton("bottom");
		originButtonGroup = new ButtonGroup();
		verticalCheckBox = new JCheckBox("Vertical");
		JButton drawButton = new JButton("Draw");

		examplesBox.addActionListener(this);
		examplesBox.setSelectedIndex(0);

		originButtonGroup.add(cornerButton);
		originButtonGroup.add(centerButton);
		originButtonGroup.add(bottomButton);

		constraints.gridx = 0;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.anchor = GridBagConstraints.NORTHWEST;

		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(titleLabel, constraints);

		constraints.gridy = 1;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(examplesLabel, constraints);

		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		// examplesList.setVisibleRowCount(1);
		menuPanel.add(new JScrollPane(examplesBox), constraints);

		constraints.gridy = 4;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(startLabel, constraints);

		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(startTextField, constraints);

		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(rulesLabel, constraints);

		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		rulesTextArea.setLineWrap(true);
		menuPanel.add(new JScrollPane(rulesTextArea), constraints);

		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(iterationsLabel, constraints);

		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(iterationsTextField, constraints);

		constraints.gridy = 11;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 12;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(angleLabel, constraints);

		constraints.gridy = 13;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(angleTextField, constraints);

		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(lineLabel, constraints);

		constraints.gridy = 15;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(lineTextField, constraints);

		constraints.gridy = 16;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 17;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(originLabel, constraints);

		constraints.gridy = 18;
		menuPanel.add(cornerButton, constraints);

		constraints.gridx = 1;
		menuPanel.add(centerButton, constraints);

		constraints.gridy = 19;
		constraints.gridx = 0;
		menuPanel.add(bottomButton, constraints);

		constraints.gridy = 20;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 21;
		menuPanel.add(verticalCheckBox, constraints);

		constraints.gridy = 22;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 23;
		drawButton.addActionListener(this);
		menuPanel.add(drawButton, constraints);

		mainPanel.add(menuPanel);
		mainPanel.add(canvasPanel);
	}

	/**
	 * Returns the canvas object.
	 * 
	 * @return The canvas.
	 */
	public final Canvas getCanvas() {
		return canvasPanel;
	}

	@Override
	public final void actionPerformed(final ActionEvent event) {
		if (event.getActionCommand() == "comboBoxChanged") {
			@SuppressWarnings("unchecked")
			JComboBox<String> examplesBox = (JComboBox<String>) event
					.getSource();
			String example = (String) examplesBox.getSelectedItem();

			// Switch case with Strings. Thanks JDK 7!.
			switch (example) {
			case "Simple Tree":
				startTextField.setText("G");
				rulesTextArea.setText("F:FF\nG:F[+G]-G");
				iterationsTextField.setText("8");
				angleTextField.setText("45");
				lineTextField.setText("2");
				bottomButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Koch Curve I":
				startTextField.setText("F");
				rulesTextArea.setText("F:F+F-F-F+F");
				iterationsTextField.setText("6");
				angleTextField.setText("90");
				lineTextField.setText("2");
				cornerButton.setSelected(true);
				verticalCheckBox.setSelected(false);
				break;
			case "Koch Curve II":
				startTextField.setText("F+F+F+F");
				rulesTextArea.setText("F:F+F-F-FF+F+F-F");
				iterationsTextField.setText("3");
				angleTextField.setText("90");
				lineTextField.setText("2");
				centerButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Hilbert Curve":
				startTextField.setText("X");
				rulesTextArea.setText("X:+YF-XFX-FY+\nY:-XF+YFY+FX-\nF:F");
				iterationsTextField.setText("7");
				angleTextField.setText("90");
				lineTextField.setText("2");
				centerButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Cube":
				startTextField.setText("F+F+F+F");
				rulesTextArea.setText("F:FF+F+F+F+FF");
				iterationsTextField.setText("4");
				angleTextField.setText("90");
				lineTextField.setText("4");
				cornerButton.setSelected(true);
				verticalCheckBox.setSelected(false);
				break;
			case "Crystal":
				startTextField.setText("F+F+F+F");
				rulesTextArea.setText("F:FF+F++F+F");
				iterationsTextField.setText("4");
				angleTextField.setText("90");
				lineTextField.setText("4");
				cornerButton.setSelected(true);
				verticalCheckBox.setSelected(false);
				break;
			case "Hexagonal Gosper":
				startTextField.setText("XF");
				rulesTextArea.setText("X:X+YF++YF-FX--FXFX-YF+"
						+ "\nY:-FX+YFYF++YF+FX--FX-Y\nF:F");
				iterationsTextField.setText("4");
				angleTextField.setText("60");
				lineTextField.setText("4");
				bottomButton.setSelected(true);
				verticalCheckBox.setSelected(false);
				break;
			case "Sierpinski Triangle":
				startTextField.setText("F");
				rulesTextArea.setText("F:G-F-G\nG:F+G+F");
				iterationsTextField.setText("8");
				angleTextField.setText("60");
				lineTextField.setText("2");
				cornerButton.setSelected(true);
				verticalCheckBox.setSelected(false);
				break;
			case "Rings":
				startTextField.setText("F+F+F+F");
				rulesTextArea.setText("F:FF+F+F+F+F+F-F");
				iterationsTextField.setText("4");
				angleTextField.setText("90");
				lineTextField.setText("2");
				centerButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Dragon Curve":
				startTextField.setText("FX");
				rulesTextArea.setText("X:X+YF\nY:FX-Y");
				iterationsTextField.setText("13");
				angleTextField.setText("90");
				lineTextField.setText("3");
				centerButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Seaweed":
				startTextField.setText("F");
				rulesTextArea.setText("F:FF-[-F+F+F]+[+F-F-F]");
				iterationsTextField.setText("4");
				angleTextField.setText("11");
				lineTextField.setText("8");
				bottomButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Fractal Plant":
				startTextField.setText("X");
				rulesTextArea.setText("X:F-[[X]+X]+F[+FX]-X\nF:FF");
				iterationsTextField.setText("6");
				angleTextField.setText("25");
				lineTextField.setText("3");
				bottomButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			case "Penrose Tiling":
				startTextField.setText("[N]++[N]++[N]++[N]++[N]");
				rulesTextArea.setText("M=OF++PF----NF[-OF----MF]++"
						+ "\nN=+OF--PF[---MF--NF]+" + "\nO=-MF++NF[+++OF++PF]-"
						+ "\nP=--OF++++MF[+PF++++NF]--NF\nF=");
				iterationsTextField.setText("6");
				angleTextField.setText("36");
				lineTextField.setText("8");
				centerButton.setSelected(true);
				verticalCheckBox.setSelected(true);
				break;
			default:
				break;
			}

		} else {
			try {
				String start = startTextField.getText().replaceAll("\\s", "");
				String[] rules = rulesTextArea.getText().split("\\n");
				int iterations = Integer
						.parseInt(iterationsTextField.getText());
				int angle = Integer.parseInt(angleTextField.getText());
				int lineLength = Integer.parseInt(lineTextField.getText());

				int startPosition = 1;
				if (!centerButton.isSelected())
					startPosition = cornerButton.isSelected() ? 2 : 3;

				Lindenmayer.calculateLSystem(start, rules, iterations, angle,
						lineLength, startPosition,
						verticalCheckBox.isSelected());

			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(canvasPanel,
						"Please review input parameters.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
