package schneiderlab.tools.smpbasedmax2;

import ij.plugin.PlugIn;
import schneiderlab.tools.smpbasedmax2.controllers.MainController;
import schneiderlab.tools.smpbasedmax2.models.MainModel;
import schneiderlab.tools.smpbasedmax2.uicomponents.MainDialog;

import javax.swing.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
        JFrame frame = new JFrame("SMPTool Version 2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainModel mainModel = new MainModel(ProcessingMode.SINGLE_FILE,
                ThemeMode.LIGHT,
                ZStackDirection.IN,
                60,
                30,
                0,
                0,
                Paths.get(""),
                Paths.get(""),
                new HashSet<>(Collections.singletonList(OutputTypeName.SMP)));
        MainDialog form = new MainDialog(frame);
        MainController mainController = new MainController(mainModel,form);
        frame.setContentPane(form.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }


}
