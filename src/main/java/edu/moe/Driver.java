package edu.moe;

import edu.moe.imgAnalysis.Analyzer;
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
        Analyzer imgAnalyzer = new Analyzer();
        try {
            imgAnalyzer.executeOCR();
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
    }
}
