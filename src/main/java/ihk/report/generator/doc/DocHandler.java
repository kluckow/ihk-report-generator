package ihk.report.generator.doc;

import ihk.report.generator.excel.unit.Week;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DocHandler {

    public static final String PLACEHOLDER_LAST_NAME = "$last_name";
    public static final String PLACEHOLDER_FIRST_NAME = "$first_name";
    public static final String PLACEHOLDER_BIRTHDAY = "$birthday";
    public static final String PLACEHOLDER_BIRTH_LOCATION = "$birth_location";
    public static final String PLACEHOLDER_POST_CODE = "$postcode";
    public static final String PLACEHOLDER_STREET_NR = "$street_nr";
    public static final String PLACEHOLDER_PROFESSION = "$profession";
    public static final String PLACEHOLDER_START_DATE = "00.00.0001";
    public static final String PLACEHOLDER_END_DATE = "00.00.0002";
    public static final String PLACEHOLDER_COMPANY = "$company";
	
	private static final String OUTPUT_PATH = "C:\\Users\\Markus\\Desktop\\";
	
	public void createReportFile(Week week) {
		
		   //Blank Document
//		   HWPFDocument document= new HWPFDocument(); 
		   //Write the Document in file system
		FileOutputStream out;
		try {
			out = new FileOutputStream(
					new File(System.getProperty("user.home" + File.separator + "Downloads")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// document.write(out);
		// out.close();
		System.out.println("createdocument.docx written successully");
	}
	
    public void setupCoverpage(final Map<String, String> coverFormMap) {
        
      File fileCoverTemplate = new File(getClass().getClassLoader()
      .getResource("templates/ausbildungsnachweis_deckblatt.doc").getFile());

      String filePath = OUTPUT_PATH
      + coverFormMap.get(PLACEHOLDER_LAST_NAME).toLowerCase()
      + "_" + coverFormMap.get(PLACEHOLDER_FIRST_NAME).toLowerCase()
      + "_berichtsheft_deckblatt.doc";
      
      POIFSFileSystem fs = null;
      try {
        fs = new POIFSFileSystem(new FileInputStream(fileCoverTemplate.getPath()));            
          HWPFDocument doc = new HWPFDocument(fs);

          // replace placeholders
          Iterator<Entry<String, String>> it = coverFormMap.entrySet().iterator();
          while (it.hasNext()) {
              Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
              doc = replaceText(doc, pair.getKey(), pair.getValue());
          }
          // save to new file
          saveWord(filePath, doc);
      } catch(FileNotFoundException e){
          e.printStackTrace();
      } catch(IOException e){
          e.printStackTrace();
      }
      
	}

    /**
     * @param filePath
     * @param doc
     * @throws IOException 
     */
    private void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException {

        FileOutputStream out = null;
        try {
            System.out.println(filePath);
            File file = new File(filePath);
            if (file.createNewFile()) {
                out = new FileOutputStream(file);
                doc.write(out);
            } else {
                System.out.println("Could not create new coverpage file!");
            }
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @param doc
     * @param string
     * @param string2
     * @return
     */
    private HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r1 = doc.getRange(); 

        for (int i = 0; i < r1.numSections(); ++i ) { 
            Section s = r1.getSection(i); 
            for (int x = 0; x < s.numParagraphs(); x++) { 
                Paragraph p = s.getParagraph(x); 
                for (int z = 0; z < p.numCharacterRuns(); z++) { 
                    CharacterRun run = p.getCharacterRun(z); 
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    } 
                }
            }
        } 
        return doc;
    }
	
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    public void setupCoverpage(final Map<String, String> coverFormMap) {
//		
//		FileInputStream fis;
//		File file = new File(
//				getClass().getClassLoader()
//				.getResource("templates/ausbildungsnachweis_deckblatt.doc").getFile());
//		try {
//			
//			fis = new FileInputStream(file);
//			HWPFDocument doc = new HWPFDocument(fis);
//			WordExtractor we = new WordExtractor(doc);
//			String[] paragraphsOld = we.getParagraphText();
//			String[] paragraphsNew = new String[paragraphsOld.length]; 
//			System.out.println("Total no of paragraph " + paragraphsOld.length);
//			
//			int counterReplacements = 0;
//			for (int i = 0; i < paragraphsOld.length; i++) {
//			    
//			    System.out.println(paragraphsOld[i]);
//			    
//			    Iterator it = coverFormMap.keySet().iterator();
//			    while (it != null && it.hasNext()) {
//			        
//                    Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
//                    if (paragraphsOld[i].contains(pair.getKey())) {
//                        paragraphsOld[i] = paragraphsOld[i].replace(pair.getKey(), pair.getValue());
//                        counterReplacements++;
//                        continue;
//                    }
//                    
//                    if (counterReplacements == AMOUNT_MAXIMUM_PLACEHOLDER) {
//                        break;
//                    }
//			    }
//			        
//			}
//			
//			fis.close();
//			
////			FileOutputStream fos = new FileOutputStream();
//		    
//		    File newFile = new File("C:\\Users\\Kluckow\\Desktop\\"
//		                    + coverFormMap.get(PLACEHOLDER_LAST_NAME)
//		                    + "_" + coverFormMap.get(PLACEHOLDER_FIRST_NAME)
//		                    + "_berichtsheft_deckblatt.doc");
//			POIFSFileSystem poiFS = new POIFSFileSystem(newFile);
//			HWPFDocument newDoc = new HWPFDocument(poiFS);
//			
//			// TODO: create .doc file with paragraphsOld[] 
//			
//		} catch (Exception e) {
//		    // TODO: explain to user
//			e.printStackTrace();
//		}
//	}
	
}
