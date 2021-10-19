package edu.moe.imgAnalysis;

import edu.moe.Driver;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Analyzer {
    public Analyzer(){

    }
    public void executeOCR() throws TesseractException, IOException {
        // M1 issues can be stemming from - https://github.com/RaiMan/SikuliX1/issues/459
        //To fix for M1 do the following : In short, the problem is because tess4j-2.0.0.jar doesn't include MacOS
        // library. So I just modified the maven cached jar on mine by doing these steps:
        // https://stackoverflow.com/a/30724844/272071
        String imgPath = "/testImgs/weequahic_front_9.jpg";
        //String imgPath = "/testImgs/index.png";
        String classLoadedPath = this.getClass().getResource(imgPath).getPath();
        File image = new File(classLoadedPath);
        BufferedImage picture;
        try
        {
            System.out.println("Class loaded path = " + classLoadedPath);
            //Extract tesseract dependencies.
            extractTessDeps();
            Tesseract tesseract = new Tesseract();
            tesseract.setLanguage("eng");
            tesseract.setOcrEngineMode(1);
            tesseract.setTessVariable("user_defined_dpi", "300");
            tesseract.setDatapath(ClassLoader.getSystemResource("testData").getPath());

            BufferedImage image2 = ImageIO.read(Driver.class.getResourceAsStream(imgPath));
            String result = tesseract.doOCR(image2);
            System.out.println(result);

        }
        catch (IOException e)
        {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + classLoadedPath);
            e.printStackTrace();
        }
    }
    public void extractTessDeps(){
        //This only works on windows/linux for m1 you must brew install tesseract directly and pull from there.
        File tmpFolder = LoadLibs.extractTessResources("win32-x86-64");
        System.setProperty("java.library.path", tmpFolder.getPath());
    }
}
