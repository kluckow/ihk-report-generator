package ihk.report.generator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ihk.report.generator.doc.DocHandler;
import ihk.report.generator.excel.unit.Day;
import ihk.report.generator.excel.unit.Record;
import ihk.report.generator.excel.unit.Week;
import ihk.report.generator.excel.util.Configuration;
import ihk.report.generator.util.FileUtils;

public class ExcelReader {

    /**
     * The logger for this class.
     */
    private static final Logger LOG = Logger.getLogger(ExcelReader.class);
    
	private static final String XLS_FILEFORMAT = ".xls";
	private static final String XLSX_FILEFORMAT = ".xlsx";
	private static final List<String> RELEVANT_FILEFORMATS = Arrays.asList(XLS_FILEFORMAT, XLSX_FILEFORMAT);
	private static final String EXCEL_WORKBOOK_ZEITNACHWEISE = "Zeitnachweise";
	private List<File> xlsFiles;
	
	public Record createRecord(HSSFRow row) {
		
		return null;
	}
	public Day createDay(List<Record> records) {
		
		return null;
	}
	public Week createWeek(List<Day> days) {
		
		return null;
	}
	public void start(String dirName) {

	    File excelDir = new File(dirName);
	    
		/**
		 * Pseudo for dir2xls
		 * 
		 * 1. get all .xls as File in dir
		 * 2. use pseudo below
		 */
		loadExcelFiles(excelDir);
		for (File file : this.xlsFiles) {
            LOG.debug(file.getAbsolutePath());
        }
		/**
		 * Pseudo for each .xls
		 * 
		 * 1. iterate over .xls
		 * 2. get workbook
		 * 3. get sheet
		 * 4. iterate over rows of sheet
		 * 5. create record for each row
		 * 6. create day by all records
		 * 7. create week by all days
		 * 8. format week
		 * 9. get template doc file
		 * 10. put week into .doc file 
		 */
		processExcelFiles();
		
		
		
		FileInputStream fis = null;
		try {
		    File testXls = new File(getClass().getClassLoader()
	            .getResource("testdata/test.xls").getFile());
			fis = new FileInputStream(testXls);
			
//		create workbook
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet worksheet = workbook.getSheet(EXCEL_WORKBOOK_ZEITNACHWEISE);
		
//		get any row in order to get the number of columns
		int rowCount = worksheet.getPhysicalNumberOfRows();
		
		int cellCount = (int) worksheet.getRow(1).getLastCellNum();
		
		List<String> relevantHeaders = Configuration.relevantHeaders;
		
		System.out.println("Das Worksheet hat " + rowCount + " Zeilen");
		System.out.println("Das Worksheet hat " + cellCount + " Spalten");
		
//		HSSFRow currRow;
		
//		get the relevant column by column headers
		List<Integer> relevantRowIndices = new ArrayList<>();
		int headerRowIndex = 0;
		boolean isRelevantHeader;
		HSSFRow headerRow = worksheet.getRow(headerRowIndex);
		
		// get relevant column indices
		for (int col = 0; col < cellCount; col++) {
			
			isRelevantHeader = relevantHeaders.contains(headerRow.getCell(col).getStringCellValue());
			// if header is relevant...
			if (isRelevantHeader) {
				
				// save indices
				relevantRowIndices.add(col);
			}
		}
		
		// create Entry objects
		// iterate over rows
		// starting at 1, because headers are in row = 0
		for (int row = 1; row < rowCount; row++) {
			
			// create Entry Object
//			entry[row-1] = new Entry(); 
			
			for (int index: relevantRowIndices) {
				
				worksheet.getRow(row).getCell(index).getCellType();
				switch (index) {
				case 1:
					// define vorgang
					String vorgang = worksheet.getRow(row).getCell(index).getStringCellValue();
					// add vorgang to entry
//					entry[row-1].setVorgang(vorgang);
					System.out.println(vorgang);
					break;
				case 2:
					// define stunden
					double stunden = worksheet.getRow(row).getCell(index).getNumericCellValue();
					// add stunden to entry
//					entry[row-1].setStunden(stunden);
					System.out.println(stunden);
					break;
				case 3:
                        worksheet.getRow(row).getCell(index).getDateCellValue();
//					date = date.substring(0, 10);
//					entry[row-1].setDatum(date);
					break;
				case 5:
					// define fullName
					String fullName = worksheet.getRow(row).getCell(index).getStringCellValue();
					//  defin firstName and lastName by splitting by space character
                    String[] tmp = fullName.split(" ");
                        

//					entry[row-1].setFullName(fullName);
//					entry[row-1].setFirstName(firstName);
//					entry[row-1].setLastName(lastName);
					
					System.out.println(fullName);
//					System.out.println(firstName);
//					System.out.println(lastName);
					break;
				case 17:
					// define beschreibung
					System.out.println(worksheet.getRow(row).getCell(index).getStringCellValue());
					break;
				}
				
			}
		}
		workbook.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void processExcelFiles() {
		
	    FileInputStream fis = null;
		for (File xlsFile : this.xlsFiles) {
			try {
				fis = new FileInputStream(xlsFile);
				// create workbook
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				HSSFSheet worksheet = workbook.getSheet(EXCEL_WORKBOOK_ZEITNACHWEISE);
				
				Week week = processWorksheet(worksheet);
				new DocHandler().createReportFile(week);
				
				workbook.close();
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Week processWorksheet(HSSFSheet worksheet) {
		/**
		 * TODO: process HSSFSheet "Zeitnachweise" to create entire Week
		 * 
		 * pseudo:
		 * - iterate over rows
		 * - create Record for each row by setting its properties by relevant columns
		 * => (1 - Vorgangszusammenfassung, 2 - Stunden, 3 - Arbeitsdatum, 17 - Arbeitsbeschreibung)
		 * - handle formatting before putting into Record
		 * - merge Records by Arbeitsdatum(3)
		 * - create list of days by merging Records by Vorgangszusammenfassung
		 * - create week by list of days
		 * - return the week
		 */
		
		Week week = null;
		return week;
	}
	public void loadExcelFiles(File dir) {
		this.xlsFiles = new FileUtils().getFilesByFormat(dir, XLS_FILEFORMAT);
	}
	public List<File> getXlsFiles() {
		return xlsFiles;
	}
	public List<String> getExcelFileFormats() {
		
		return RELEVANT_FILEFORMATS;
	}
}