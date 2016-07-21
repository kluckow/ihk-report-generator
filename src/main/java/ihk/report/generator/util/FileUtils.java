package ihk.report.generator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	// TODO: unit test
	public List<String> getFormatsInDir(File dir, List<String> formatsToCheck) {
		
		List<String> fileFormatsFound = new ArrayList<String>();
		for (String format : formatsToCheck) {
			for (File file : dir.listFiles()) {
				// ignore directories
				if (file.isDirectory()) {
					continue;
				} else if (fileFormatsFound.contains(format)) {
					// skip all other checks for this format
					break;
				}
				// end check if all formats found
				if (fileFormatsFound.size() == formatsToCheck.size()) {
					return fileFormatsFound;
				} else if (file.getName().endsWith(format)) {
					fileFormatsFound.add(format);
				}
			}
		}
		// make sure to return null if no relevant formats found
		fileFormatsFound = fileFormatsFound.isEmpty() ? null : fileFormatsFound;
		return fileFormatsFound;
	}
	
	// TODO: unit test
	public List<File> getFilesByFormat(File dir, String format) {
		
		List<File> filesFound = new ArrayList<File>();
		for (File file : dir.listFiles()) {
			if (file.isDirectory() || !file.getName().endsWith(format)) {
				continue;
			} else {
				filesFound.add(file);
			}
		}
		return filesFound;
	} 
}
