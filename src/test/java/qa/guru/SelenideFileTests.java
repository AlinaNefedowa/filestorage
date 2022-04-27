package qa.guru;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.assertj.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class SelenideFileTests {

    ClassLoader classLoader = SelenideFileTests.class.getClassLoader();

    // public static String zipFilePath = "src/test/resources/Archive.zip";
    // public static String zipFileName = "Archive.zip";

    @DisplayName("Check the pdf file in zip file")
    @Test
    void zipPdfFileTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/zip/Archive.zip"));
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream("zip/Archive.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("pdfFile.pdf");
            try (InputStream InputStream = zf.getInputStream(entry)) {
                PDF pdf = new PDF(InputStream);
                Assertions.assertThat(pdf.numberOfPages).isEqualTo(2);
            }
        }
    }
}