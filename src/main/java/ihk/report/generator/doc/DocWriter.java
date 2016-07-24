package ihk.report.generator.doc;

import ihk.report.generator.excel.unit.Week;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocWriter {

	private static final String PLACEHOLDER_CITY_OF_BIRTH = "$geburtsort";
	
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
	public void readCoverpageTest() {
		
		FileInputStream fis;
		File file = new File(
				getClass().getClassLoader()
				.getResource("templates/ausbildungsnachweis_deckblatt.doc").getFile());
		try {
			
			fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			WordExtractor we = new WordExtractor(doc);
			String[] paragraphs = we.getParagraphText();
			System.out.println("Total no of paragraph "+paragraphs.length);
			for (String para : paragraphs) {
				System.out.println(para.toString());
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
