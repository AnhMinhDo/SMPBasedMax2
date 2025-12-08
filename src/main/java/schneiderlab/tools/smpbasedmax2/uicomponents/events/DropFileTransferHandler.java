package schneiderlab.tools.smpbasedmax2.uicomponents.events;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DropFileTransferHandler extends TransferHandler {

    public DropFileTransferHandler(){}

    @Override
    public boolean canImport(TransferSupport support){
        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) return false;
        Transferable t = support.getTransferable();
        try {
            List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
            for (File file : files) {
                System.err.println("Dropped file path: " + file.getAbsolutePath());
            }
            return true;
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
