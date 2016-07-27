package ihk.report.generator.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * The Class FileUtilsTest.
 */
public class FileUtilsTest {

	/**
	 * Test.
	 */
	@Test
	public void testGetFilesByFormat() {
		
		/**
		 * params: File dir, String format
		 * success-result: List<Files> filesFound must not be null, filesFound.isEmpty() can be true 
		 * cases:
		 * 1. TODO
		 */
		List<File> filesFound = new ArrayList<File>();
		assertTrue(filesFound.isEmpty());
	}

}
