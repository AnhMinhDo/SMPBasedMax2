package schneiderlab.tools.smpbasedmax2.uicomponents.events;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TooltipTextFieldUpdate implements DocumentListener {
    private JTextField textField;

    public TooltipTextFieldUpdate(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        textField.setToolTipText(textField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        textField.setToolTipText(textField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        textField.setToolTipText(textField.getText());
    }
}
