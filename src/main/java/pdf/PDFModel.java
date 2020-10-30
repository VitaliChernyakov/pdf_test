package pdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class PDFModel  {
    private static final String CREATED_PDF ="D://test.pdf" ;

    public void writeDocument() throws FileNotFoundException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(CREATED_PDF));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("рлоирлилоилолид"));
        // step 5
        document.newPage();
        document.add(new Paragraph("ghfhfghfghfghghfgfj"));
        document.close();
    }

}
