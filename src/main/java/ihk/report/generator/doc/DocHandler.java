package ihk.report.generator.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import org.apache.log4j.Logger;

import ihk.report.generator.excel.unit.Week;

public class DocHandler {

    /**
     * The logger for this class.
     */
    private static final Logger LOG = Logger.getLogger(DocHandler.class);
    
    public static final String PLACEHOLDER_LAST_NAME = "$last_name";
    public static final String PLACEHOLDER_FIRST_NAME = "$first_name";
    public static final String PLACEHOLDER_BIRTHDAY = "$birthday";
    public static final String PLACEHOLDER_BIRTH_LOCATION = "$birth_location";
    public static final String PLACEHOLDER_POST_CODE = "$postcode";
    public static final String PLACEHOLDER_STREET_NR = "$street_nr";
    public static final String PLACEHOLDER_PROFESSION = "$profession";
    public static final String PLACEHOLDER_START_DATE = "11.11.1970";
    public static final String PLACEHOLDER_END_DATE = "22.22.1970";
    public static final String PLACEHOLDER_COMPANY = "$company";

    // private static final String OUTPUT_PATH = "C:\\Users\\Markus\\Desktop\\";
    private static final String OUTPUT_PATH = "C:\\Users\\kluckow\\Desktop\\";

    public void createReportFile(Week week) {

        // Write the Document in file system
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(
                new File(System.getProperty("user.home") + File.separator + "Downloads"));
        } catch (FileNotFoundException e) {
            LOG.error("File not found!");
        } finally {
//             document.write(out);
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings({ "rawtypes"})
    public void setupCoverpage(final Map<String, String> coverFormMap) {

        LOG.debug("setupCoverpage");
        
        File fileCoverTemplate = new File(getClass().getClassLoader()
            .getResource("templates/ausbildungsnachweis_deckblatt.docx").getFile());
        File newFile = new File(OUTPUT_PATH + "\\test.docx");
        Path newFilePath;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        XWPFDocument doc = null;
        try {
            newFilePath = Files.copy(fileCoverTemplate.toPath(), newFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
            File targetFile = new File(newFilePath.toString());
            fis = new FileInputStream(targetFile);
            
            doc = new XWPFDocument(fis);
            
            // this section is currently not used, atleast not for coverpage template
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        Iterator<Entry<String, String>> it1 = coverFormMap.entrySet().iterator();
                        while (it1.hasNext()) {
                            Map.Entry pair = it1.next();
                            if (text != null && text.contains((String) pair.getKey())) {
                                text = text.replace((String) pair.getKey(), (String) pair.getValue());
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }

            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                Iterator<Entry<String, String>> it2 = coverFormMap.entrySet().iterator();
                                while (it2.hasNext()) {
                                    Map.Entry pair = it2.next();
                                    if (text != null && text.contains(pair.getKey().toString())) {
                                        text = text.replace((String) pair.getKey(), (String) pair.getValue());
                                        r.setText(text, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            fis.close();
            fos = new FileOutputStream(targetFile);
            doc.write(fos);
        } catch (IOException e) {
            LOG.error("Fehler beim lesen/erstellen/beschreiben der Word-Datei!");
        } finally {
            try {
                doc.close();
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                LOG.error("Fehler beim Zugriff auf die Word-Datei!");
            }
        }

    }

}
