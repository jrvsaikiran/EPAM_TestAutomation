package Marriage.Bharath;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class img {
    public static  void main() throws IOException {

        //create a new workbook
        XSSFWorkbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
//add picture data to this workbook.
        FileInputStream is = new FileInputStream("src/test/java/Marriage/Bharath/Screenshots/Screenshot 2024-07-25 103002.png");

        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

        is.close();
        XSSFCreationHelper helper = wb.getCreationHelper();
//create sheet
        XSSFSheet sheet = wb.createSheet("pic");
// Create the drawing patriarch.  This is the top level container for all shapes.
        Drawing drawing = sheet.createDrawingPatriarch();
//add a picture shape
        XSSFClientAnchor anchor = helper.createClientAnchor();
//set top-left corner of the picture,
//subsequent call of Picture#resize() will operate relative to it
        anchor.setCol1(3);
        anchor.setRow1(2);
        Picture pict = drawing.createPicture(anchor, pictureIdx);
//auto-size picture relative to its top-left corner
        pict.resize();
//save workbook
        String file = "src/test/java/Marriage/Bharath/bharathProfile.xlsx";
//        if(wb instanceof XSSFWorkbook) file += "x";
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            wb.write(fileOut);
            wb.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
