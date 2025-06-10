package schneiderlab.tools.smpbasedmax2;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import ij.plugin.PlugIn;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SMP_Based_Max2 implements PlugIn {

    @Override
    public void run(String s) {
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
        frame.setLayout(null);

        frame.pack();
        frame.setVisible(true);
    }


}
