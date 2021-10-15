package edu.moe.imgAnalysis;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Analyzer {
    public Analyzer(){

    }
    public void executeOCR() throws TesseractException, IOException {
//        File image = new File("src/main/resources/testImgs/weequahic_front_9.jpg");
        //TODO figure out how to give tesseract image for input
        //could be due to M1 - https://github.com/RaiMan/SikuliX1/issues/459
        String absImgPath = "/Users/moevaughan/Development/projects/java/scoreChew/src/main/resources/testImgs/weequahic_front_9.jpg";
        String imgPath = "/testImgs/weequahic_front_9.jpg";
        String classLoadedPath = this.getClass().getResource(imgPath).getPath();
        File image = new File(classLoadedPath);
        BufferedImage picture;
        try
        {
            System.out.println("Class loaded path = " + classLoadedPath);
            picture = ImageIO.read(this.getClass().getResource(imgPath));
            Tesseract tesseract = new Tesseract();
            String path = ClassLoader.getSystemResource("tessdata").getPath();
//        tesseract.setDatapath("src/main/resources/tessdata");
            tesseract.setDatapath(path);
            tesseract.setLanguage("eng");
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);
            tesseract.doOCR(picture);
        }
        catch (IOException e)
        {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + classLoadedPath);
            e.printStackTrace();
        }


    }
}
