package schneiderlab.tools.smpbasedmax2.uicomponents.workers;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.plugin.ChannelSplitter;
import schneiderlab.tools.smpbasedmax2.OutputTypeName;
import schneiderlab.tools.smpbasedmax2.ZStackDirection;
import schneiderlab.tools.smpbasedmax2.helpersandutils.SmpBasedMaxUtil;
import schneiderlab.tools.smpbasedmax2.services.HandleSingleFile;
import schneiderlab.tools.smpbasedmax2.services.HandleSingleFileWithChannels;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ProcessSingleFileWorker extends SwingWorker<Void,Void> {
    private JTextField statusBar;
    private JProgressBar progressBar;

    private String filePath;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private float[] envMaxzValues;
    private final boolean hasManyChannels;
    private final int referenceChannelIdx;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;
    private ImagePlus result;

    public ProcessSingleFileWorker(int stiffness, int filterSize, ZStackDirection zStackDirection, int offset, int depth, String filePath, JTextField statusBar, boolean hasManyChannels, int referenceChannelIdx, JProgressBar progressBar) {
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
        this.filePath = filePath;
        this.statusBar = statusBar;
        this.hasManyChannels = hasManyChannels;
        this.referenceChannelIdx = referenceChannelIdx;
        this.progressBar = progressBar;
    }

    @Override
    protected Void doInBackground() throws Exception {
        if(!hasManyChannels) {
            // Updating progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            ImagePlus inputImageRaw = new ImagePlus(filePath);
            ImagePlus[] inputImageChannels = ChannelSplitter.split(inputImageRaw);
            ImagePlus inputImage = inputImageChannels[referenceChannelIdx];
            HandleSingleFile handleSingleFile = new HandleSingleFile(inputImage,
                    zStackDirection,
                    stiffness,
                    filterSize,
                    offset,
                    depth);
            result = handleSingleFile.process();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            // save output
            saveOutputToFile(result, filePath);
            return null;
        } else {
            // Update progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            ImagePlus inputImage = new ImagePlus(filePath);
            HandleSingleFileWithChannels handleSingleFileWithChannels = new HandleSingleFileWithChannels(inputImage,zStackDirection,stiffness,filterSize,offset,depth,referenceChannelIdx);
            result = handleSingleFileWithChannels.process();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            saveOutputToFile(result,filePath);
            return null;
        }
    }

    @Override
    protected void done(){
        statusBar.setText("Complete Processing");
        statusBar.setToolTipText("Complete Processing");
    }

        private void saveOutputToFile(ImagePlus output, String filePath){
            // prepare the directory for output
//            String resultDir = SmpBasedMaxUtil.createResultDir(filePath,
//                    zStackDirection,
//                    stiffness,
//                    filterSize,
//                    offset,
//                    depth);
//            String fileName = SmpBasedMaxUtil.extractFilename(filePath);

            // Save SMP projected image and zMap
            if(depth == 0 ) {
                SmpBasedMaxUtil.savePostProcessImagePlus(output, OutputTypeName.SMP, Paths.get(filePath), stiffness, filterSize, offset, depth);
//            SmpBasedMaxUtil.savePostProcessImagePlus(this.smpZmap, OutputTypeName.SMP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
            }
            // Save SMP depth-adjusted image and zMap
            if (depth != 0) {
                SmpBasedMaxUtil.savePostProcessImagePlus(output, OutputTypeName.SMPbasedMIP,Paths.get(filePath), stiffness, filterSize, offset, depth);
//                SmpBasedMaxUtil.savePostProcessImagePlus(this.smpMipZmap, OutputTypeName.SMPbasedMIP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
            }
        }


}
