package schneiderlab.tools.smpbasedmax2.uicomponents;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import schneiderlab.tools.smpbasedmax2.uicomponents.events.*;
/*
 * Created by JFormDesigner on Mon Jun 09 23:41:48 CEST 2025
 */



/**
 * @author anhminh
 */
public class MainDialog extends JFrame {
	private JFrame parentFrame;

	public MainDialog(JFrame parentFrame) {
		initComponents();
		this.parentFrame = parentFrame;

		// Action to show the checkbox list for output type
		button3.addActionListener(new ShowPopUpMenu(button3,popupMenu1));

		// for toggleButton switching light and dark mode
		toggleButton1.addActionListener(new ToggleLightDarkMode(toggleButton1, parentFrame));

		// for button browse button directory
		button1.addActionListener(new SelectDirDisplayInTextField("",
				parentFrame,
				textField1));

		// for button browse button file
		button2.addActionListener(new SelectFileDisplayInTextField("",
				parentFrame,
				textField2));

		// Cancel button
		button5.addActionListener(new CloseMainWindow(parentFrame));

		// Add tooltip to textFields to view full file path
		textField1.addMouseListener(new TooltipTextField(textField1));
		textField2.addMouseListener(new TooltipTextField(textField2));

		// SingleMode radio Button
		radioButton1.addItemListener(new ProcessModeGroupButtonStateListener(
				button2,
				button1,
				textField2,
				textField1,
				radioButton1,
				radioButton3,
				radioButton2

		));
		// Preview Mode radio Button
		radioButton3.addItemListener(new ProcessModeGroupButtonStateListener(
				button2,
				button1,
				textField2,
				textField1,
				radioButton1,
				radioButton3,
				radioButton2));
		// Multiple File Mode radio button
		radioButton2.addItemListener(new ProcessModeGroupButtonStateListener(
				button2,
				button1,
				textField2,
				textField1,
				radioButton1,
				radioButton3,
				radioButton2
		));

	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Anh Minh Do
		label1 = new JLabel();
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		radioButton3 = new JRadioButton();
		toggleButton1 = new JToggleButton();
		label2 = new JLabel();
		comboBox1 = new JComboBox<>();
		label3 = new JLabel();
		spinner1 = new JSpinner();
		label4 = new JLabel();
		spinner2 = new JSpinner();
		spinner3 = new JSpinner();
		label8 = new JLabel();
		label6 = new JLabel();
		spinner4 = new JSpinner();
		label7 = new JLabel();
		button1 = new JButton();
		textField1 = new JTextField();
		label9 = new JLabel();
		button2 = new JButton();
		textField2 = new JTextField();
		checkBox1 = new JCheckBox();
		label10 = new JLabel();
		comboBox2 = new JComboBox();
		label11 = new JLabel();
		button3 = new JButton();
		progressBar1 = new JProgressBar();
		button4 = new JButton();
		button5 = new JButton();
		popupMenu1 = new JPopupMenu();
		checkBoxMenuItem8 = new JCheckBoxMenuItem();
		checkBoxMenuItem9 = new JCheckBoxMenuItem();
		checkBoxMenuItem10 = new JCheckBoxMenuItem();
		checkBoxMenuItem11 = new JCheckBoxMenuItem();
		checkBoxMenuItem12 = new JCheckBoxMenuItem();
		checkBoxMenuItem13 = new JCheckBoxMenuItem();
		buttonGroup1 = new ButtonGroup();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3,align center top",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label1 ----
		label1.setText("Process Mode:");
		contentPane.add(label1, "cell 0 0");

		//---- radioButton1 ----
		radioButton1.setText("SINGLE_FILE");
		radioButton1.setSelected(true);
		radioButton1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton1.setIconTextGap(20);
		contentPane.add(radioButton1, "cell 1 0");

		//---- radioButton2 ----
		radioButton2.setText("MULTIPLE_FILES");
		radioButton2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(radioButton2, "cell 2 0");

		//---- radioButton3 ----
		radioButton3.setText("PREVIEW");
		radioButton3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(radioButton3, "cell 3 0");

		//---- toggleButton1 ----
		toggleButton1.setIcon(new ImageIcon(getClass().getResource("/iconimages/light_mode_36dp_000000_FILL0_wght400_GRAD0_opsz40.png")));
		toggleButton1.setSelectedIcon(new ImageIcon(getClass().getResource("/iconimages/dark_mode_36dp_FFFFFF_FILL0_wght400_GRAD0_opsz40.png")));
		toggleButton1.setBorderPainted(false);
		contentPane.add(toggleButton1, "cell 4 0");

		//---- label2 ----
		label2.setText("Direction of Z stack: ");
		contentPane.add(label2, "cell 0 1 3 1,alignx right,growx 0");

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"IN",
			"OUT"
		}));
		contentPane.add(comboBox1, "cell 3 1,alignx left,growx 0");

		//---- label3 ----
		label3.setText("Enter Envelope stiffness [pixels]:");
		label3.setLabelFor(spinner1);
		contentPane.add(label3, "cell 0 2 3 1,alignx right,growx 0");

		//---- spinner1 ----
		spinner1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPane.add(spinner1, "cell 3 2");

		//---- label4 ----
		label4.setText("Enter final Filter size [pixel]:");
		label4.setLabelFor(spinner2);
		contentPane.add(label4, "cell 0 3 3 1,alignx right,growx 0");

		//---- spinner2 ----
		spinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPane.add(spinner2, "cell 3 3");

		//---- spinner3 ----
		spinner3.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPane.add(spinner3, "cell 3 4");

		//---- label8 ----
		label8.setText("Offset-N planes above(+) or below(-) blanket [pixels]:");
		contentPane.add(label8, "cell 0 4 3 1,alignx right,growx 0");

		//---- label6 ----
		label6.setText("Depth:MIP for N pixels into blanket [pixels]:");
		label6.setLabelFor(spinner4);
		contentPane.add(label6, "cell 0 5 3 1,alignx right,growx 0");

		//---- spinner4 ----
		spinner4.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPane.add(spinner4, "cell 3 5");

		//---- label7 ----
		label7.setText("Directory for MULTIPLE_FILES:");
		contentPane.add(label7, "cell 0 6 2 1,alignx right,growx 0");

		//---- button1 ----
		button1.setText("Browse");
		button1.setEnabled(false);
		contentPane.add(button1, "cell 2 6");

		//---- textField1 ----
		textField1.setEnabled(false);
		textField1.setEditable(false);
		contentPane.add(textField1, "cell 3 6");

		//---- label9 ----
		label9.setText("File path for SINGLE FILE:");
		contentPane.add(label9, "cell 0 7 2 1,alignx right,growx 0");

		//---- button2 ----
		button2.setText("Browse");
		contentPane.add(button2, "cell 2 7");

		//---- textField2 ----
		textField2.setEditable(false);
		contentPane.add(textField2, "cell 3 7");

		//---- checkBox1 ----
		checkBox1.setText("Project Additional Channels:");
		contentPane.add(checkBox1, "cell 0 8 2 1");

		//---- label10 ----
		label10.setText("Select Reference");
		contentPane.add(label10, "cell 2 8");
		contentPane.add(comboBox2, "cell 3 8");

		//---- label11 ----
		label11.setText("Choose Output File type:");
		contentPane.add(label11, "cell 0 9 3 1,alignx right,growx 0");

		//---- button3 ----
		button3.setText("select output");
		button3.setContentAreaFilled(false);
		contentPane.add(button3, "cell 3 9");
		contentPane.add(progressBar1, "cell 1 10");

		//---- button4 ----
		button4.setText("Start Process");
		contentPane.add(button4, "cell 2 10");

		//---- button5 ----
		button5.setText("Cancel");
		contentPane.add(button5, "cell 3 10");
		pack();
		setLocationRelativeTo(getOwner());

		//======== popupMenu1 ========
		{

			//---- checkBoxMenuItem8 ----
			checkBoxMenuItem8.setText("MIP");
			popupMenu1.add(checkBoxMenuItem8);

			//---- checkBoxMenuItem9 ----
			checkBoxMenuItem9.setText("MIP z map");
			popupMenu1.add(checkBoxMenuItem9);

			//---- checkBoxMenuItem10 ----
			checkBoxMenuItem10.setText("SMP ");
			popupMenu1.add(checkBoxMenuItem10);

			//---- checkBoxMenuItem11 ----
			checkBoxMenuItem11.setText("SMP z map");
			popupMenu1.add(checkBoxMenuItem11);

			//---- checkBoxMenuItem12 ----
			checkBoxMenuItem12.setText("depth-adjusted SMP ");
			popupMenu1.add(checkBoxMenuItem12);

			//---- checkBoxMenuItem13 ----
			checkBoxMenuItem13.setText("depth-adjusted SMP z map");
			popupMenu1.add(checkBoxMenuItem13);
		}

		//---- buttonGroup1 ----
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);
		buttonGroup1.add(radioButton3);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Anh Minh Do
	private JLabel label1;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JToggleButton toggleButton1;
	private JLabel label2;
	private JComboBox<String> comboBox1;
	private JLabel label3;
	private JSpinner spinner1;
	private JLabel label4;
	private JSpinner spinner2;
	private JSpinner spinner3;
	private JLabel label8;
	private JLabel label6;
	private JSpinner spinner4;
	private JLabel label7;
	private JButton button1;
	private JTextField textField1;
	private JLabel label9;
	private JButton button2;
	private JTextField textField2;
	private JCheckBox checkBox1;
	private JLabel label10;
	private JComboBox comboBox2;
	private JLabel label11;
	private JButton button3;
	private JProgressBar progressBar1;
	private JButton button4;
	private JButton button5;
	private JPopupMenu popupMenu1;
	private JCheckBoxMenuItem checkBoxMenuItem8;
	private JCheckBoxMenuItem checkBoxMenuItem9;
	private JCheckBoxMenuItem checkBoxMenuItem10;
	private JCheckBoxMenuItem checkBoxMenuItem11;
	private JCheckBoxMenuItem checkBoxMenuItem12;
	private JCheckBoxMenuItem checkBoxMenuItem13;
	private ButtonGroup buttonGroup1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
