package edu.moe;

import edu.moe.imgAnalysis.Analyzer;
import edu.moe.imgAnalysis.TesseractAnalyzer;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Driver
{
    public static void main( String[] args )
    {
        Analyzer imgTesseractAnalyzer = new TesseractAnalyzer();
        try {
            String result = imgTesseractAnalyzer.executeOCR();
            System.out.println(result);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
