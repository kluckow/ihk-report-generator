package ihk.report.generator.view.util.dialogs;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

@SuppressWarnings("restriction")
public class ChooseDialogHelper {

	private String title;
	
	public ChooseDialogHelper(String title) {
		this.title = title;
	}
	
	public File openDirectoryChooser() {
		
		DirectoryChooser dcExcel = new DirectoryChooser();
		dcExcel.setTitle(this.title);
//		dcExcel.setInitialDirectory(new File(System.getProperty("user.dir")));
		return dcExcel.showDialog(null);
	}
	
	public File openFileChooser() {
		
		FileChooser fcExcel = new FileChooser();
		fcExcel.setTitle(this.title);
//		fcExcel.setInitialDirectory(new File(System.getProperty("user.dir")));
		return fcExcel.showOpenDialog(null);
	}
}
