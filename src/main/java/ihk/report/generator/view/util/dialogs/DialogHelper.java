package ihk.report.generator.view.util.dialogs;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * The Class ChooseDialogHelper.
 */
@SuppressWarnings("restriction")
public class DialogHelper {

	private String title;
	
	/**
	 * Instantiates a new dialog helper.
	 *
	 * @param title the title
	 */
	public DialogHelper(String title) {
		this.title = title;
	}
	
	/**
	 * Open directory chooser.
	 *
	 * @return the file
	 */
	public File openDirectoryChooser() {
		
		DirectoryChooser dcExcel = new DirectoryChooser();
		dcExcel.setTitle(this.title);
//		dcExcel.setInitialDirectory(new File(System.getProperty("user.dir")));
		return dcExcel.showDialog(null);
	}
	
	/**
	 * Open file chooser.
	 *
	 * @return the file
	 */
	public File openFileChooser() {
		
		FileChooser fcExcel = new FileChooser();
		fcExcel.setTitle(this.title);
//		fcExcel.setInitialDirectory(new File(System.getProperty("user.dir")));
		return fcExcel.showOpenDialog(null);
	}
}
