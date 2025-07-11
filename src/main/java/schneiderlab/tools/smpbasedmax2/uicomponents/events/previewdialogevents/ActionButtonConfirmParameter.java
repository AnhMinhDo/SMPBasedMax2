package schneiderlab.tools.smpbasedmax2.uicomponents.events.previewdialogevents;

import schneiderlab.tools.smpbasedmax2.uicomponents.MainDialog;
import schneiderlab.tools.smpbasedmax2.uicomponents.PreviewDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonConfirmParameter implements ActionListener {
    private MainDialog mainDialogView;
    private PreviewDialog previewDialogView;

    public ActionButtonConfirmParameter(MainDialog mainDialogView,
                                        PreviewDialog previewDialogView) {
        this.mainDialogView = mainDialogView;
        this.previewDialogView = previewDialogView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String direction = (String)previewDialogView.getComboBox1ZStackDirection().getSelectedItem();
        if (direction.equals("IN")){
            mainDialogView.getComboBox1ZStackDirection().setSelectedIndex(0);
        } else if (direction.equals("OUT")) {
            mainDialogView.getComboBox1ZStackDirection().setSelectedIndex(1);
        }
        int stiffness = (int)previewDialogView.getSpinner1Stiffeness().getValue();
        int filtersize = (int)previewDialogView.getSpinner2FilterSize().getValue();
        int offset = (int)previewDialogView.getSpinner3Offset().getValue();
        int depth = (int)previewDialogView.getSpinner4Depth().getValue();
        mainDialogView.getSpinner1Stiffeness().setValue(stiffness);
        mainDialogView.getSpinner2FilterSize().setValue(filtersize);
        mainDialogView.getSpinner3Offset().setValue(offset);
        mainDialogView.getSpinner4Depth().setValue(depth);
    }
}
