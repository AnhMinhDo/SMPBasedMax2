package schneiderlab.tools.smpbasedmax2.services;

import ij.ImagePlus;
import schneiderlab.tools.smpbasedmax2.OutputTypeName;
import schneiderlab.tools.smpbasedmax2.ZStackDirection;
import schneiderlab.tools.smpbasedmax2.corecomputation.MaxIntensityProjection;
import schneiderlab.tools.smpbasedmax2.corecomputation.SMP_MIP_Projection;
import schneiderlab.tools.smpbasedmax2.corecomputation.SMProjection;
import schneiderlab.tools.smpbasedmax2.helpersandutils.SmpBasedMaxUtil;

import java.nio.file.Path;
import java.util.HashSet;

public class HandleSingleFileNoChannelSaveOutput {
    /* TODO: this class takes in 1 imagePlus object,
         1 path object, 1 set for output type,
        parameters for the process, process the file
        and save the desired output according to the provided output set,
         including method to get the main output if required,
         for saving output: check all the available value of OutputType,
         if any exist, perform the saving process
          */
    private Path inputPath;
    private ImagePlus inputImage;
    private HashSet<OutputTypeName> outputTypeSet;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private float[] envMaxzValues;

    private ImagePlus MIPImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;

    public HandleSingleFileNoChannelSaveOutput(Path inputPath,
                                               ImagePlus inputImage,
                                               HashSet<OutputTypeName> outputTypeSet,
                                               int stiffness, int filterSize,
                                               ZStackDirection zStackDirection,
                                               int offset,
                                               int depth) {
        this.inputPath = inputPath;
        this.inputImage = inputImage;
        this.outputTypeSet = outputTypeSet;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
    }

    public void process(){
        performProcessing();
        saveOutputToFile();
    }

    public ImagePlus getSMPOutput(){
        if(this.projectedSMPImage == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return depth==0 ? this.projectedSMPImage : projectedSMPMIPImage;
        }
    }

    public ImagePlus getSMPzMapOutput(){
        if(this.smpZmap == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return depth==0 ? this.smpZmap : this.smpMipZmap;
        }
    }

    private void performProcessing(){
        // create imagePlus object from filePath
        inputImage = SmpBasedMaxUtil.preProcessInputImage(this.inputImage);
        // ZProjecting MIP
        MaxIntensityProjection projector = new MaxIntensityProjection(inputImage);
        this.MIPImage = projector.doProjection();
        this.zMap = projector.getZmap();
        // ZProjecting SMP
        SMProjection smProjector = new SMProjection(inputImage, zMap, stiffness, filterSize, zStackDirection, offset);
        this.projectedSMPImage = smProjector.doSMProjection();
        this.envMaxzValues = smProjector.getEnvMax();
//        hasProcessed = true;
        this.smpZmap = smProjector.getSMPZmap();
        // SMP-MIP if depth !=0
        SMP_MIP_Projection smpMipProjector = new SMP_MIP_Projection(inputImage, smpZmap, depth, zStackDirection);
        this.projectedSMPMIPImage = smpMipProjector.doProjection();
        this.smpMipZmap = smpMipProjector.getZmap();
    }

    private void saveOutputToFile(){
        for(OutputTypeName outputTypeName: outputTypeSet){
            if(outputTypeName == null) continue;
            switch (outputTypeName){
                case MIP: saveMIP();
                case MIP_ZMAP: saveMIPZMap();
                case SMP: saveSMP();
                case SMP_ZMAP: saveSMPZMap();
            }
        }
    }

    private void saveMIP(){
        SmpBasedMaxUtil.savePostProcessImagePlus(this.MIPImage,
                OutputTypeName.MIP,
                inputPath,
                this.stiffness,
                this.filterSize,
                this.offset,
                this.depth);
    }

    private void saveMIPZMap(){
        SmpBasedMaxUtil.savePostProcessImagePlus(this.zMap,
                OutputTypeName.MIP_ZMAP,
                inputPath,
                this.stiffness,
                this.filterSize,
                this.offset,
                this.depth);
    }

    private void saveSMP(){
        if(depth == 0){
            SmpBasedMaxUtil.savePostProcessImagePlus(this.projectedSMPImage,
                    OutputTypeName.SMP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth);
        } else {
            SmpBasedMaxUtil.savePostProcessImagePlus(this.projectedSMPMIPImage,
                    OutputTypeName.SMPbasedMIP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth);
        }
    }

    private void saveSMPZMap(){
        if(depth == 0){
            SmpBasedMaxUtil.savePostProcessImagePlus(this.smpZmap,
                    OutputTypeName.SMP_ZMAP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth);
        } else {
            SmpBasedMaxUtil.savePostProcessImagePlus(this.smpMipZmap,
                    OutputTypeName.SMPbasedMIP_ZMAP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth);
        }
    }
}
