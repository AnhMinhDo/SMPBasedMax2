package schneiderlab.tools.smpbasedmax2.uicomponents.events;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProcessModeGroupButtonStateListener implements ItemListener {
    private JButton browseButtonSingleFile;
    private JButton browseButtonDirectory;
    private JTextField textFieldSingleFile;
    private JTextField textFieldDirectory;
    private JRadioButton singleModeButton;
    private JRadioButton previewModeButton;
    private JRadioButton multipleFilesModeButton;

    public ProcessModeGroupButtonStateListener(JButton browseButtonSingleFile,
                                               JButton browseButtonDirectory,
                                               JTextField textFieldSingleFile,
                                               JTextField textFieldDirectory,
                                               JRadioButton singleModeButton,
                                               JRadioButton previewModeButton,
                                               JRadioButton multipleFilesModeButton) {
        this.browseButtonSingleFile = browseButtonSingleFile;
        this.browseButtonDirectory = browseButtonDirectory;
        this.textFieldSingleFile = textFieldSingleFile;
        this.textFieldDirectory = textFieldDirectory;
        this.singleModeButton = singleModeButton;
        this.previewModeButton = previewModeButton;
        this.multipleFilesModeButton = multipleFilesModeButton;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(singleModeButton.isSelected() || previewModeButton.isSelected()){
            browseButtonDirectory.setEnabled(false);
            browseButtonSingleFile.setEnabled(true);
            textFieldSingleFile.setEnabled(true);
            textFieldDirectory.setEnabled(false);
        } else if(multipleFilesModeButton.isSelected()){
            browseButtonDirectory.setEnabled(true);
            browseButtonSingleFile.setEnabled(false);
            textFieldSingleFile.setEnabled(false);
            textFieldDirectory.setEnabled(true);
        }
    }
}
