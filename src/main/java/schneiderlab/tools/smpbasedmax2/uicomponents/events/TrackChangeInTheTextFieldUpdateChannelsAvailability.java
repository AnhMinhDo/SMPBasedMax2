package schneiderlab.tools.smpbasedmax2.uicomponents.events;

import ij.ImagePlus;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TrackChangeInTheTextFieldUpdateChannelsAvailability implements DocumentListener {
    private JTextField textField;
    private JComboBox comboBox;
    private JCheckBox checkBox;

    public TrackChangeInTheTextFieldUpdateChannelsAvailability(JTextField textField, JComboBox comboBox, JCheckBox checkBox) {
        this.textField = textField;
        this.comboBox = comboBox;
        this.checkBox = checkBox;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        ImagePlus inputFile = new ImagePlus(textField.getText());
        int numOfChannels = inputFile.getNChannels();
        comboBox.removeAllItems();
        for (int i = 0; i < numOfChannels; i++) {
            comboBox.addItem("channels " + (i+1));
        }
        comboBox.setSelectedIndex(0);
        if(numOfChannels > 1){
            checkBox.setSelected(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
