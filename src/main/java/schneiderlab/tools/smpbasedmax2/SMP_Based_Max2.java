package schneiderlab.tools.smpbasedmax2;

import ij.plugin.PlugIn;
import schneiderlab.tools.smpbasedmax2.uicomponents.MainDialog;

import javax.swing.*;

public class SMP_Based_Max2 implements PlugIn {

    @Override
    public void run(String arg) {
        SwingUtilities.invokeLater(this::launchUI);
    }

    public void launchUI (){
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("mac")){
                com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
            } else {
                com.formdev.flatlaf.FlatLightLaf.setup();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame("Main Windows of Application");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MainDialog form = new MainDialog(frame);
        frame.setContentPane(form.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }


}
