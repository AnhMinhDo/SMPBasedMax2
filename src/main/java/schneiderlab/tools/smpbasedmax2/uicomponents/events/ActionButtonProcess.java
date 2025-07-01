package schneiderlab.tools.smpbasedmax2.uicomponents.events;

import schneiderlab.tools.smpbasedmax2.ProcessingMode;
import schneiderlab.tools.smpbasedmax2.ZStackDirection;
import schneiderlab.tools.smpbasedmax2.uicomponents.MainDialog;
import schneiderlab.tools.smpbasedmax2.uicomponents.workers.ProcessPreviewFileWorker;
import schneiderlab.tools.smpbasedmax2.uicomponents.workers.ProcessSingleFileWorker;
import schneiderlab.tools.smpbasedmax2.uicomponents.workers.ProcessingWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonProcess implements ActionListener {
    private JTextField statusBar;
    private ButtonGroup processingModeRadioButtonGroup;
    private JComboBox zStackDirectionCombobox;
    private JSpinner stiffness;
    private JSpinner filterSize;
    private JSpinner offset;
    private JSpinner depth;
    private JTextField filePathTextField;
    private JCheckBox otherChannelProjectionSingleMode;
    private JComboBox comboBoxSingleMode;
    private JCheckBox otherChannelProjectionBatchMode;
    private JComboBox comboBoxBatchMode;
    private JCheckBox otherChannelProjectionPreviewMode;
    private JComboBox comboBoxPreviewMode;
    private boolean hasManyChannels;
    private int referenceChannelIdx;
    private JProgressBar progressBar;
    private JFrame parent;
    private MainDialog mainDialogView;
    private ProcessingMode processingMode;
    private ZStackDirection zStackDirection;

    public ActionButtonProcess(JTextField statusBar,
                               ButtonGroup processingModeRadioButtonGroup,
                               JComboBox zStackDirectionCombobox,
                               JSpinner stiffness,
                               JSpinner filterSize,
                               JSpinner offset,
                               JSpinner depth,
                               JTextField filePathTextField,
                               JCheckBox otherChannelProjectionSingleMode,
                               JComboBox comboBoxSingleMode,
                               JCheckBox otherChannelProjectionBatchMode,
                               JComboBox comboBoxBatchMode,
                               JCheckBox otherChannelProjectionPreviewMode,
                               JComboBox comboBoxPreviewMode,
                               JProgressBar progressBar,
                               JFrame parent,
                               MainDialog mainDialogView) {
        this.statusBar = statusBar;
        this.processingModeRadioButtonGroup = processingModeRadioButtonGroup;
        this.zStackDirectionCombobox = zStackDirectionCombobox;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.filePathTextField = filePathTextField;
        this.otherChannelProjectionSingleMode = otherChannelProjectionSingleMode;
        this.comboBoxSingleMode = comboBoxSingleMode;
        this.otherChannelProjectionBatchMode = otherChannelProjectionBatchMode;
        this.comboBoxBatchMode = comboBoxBatchMode;
        this.otherChannelProjectionPreviewMode = otherChannelProjectionPreviewMode;
        this.comboBoxPreviewMode = comboBoxPreviewMode;
        this.progressBar = progressBar;
        this.parent = parent;
        this.mainDialogView = mainDialogView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setProcessingMode();
        setzStackDirection();
        progressBar.setValue(0);
        statusBar.setText("processing...");
        if (processingMode==ProcessingMode.SINGLE_FILE){
            this.hasManyChannels = otherChannelProjectionSingleMode.isSelected();
            this.referenceChannelIdx = comboBoxSingleMode.getSelectedIndex();
            ProcessSingleFileWorker processSingleFileWorker =
                    new ProcessSingleFileWorker((int)stiffness.getValue(),
                            (int)filterSize.getValue(),
                            zStackDirection,
                            (int) offset.getValue(),
                            (int) depth.getValue(),
                            filePathTextField.getText(),
                            statusBar,
                            hasManyChannels,
                            referenceChannelIdx,
                            progressBar);
            processSingleFileWorker.addPropertyChangeListener(evt ->
            {
                if ("progress".equals(evt.getPropertyName())){
                    int value = (int) evt.getNewValue();
                    progressBar.setValue(value);
                    progressBar.setToolTipText(value + "%");
                }
            });
            processSingleFileWorker.execute();
        } else if (processingMode==ProcessingMode.PREVIEW) {
            this.hasManyChannels = otherChannelProjectionPreviewMode.isSelected();
            this.referenceChannelIdx = comboBoxPreviewMode.getSelectedIndex();
            ProcessPreviewFileWorker processPreviewFileWorker =
                    new ProcessPreviewFileWorker((int)stiffness.getValue(),
                            (int)filterSize.getValue(),
                            zStackDirection,
                            (int) offset.getValue(),
                            (int) depth.getValue(),
                            filePathTextField.getText(),
                            statusBar,
                            hasManyChannels,
                            referenceChannelIdx,
                            progressBar,
                            parent,
                            mainDialogView);
            processPreviewFileWorker.addPropertyChangeListener(evt ->
            {
                if ("progress".equals(evt.getPropertyName())){
                    int value = (int) evt.getNewValue();
                    progressBar.setValue(value);
                    progressBar.setToolTipText(value + "%");
                }
            });
            processPreviewFileWorker.execute();
        } else if (processingMode==ProcessingMode.MULTIPLE_FILES) {
            this.hasManyChannels = otherChannelProjectionBatchMode.isSelected();
            this.referenceChannelIdx = comboBoxBatchMode.getSelectedIndex();
            // TODO: CALL THE BATCH WORKER
            statusBar.setText("Feature has not been implemented!");
        }
    }

    public void setzStackDirection(){
        String zStackDirectionstring = (String) zStackDirectionCombobox.getSelectedItem();
        if(zStackDirectionstring.equals("IN")){
            zStackDirection = ZStackDirection.IN;
        } else if (zStackDirectionstring.equals("OUT")) {
            zStackDirection = ZStackDirection.OUT;
        }
    }

    public void setProcessingMode(){
        String processingModeString = processingModeRadioButtonGroup.getSelection().getActionCommand();
        if(processingModeString.equals("SINGLE_FILE")){
            processingMode = ProcessingMode.SINGLE_FILE;
        } else if (processingModeString.equals("PREVIEW")) {
            processingMode = ProcessingMode.PREVIEW;
        } else if (processingModeString.equals("MULTIPLE_FILES")) {
            processingMode = ProcessingMode.MULTIPLE_FILES;
        }
    }

}
