package ihk.report.generator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class FileUtils.
 */
public class FileUtils {

	/**
	 * Gets the existing formats in dir by formats.
	 *
	 * @param dir the dir
	 * @param formatsToCheck the formats to check
	 * @return the existing formats in dir by formats
	 */
	public List<String> getExistingFormatsInDirByFormats(File dir, List<String> formatsToCheck) {
		
	    if (formatsToCheck == null || formatsToCheck.isEmpty()) {
	        return new ArrayList<>();
	    } 
	    
		List<String> fileFormatsFound = new ArrayList<>();
		for (String format : formatsToCheck) {
		    
			for (File file : dir.listFiles()) {
			    
				// ignore directories, dont check recursively currently
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
		fileFormatsFound = fileFormatsFound.isEmpty() ? new ArrayList<>() : fileFormatsFound;
		return fileFormatsFound;
	}
	
	/**
	 * Gets the files by format.
	 *
	 * @param dir the dir
	 * @param format the format
	 * @return the files by format
	 */
	public List<File> getFilesByFormat(File dir, String format) {
	    
	    if ("".equals(format) || format == null) {
	        return new ArrayList<>();
	    }
		List<File> filesFound = new ArrayList<>();
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
