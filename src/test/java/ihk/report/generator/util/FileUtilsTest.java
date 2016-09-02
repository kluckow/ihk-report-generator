package ihk.report.generator.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * The Class FileUtilsTest.
 */
public class FileUtilsTest {

    /**
     * Tests cover the following cases:
     * - get all files with given format
     * - given format is an empty string
     * - given format is null
     * - list must not be null
     * - list is empty on invalid param
     */
    @Test
    public void testGetFilesByFormat() {

        assertThat(new FileUtils().getFilesByFormat(getTestDirectory(false), ".txt").isEmpty(), is(false));
        assertThat(new FileUtils().getFilesByFormat(getTestDirectory(false), ".txt").size(), is(2));
        
        assertThat(new FileUtils().getFilesByFormat(getTestDirectory(false), "").isEmpty(), is(true));
        assertThat(new FileUtils().getFilesByFormat(getTestDirectory(false), "").size(), is(0));
        
        assertThat(new FileUtils().getFilesByFormat(getTestDirectory(false), null).isEmpty(), is(true));
    }

    /**
     * Tests cover the following cases:
     * - given format list is empty or null
     * - multiple files of the same format exist
     * - directory is empty
     * - only return the matched formats
     */
    @Test
    public void testGetExistingFormatsInDirByFormats() {
        
        List<String> nullFormats = null;
        List<String> formats = Arrays.asList(".txt", ".xls", ".bak", "lala");
        List<String> expectedFormats = Arrays.asList(".txt", ".xls");
        
        assertTrue(new FileUtils().getExistingFormatsInDirByFormats(getTestDirectory(false), nullFormats) == null);
        assertThat(new FileUtils().getExistingFormatsInDirByFormats(getTestDirectory(false), formats), is(expectedFormats));
        assertTrue(new FileUtils().getExistingFormatsInDirByFormats(getTestDirectory(true), formats) == null);
    }

    /**
     * Gets the test directory.
     *
     * @param empty the empty
     * @return the test directory
     */
    private File getTestDirectory(boolean empty) {

        if (empty) {
            return new File(getClass().getClassLoader().getResource("file-utils-test/directory-empty").getFile());
        } else {
            return new File(getClass().getClassLoader().getResource("file-utils-test/directory").getFile());
        }
    }
}
