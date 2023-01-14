package util;

 

import java.io.FileInputStream;

import java.io.IOException;

 

import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.text.PDFTextStripper;

 

public class test {

 

              public static void main(String[] args) throws IOException {

                            

                             FileInputStream obj = new FileInputStream("D:\\Users\\rbiswal\\Docs\\Myintro.pdf");

                            

                             PDDocument obj1 = PDDocument.load(obj);

                             PDFTextStripper objstripper = new PDFTextStripper();

                             String pdcontent = objstripper.getText(obj1);

                            

                             System.out.println(pdcontent);

                             System.out.println("end");

             

              }            

}