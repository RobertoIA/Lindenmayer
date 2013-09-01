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

@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {

	private Canvas canvasPanel;
	private JComboBox<String> examplesBox;
	private JTextField startTextField;
	private JTextArea rulesTextArea;
	private JTextField iterationsTextField;
	private JTextField angleTextField;
	private JTextField lineTextField;
	private ButtonGroup originButtonGroup;
	private JRadioButton cornerButton;
	private JRadioButton centerButton;

	private String[] examples = { "Koch Curve", "Sierpinski Triangle",
			"Dragon Curve", "Fractal Plant" };

	public Frame() {
		setTitle("");

		initUI();

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public final void initUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel);

		JPanel menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 5)));

		canvasPanel = new Canvas();
		canvasPanel.setBackground(Color.white);
		canvasPanel.setPreferredSize(new Dimension(500, 500));
		canvasPanel.setBorder(new LineBorder(Color.black));

		GridBagConstraints constraints = new GridBagConstraints();

		JLabel titleLabel = new JLabel("Lindenmeyer");
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
		originButtonGroup = new ButtonGroup();
		JButton drawButton = new JButton("Draw");

		examplesBox.addActionListener(this);
		examplesBox.setSelectedIndex(0);

		originButtonGroup.add(cornerButton);
		originButtonGroup.add(centerButton);

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
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 20;
		drawButton.addActionListener(this);
		menuPanel.add(drawButton, constraints);

		mainPanel.add(menuPanel);
		mainPanel.add(canvasPanel);
	}

	public Canvas getCanvas() {
		return canvasPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "comboBoxChanged") {
			@SuppressWarnings("unchecked")
			JComboBox<String> examplesBox = (JComboBox<String>) event
					.getSource();
			String example = (String) examplesBox.getSelectedItem();

			// Switch case with Strings. Thanks JDK 7!.
			switch (example) {
			case "Koch Curve":
				startTextField.setText("F");
				rulesTextArea.setText("F:F+F-F-F+F");
				iterationsTextField.setText("6");
				angleTextField.setText("90");
				lineTextField.setText("2");
				cornerButton.setSelected(true);
				break;
			case "Sierpinski Triangle":
				startTextField.setText("F");
				rulesTextArea.setText("F:G-F-G\nG:F+G+F");
				iterationsTextField.setText("8");
				angleTextField.setText("60");
				lineTextField.setText("2");
				cornerButton.setSelected(true);
				break;
			case "Dragon Curve":
				startTextField.setText("FX");
				rulesTextArea.setText("X:X+YF\nY:FX-Y");
				iterationsTextField.setText("13");
				angleTextField.setText("90");
				lineTextField.setText("3");
				centerButton.setSelected(true);
				break;
			case "Fractal Plant":
				startTextField.setText("X");
				rulesTextArea.setText("X:F-[[X]+X]+F[+FX]-X\nF:FF");
				iterationsTextField.setText("");
				angleTextField.setText("25");
				lineTextField.setText("");
				cornerButton.setSelected(true);
				break;
			}

		} else {
			try {
				String start = startTextField.getText().replaceAll("\\s", "");
				String rules[] = rulesTextArea.getText().split("\\n");
				int iterations = Integer
						.parseInt(iterationsTextField.getText());
				int angle = Integer.parseInt(angleTextField.getText());
				int lineLength = Integer.parseInt(lineTextField.getText());

				Lindenmayer.calculateLSystem(start, rules, iterations, angle,
						lineLength, centerButton.isSelected());
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(canvasPanel,
						"Please review input parameters.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
