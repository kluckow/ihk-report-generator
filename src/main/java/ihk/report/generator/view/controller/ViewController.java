package ihk.report.generator.view.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ihk.report.generator.excel.ExcelReader;
import ihk.report.generator.util.FileUtils;
import ihk.report.generator.view.util.dialogs.ChooseDialogHelper;
import ihk.report.generator.view.util.dialogs.NotificationWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

@SuppressWarnings("restriction")
public class ViewController implements Initializable {

	@FXML
	private Button btnSearchExcelDirectory;
	
	@FXML
	private TextField inputExcelDirectory;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("init view...");
		
		configureComponents();
	}
	
	private void configureComponents() {

		this.btnSearchExcelDirectory.setOnAction(e -> {
			e.consume();
			System.out.println("test");
			File excelDir = new ChooseDialogHelper("Verzeichnisauswahl für Excel-Dateien (.xls/.xslx)").openDirectoryChooser();
			processSelectedDir(excelDir);
		});
	}
	
	private void processSelectedDir(File dir) {
		
		if (dir == null) {
			System.out.println("excel dir is: invalid (null)!");
			// do nothing, user just canceled directory chooser
		} else {
			System.out.println("dir is: " + dir.getAbsolutePath());
			
			List<String> fileFormats = new FileUtils().getFormatsInDir(dir, new ExcelReader().getExcelFileFormats());
			if (fileFormats != null) {
				for (String fileFormat : fileFormats) {
					System.out.println(fileFormat);
				}
				new NotificationWindow("DEBUG", "Following formats found: " + fileFormats.toString().substring(1, fileFormats.toString().length() - 1));
				this.inputExcelDirectory.setText(dir.getAbsolutePath());
			} else {
				new NotificationWindow("Information", "The selected directory does not contain any .xls/.xlsx files!");
			}
		}
	}

}
