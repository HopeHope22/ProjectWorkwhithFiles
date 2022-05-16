import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestWithFiles {

        @Test
        void pdfTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/ZIPTEST.zip");
            ZipEntry zipEntry = zipFile.getEntry("impotantFile.pdf");
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            PDF pdf = new PDF(inputStream);
            assertThat(pdf.text).contains("Тестовый PDF-документ");
        }


        @Test
        void xlsTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/ZIPTEST.zip");
            ZipEntry zipEntry = zipFile.getEntry("sample-simple-1.xls");
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            XLS xls = new XLS(inputStream);
            assertThat(xls.excel
                    .getSheetAt(0)
                    .getRow(1)
                    .getCell(1)
                    .getStringCellValue()).contains("test1");
        }

        @Test
        void csvTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/ZIPTEST.zip");
            ZipEntry zipEntry = zipFile.getEntry("SomeFile.csv");
            try (InputStream inputStream = zipFile.getInputStream(zipEntry);
                 CSVReader csv = new CSVReader(new InputStreamReader(inputStream))) {
                var content = csv.readAll();
                assertThat(content.get(0)).contains("SomeText1");
            }
        }
}