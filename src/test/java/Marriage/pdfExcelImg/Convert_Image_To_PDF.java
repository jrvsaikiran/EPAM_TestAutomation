package Marriage.pdfExcelImg;

import java.io.*;

// importing itext library packages
import com.itextpdf.io.image.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

//public  String pdfPath ;
public class Convert_Image_To_PDF {
    public static String pdfPath;
    private static  File file;
    public  void createPdfs(String destination, String imageName) throws IOException
    {
        String currDir = "C:/Users/rajavenkatasaikiran_/IdeaProjects/TestAutomation/target/pdfs";

//        String currDir = "C:/Users/rajavenkatasaikiran_/IdeaProjects/TestAutomation/src/test/java/Marriage/Bharath/pdfs";
        // Getting path of current working directory
        // to create the pdf file in the same directory of
        // the running java program

        try {
            if (file==null) {
                file = new File(currDir);
                file.mkdir();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pdfPath = currDir +"/"+imageName+".pdf";

        // Creating path for the new pdf file
        PdfWriter writer = new PdfWriter(pdfPath);

        // Creating PdfWriter object to write pdf file to
        // the path
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Creating a PdfDocument object
        Document doc = new Document(pdfDoc);

        // Creating a Document object

        ImageData imageData = ImageDataFactory.create( destination);

        // Creating imagedata from destination on disk(from given
        // path) using ImageData object
        Image img = new Image(imageData);

        // Creating Image object from the imagedata
        doc.add(img);

        // Adding Image to the empty document
        doc.close();

        // Closing the document
        System.out.println(
                "Image added successfully and PDF file created!");
    }
}
