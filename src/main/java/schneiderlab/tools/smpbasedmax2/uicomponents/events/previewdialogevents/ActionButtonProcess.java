package schneiderlab.tools.smpbasedmax2.uicomponents.events.previewdialogevents;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import schneiderlab.tools.smpbasedmax2.ProcessingMode;
import schneiderlab.tools.smpbasedmax2.ZStackDirection;
import schneiderlab.tools.smpbasedmax2.services.HandleSingleFile;
import schneiderlab.tools.smpbasedmax2.services.HandleSingleFileWithChannels;
import schneiderlab.tools.smpbasedmax2.uicomponents.PreviewDialog;
import schneiderlab.tools.smpbasedmax2.uicomponents.workers.previewdialogworkers.PreviewDialogWithChannelsWorker;
import schneiderlab.tools.smpbasedmax2.uicomponents.workers.previewdialogworkers.PreviewDialogWithoutChannelWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ActionButtonProcess implements ActionListener {
    private JComboBox zStackDirectionCombobox;
    private JSpinner spinnerStiffness;
    private JSpinner spinnerFilterSize;
    private JSpinner spinnerOffSet;
    private JSpinner spinnerDepth;
    private PreviewDialog previewDialogView;
    private boolean hasChannels;
    private int channelIdx;
    private int stiffness;
    private int filterSize;
    private int offSet;
    private int depth;
    private ZStackDirection zStackDirection;
    private ImagePlus outputImage;
    private Path inputImagePath;
    private ImagePlus inputImage;

    public ActionButtonProcess(PreviewDialog previewDialogView,
                               ImagePlus outputImage,
                               boolean hasChannels,
                               int channelIdx,
                               Path inputImagePath,
                               ImagePlus inputImage) {
        this.zStackDirectionCombobox = previewDialogView.getComboBox1ZStackDirection();
        this.previewDialogView = previewDialogView;
        this.spinnerStiffness= previewDialogView.getSpinner1Stiffeness();
        this.spinnerFilterSize= previewDialogView.getSpinner2FilterSize();
        this.spinnerOffSet= previewDialogView.getSpinner3Offset();
        this.spinnerDepth= previewDialogView.getSpinner4Depth();
        this.hasChannels= hasChannels;
        this.channelIdx= channelIdx;
        this.inputImage= inputImage;
        this.outputImage= outputImage;
        this.inputImagePath= inputImagePath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        setzStackDirection();
        zStackDirection = (ZStackDirection) zStackDirectionCombobox.getSelectedItem();
        stiffness = (int)spinnerStiffness.getValue();
        filterSize = (int)spinnerFilterSize.getValue();
        offSet = (int)spinnerOffSet.getValue();
        depth = (int)spinnerDepth.getValue();
//        ImagePlus inputImagePlus = new ImagePlus(inputImage.toAbsolutePath().toString());
        if(hasChannels){
            previewDialogView.getTextField3Status().setText("Processing...");
            previewDialogView.getTextField3Status().setToolTipText("Processing...");
            previewDialogView.getProgressBar1().setValue(50);
            PreviewDialogWithChannelsWorker previewDialogWithChannelsWorker = new PreviewDialogWithChannelsWorker(
                    previewDialogView.getTextField3Status(),
                    previewDialogView.getProgressBar1(),
                    previewDialogView,
                    inputImagePath,
                    stiffness,
                    filterSize,
                    zStackDirection,
                    offSet,
                    depth,
                    outputImage,
                    channelIdx
            );
            previewDialogWithChannelsWorker.execute();
//            HandleSingleFileWithChannels handleSingleFileWithChannels = new HandleSingleFileWithChannels(inputImagePlus,zStackDirection,stiffness,filterSize,offSet,depth,channelIdx);
//            ImagePlus output = handleSingleFileWithChannels.process();
//            ImageProcessor outputProcessor = output.getProcessor();
//            outputImage.setProcessor(outputProcessor);
//            outputImage.updateAndDraw();
        } else {
            previewDialogView.getTextField3Status().setText("Processing...");
            previewDialogView.getTextField3Status().setToolTipText("Processing...");
            previewDialogView.getProgressBar1().setValue(50);
            PreviewDialogWithoutChannelWorker previewDialogWithoutChannelWorker = new PreviewDialogWithoutChannelWorker(
                    previewDialogView.getTextField3Status(),
                    previewDialogView.getProgressBar1(),
                    previewDialogView,
                    inputImagePath,
                    stiffness,
                    filterSize,
                    zStackDirection,
                    offSet,
                    depth,
                    outputImage,
                    inputImage
            );
            previewDialogWithoutChannelWorker.execute();
//            HandleSingleFile handleSingleFile = new HandleSingleFile(inputImagePlus,zStackDirection,stiffness,filterSize,offSet,depth);
//            ImagePlus result = handleSingleFile.process();
//            ImageProcessor resultProcessor = result.getProcessor();
//            outputImage.setProcessor(resultProcessor);
//            outputImage.updateAndDraw();
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

}
