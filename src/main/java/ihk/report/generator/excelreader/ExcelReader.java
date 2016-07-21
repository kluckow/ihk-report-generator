package ihk.report.generator.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.print.attribute.standard.PrinterLocation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ihk.report.generator.excelreader.util.Configuration;

public class ExcelReader {

	public void readAllExcelSheets(File dir) throws Exception {

		FileInputStream fileInStream = new FileInputStream("test.xls");
		
		/**
		 *  TODO:
		 *  Solve date problem => Maybe ignore dates and just create .doc-file for each week and remove not needed files manually
		 */
//		input stream with xls file
//		create workbook
		HSSFWorkbook workbook = new HSSFWorkbook(fileInStream);
//		get the worksheet (jira exports sheet called "Zeitnachweise")
		HSSFSheet worksheet = workbook.getSheet("Zeitnachweise");
//		get any row in order to get the number of columns
		
		int rowCount = worksheet.getPhysicalNumberOfRows();
		
		int cellCount = (int) worksheet.getRow(1).getLastCellNum();
		
		List<String> relevantHeaders = Configuration.relevantHeaders;
		
//		relevantHeaders.add("Vorgangszusammenfassung");
//		relevantHeaders.add("Stunden");
//		relevantHeaders.add("Arbeitsdatum");
//		relevantHeaders.add("Voller Name");
//		relevantHeaders.add("Arbeitsbeschreibung");
		
//		System.out.println("Das Worksheet hat " + rowCount + " Zeilen");
//		System.out.println("Das Worksheet hat " + cellCount + " Spalten");
		
//		HSSFRow currRow;
		
//		get the relevant column by column headers
		List<Integer> relevantRowIndices = new ArrayList<>();
		int headerRowIndex = 0;
		boolean isRelevantHeader;
		HSSFRow headerRow = worksheet.getRow(headerRowIndex);
//		System.out.println("---------------------------------------------------");
		
		
		// get relevant column indices
		for (int col = 0; col < cellCount; col++) {
			
			isRelevantHeader = relevantHeaders.contains(headerRow.getCell(col).getStringCellValue());
			// if header is relevant...
			if (isRelevantHeader) {
				
				// save indices
				relevantRowIndices.add(col);
				
//				System.out.print(headerRow.getCell(col).getStringCellValue() + ", Index: ");
//				System.out.println(col);
			}
		}
		
		// counters for differencing cells
		int numericCounter = 0;
		int stringCounter = 0;
//		for each no-header row, create entry, so one less than rows existing
//		Entry[] entry = new Entry[rowCount-1];
		
		// create Entry objects
		// iterate over rows
		// starting at 1, because headers are in row = 0
		for (int row = 1; row < rowCount; row++) {
			
			// create Entry Object
//			entry[row-1] = new Entry(); 
			
			for (int index: relevantRowIndices) {
				
				int cellType = worksheet.getRow(row).getCell(index).getCellType();
//				print index of cell
//				System.out.print(worksheet.getRow(row).getCell(index).getCellType() + " ");
				switch (index) {
				case (1):
					// define vorgang
					String vorgang = worksheet.getRow(row).getCell(index).getStringCellValue();
					// add vorgang to entry
//					entry[row-1].setVorgang(vorgang);
					System.out.println(vorgang);
					break;
				case (2):
					// define stunden
					double stunden = worksheet.getRow(row).getCell(index).getNumericCellValue();
					// add stunden to entry
//					entry[row-1].setStunden(stunden);
					System.out.println(stunden);
					break;
				case (3):
					// define datum
					Date date = worksheet.getRow(row).getCell(index).getDateCellValue();
//					date = date.substring(0, 10);
//					entry[row-1].setDatum(date);
					System.out.println(date.toLocaleString().substring(0, 10));
					break;
				case (5):
					// define fullName
					String fullName = worksheet.getRow(row).getCell(index).getStringCellValue();
					//  defin firstName and lastName by splitting by space character
					String[] tmp = fullName.split(" ");
					String firstName = tmp[0];
					String lastName = tmp[1];

//					entry[row-1].setFullName(fullName);
//					entry[row-1].setFirstName(firstName);
//					entry[row-1].setLastName(lastName);
					
					System.out.println(fullName);
//					System.out.println(firstName);
//					System.out.println(lastName);
					break;
				case (17):
					// define beschreibung
					System.out.println(worksheet.getRow(row).getCell(index).getStringCellValue());
					break;
				}
				
				System.out.print("");
			}
			System.out.println();
		}
		
		
		
		
		
//		System.out.print("Die relevanten Indices sind:");
//		for (int colIndex: relevantRowIndices) {
//			System.out.print(" " + colIndex);
//		}
//		System.out.println();
		
		
		
		
		
//		for (int row = 0; row < rowCount; row++) {
//			
//			currRow = worksheet.getRow(row);
//			System.out.println("Zeile " + (row + 1) + ":");
//			
//			
//			
//			for (int cell = 0; cell < cellCount; cell++) {
//				
//				System.out.print("\t");
////				for worked time info
//				if (row >= 1) {
//					// if header
//					if (row == 1) {
//						System.out.println(currRow.getCell(cell).getNumericCellValue());
//					} else if (cell == 2) {
//						System.out.println(currRow.getCell(cell).getDateCellValue());
//					} else {
//						System.out.println(currRow.getCell(cell).getStringCellValue());
//					}
//					
//				} else {
//					for (String header: relevantHeaders) {
//						if (header.contains(currRow.getCell(cell).getStringCellValue())) {
//							System.out.println(currRow.getCell(cell).getStringCellValue());
//						}
//					}
//				}
//				
//			}
//		}
//		HSSFCell cellA1 = row1.getCell(6);
//		System.out.println(cellA1.getStringCellValue());
	}
}