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
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

@SuppressWarnings("restriction")
public class ViewController implements Initializable {

	/**
	 * Root component
	 * Used to handle stage
	 */
	@FXML
	private StackPane mainPain;
	
	/**
	 * Components in global MenuBar
	 */
	@FXML
	private MenuItem menuItemCloseProgram;
	
	/**
	 * Components in TitledPane 1 for the form for the coverpage
	 */
	@FXML
	private TextField inputCoverLastName;
	@FXML
	private TextField inputCoverFirstName;
	@FXML
	private TextField inputCoverBirthday;
	@FXML
	private TextField inputCoverBirthLocation;
	@FXML
	private TextField inputCoverPostcode;
	@FXML
	private TextField inputCoverCity;
	@FXML
	private TextField inputCoverStreet;
	@FXML
	private TextField inputCoverStreetNr;
	@FXML
	private TextField inputCoverProfession;
	@FXML
	private TextField inputCoverStartDate;
	@FXML
	private TextField inputCoverEndDate;
	@FXML
	private TextField inputCoverCompany;
	@FXML
	private Button btnCoverGenerateCover;
	
	/**
	 * Components in TitledPane 2 for choosing the directory containing .xls files
	 */
	@FXML
	private Button btnSearchExcelDirectory;
	@FXML
	private TextField inputExcelDirectory;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("init view...");
		configureComponents();
		
		// TODO: remove when done with testing
//		DocWriter docWriter = new DocWriter();
//		docWriter.readCoverpageTest();
	}
	
	/**
	 * Configure components.
	 */
	private void configureComponents() {

		/**
		 * Global MenuBar
		 */
		this.menuItemCloseProgram.setOnAction(e -> {
//			e.consume();
			System.out.println("Closing Program...");
			// TODO: "Möchten sie das Programm wirklich schließen? dialog when accordion (any form) is filled with data
			Stage stage = (Stage) mainPain.getScene().getWindow();
		    stage.close();
		});
		
		/**
		 * Titled Pane 1 (coverpage form)
		 */
		this.btnCoverGenerateCover.setOnAction(e -> {
//			e.consume();
			System.out.println("Validate form and create coverpage as .doc file.");
			// TODO: validateCoverpageForm();
			// TODO: createCoverpage();
			// TODO: on success - ask the user to keep/reset form data
		});
		/**
		 * Titled Pane 2 (exceldir chooser)
		 */
		this.btnSearchExcelDirectory.setOnAction(e -> {
//			e.consume();
			System.out.println("Opening DirectoryChooser for exceldir.");
			File excelDir = new ChooseDialogHelper("Verzeichnisauswahl für Excel-Dateien (.xls/.xslx)").openDirectoryChooser();
			processSelectedDir(excelDir);
		});
		/**
		 * Titled Pane 3 (TODO: what is in third pane)
		 */
	}

	/**
	 * Process selected dir.
	 * 
	 * Notifies the user when there are no
	 * excel files inside the chosen directory.
	 *
	 * @param dir the dir
	 */
	private void processSelectedDir(File dir) {
		
		if (dir == null) {
			System.out.println("excel dir is: invalid (null)!");
			// do nothing, user just canceled directory chooser
		} else {
			System.out.println("dir is: " + dir.getAbsolutePath());
			
			List<String> fileFormats = new FileUtils().getExistingFormatsInDirByFormats(dir, new ExcelReader().getExcelFileFormats());
			if (fileFormats != null) {
				for (String fileFormat : fileFormats) {
					System.out.println(fileFormat);
				}
				new NotificationWindow("DEBUG", "Following formats found: " + fileFormats.toString().substring(1, fileFormats.toString().length() - 1));
				this.inputExcelDirectory.setText(dir.getAbsolutePath());
			} else {
				// notifies the user that there are no excel files inside the chosen directory
				// TODO: exceldir->invalid (for weekly reports)
				new NotificationWindow("Information", "Das ausgewählte Verzeichnis enthält keine Excel-Dateien!");
			}
		}
	}

}
