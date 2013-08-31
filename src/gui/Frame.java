package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import core.Lindenmayer;

@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {

	private Canvas canvasPanel;
	private JTextField startTextField;
	private JTextArea rulesTextArea;
	private JTextField iterationsTextField;
	private JTextField angleTextField;
	private JTextField lineTextField;

	public Frame() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				setTitle("");

				initUI();

				pack();
				setLocationRelativeTo(null);
				setResizable(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
			}
		});
	}

	public final void initUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel);

		JPanel menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 5)));

		canvasPanel = new Canvas();
		canvasPanel.setBackground(Color.white);
		canvasPanel.setPreferredSize(new Dimension(400, 400));
		canvasPanel.setBorder(new LineBorder(Color.black));

		GridBagConstraints constraints = new GridBagConstraints();

		JLabel titleLabel = new JLabel("Lindenmeyer");
		JLabel startLabel = new JLabel("Start");
		JLabel rulesLabel = new JLabel("Rules");
		JLabel iterationsLabel = new JLabel("Iterations");
		JLabel angleLabel = new JLabel("Angle");
		JLabel lineLabel = new JLabel("Line length");

		startTextField = new JTextField("F");
		// Number of rows overridden by the layout.
		rulesTextArea = new JTextArea(4, 10);
		rulesTextArea.setText("F : F+F-F-F+F");
		iterationsTextField = new JTextField("3");
		angleTextField = new JTextField("90");
		lineTextField = new JTextField("8");
		JButton drawButton = new JButton("Draw");

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
		menuPanel.add(startLabel, constraints);

		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(startTextField, constraints);

		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(rulesLabel, constraints);

		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		rulesTextArea.setLineWrap(true);
		menuPanel.add(new JScrollPane(rulesTextArea), constraints);

		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(iterationsLabel, constraints);

		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(iterationsTextField, constraints);

		constraints.gridy = 8;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(angleLabel, constraints);

		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(angleTextField, constraints);

		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		menuPanel.add(lineLabel, constraints);

		constraints.gridy = 12;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		menuPanel.add(lineTextField, constraints);

		constraints.gridy = 13;
		menuPanel.add(new JSeparator(), constraints);

		constraints.gridy = 14;
		drawButton.addActionListener(this);
		menuPanel.add(drawButton, constraints);

		mainPanel.add(menuPanel);
		mainPanel.add(canvasPanel);
	}

	public Canvas getCanvas() {
		return canvasPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String start = startTextField.getText()
					.replaceAll("\\s","");
			String rules[] = rulesTextArea.getText().split("\\n");
			int iterations = Integer.parseInt(iterationsTextField.getText());
			int angle = Integer.parseInt(angleTextField.getText());
			int lineLength = Integer.parseInt(lineTextField.getText());

			Lindenmayer.calculateLSystem(start, rules, iterations, angle,
					lineLength);
		} catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(canvasPanel,
					"Please review input parameters.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
