package ihk.report.generator.view.controller;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import ihk.report.generator.doc.DocHandler;
import ihk.report.generator.excel.ExcelReader;
import ihk.report.generator.util.FileUtils;
import ihk.report.generator.view.util.dialogs.CloseProgramDialog;
import ihk.report.generator.view.util.dialogs.DialogHelper;
import ihk.report.generator.view.util.dialogs.NotificationWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

/**
 * The Class ViewController.
 */
@SuppressWarnings("restriction")
public class ViewController implements Initializable {

    /**
     * The logger for this class.
     */
    private static final Logger LOG = Logger.getLogger(DocHandler.class);
    
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
	private DatePicker datepickerCoverBirthday;
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
	private DatePicker datepickerCoverStartDate;
	@FXML
	private DatePicker datepickerCoverEndDate;
	@FXML
	private TextField inputCoverCompany;
	@FXML
	private Button btnCoverGenerateCover;
	
	/**
	 * Components in TitledPane 2 for choosing the directory containing .xls files
	 */
	@FXML
	private TextField inputExcelDirectory;
	@FXML
	private Button btnSearchExcelDirectory;
	@FXML
	private Button btnStartExcelConversion;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	    LOG.debug("Initialize view.");
		configureComponents();
		
		// just for faster test cycles
		this.inputCoverLastName.setText("Kluckow");
		this.inputCoverFirstName.setText("Markus");
		this.datepickerCoverBirthday.getEditor().setText("05.08.1989");
		this.inputCoverBirthLocation.setText("Düsseldorf");
		this.inputCoverPostcode.setText("40237");
		this.inputCoverCity.setText("Düsseldorf");
		this.inputCoverStreet.setText("Rethelstrasse");
		this.inputCoverStreetNr.setText("45");
		this.inputCoverProfession.setText("Software-Entwickler");
		this.datepickerCoverStartDate.getEditor().setText("01.08.2014");
		this.datepickerCoverEndDate.getEditor().setText("31.07.2017");
		this.inputCoverCompany.setText("COMINTO GmbH");
	}
	
	/**
	 * Configure components.
	 */
	private void configureComponents() {

		/**
		 * Global MenuBar
		 */
		this.menuItemCloseProgram.setOnAction(e -> {
		    LOG.debug("Closing program.");
			Stage stage = (Stage) mainPain.getScene().getWindow();
			new CloseProgramDialog(stage);
		});
		
		/**
		 * Titled Pane 1 (coverpage form)
		 */
		this.btnCoverGenerateCover.setOnAction(e -> {
		    
			if (!validateCoverpageForm()) {
			    new NotificationWindow("Formular ungültig!", "Bitte überprüfen Sie Ihre Eingaben!");
			    LOG.debug("Coverpage form is invalid.");
			    return;
			} else if (createCoverpage()) {
			    // TODO: new YesNoDialog() to ask the user to keep/reset form data 
			    LOG.debug("Successfully created coverpage as .docx file.");
			} else {
			    // TODO: inform the user about failure of creating coverpage 
			    LOG.error("Could not create coverpage!");
			}
			
		});
		
		/**
		 * Titled Pane 2 (exceldir chooser)
		 */
		this.btnSearchExcelDirectory.setOnAction(e -> {
		    LOG.debug("Opening DirectoryChooser for exceldir.");
			File excelDir = new DialogHelper("Verzeichnisauswahl für Excel-Dateien (.xls/.xslx)").openDirectoryChooser();
			processSelectedDir(excelDir);
		});
		this.btnStartExcelConversion.setDisable(true);
		this.btnStartExcelConversion.setOnAction(e -> {
		    new ExcelReader().start(this.inputExcelDirectory.getText());
		});
		
		/**
		 * Titled Pane 3 (TODO: what is in third pane)
		 */
	}

    private boolean createCoverpage() {
        DocHandler docHandler = new DocHandler();
        docHandler.setupCoverpage(coverFormToMap());
        return false;
    }
    
    private Map<String, String> coverFormToMap() {
        
        Map<String, String> coverFormMap = new HashMap<>();
        
        // validation implicates that form elements are not null
        coverFormMap.put(DocHandler.PLACEHOLDER_LAST_NAME, this.inputCoverLastName.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_FIRST_NAME, this.inputCoverFirstName.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_BIRTHDAY, this.datepickerCoverBirthday.getEditor().getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_BIRTH_LOCATION, this.inputCoverBirthLocation.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_POST_CODE, this.inputCoverPostcode.getText().trim() + " " + this.inputCoverCity.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_STREET_NR, this.inputCoverStreet.getText().trim() + " " + this.inputCoverStreetNr.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_PROFESSION, this.inputCoverProfession.getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_START_DATE, this.datepickerCoverStartDate.getEditor().getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_END_DATE, this.datepickerCoverEndDate.getEditor().getText().trim());
        coverFormMap.put(DocHandler.PLACEHOLDER_COMPANY, this.inputCoverCompany.getText().trim());
        return coverFormMap;
    }

    private boolean validateCoverpageForm() {
        LOG.debug("Validate coverpage form.");
        // TODO: validate coverpage form via regexp
        return true;
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
		    // do nothing, user just canceled directory chooser
		    LOG.debug("Directory is invalid or chooser was canceled.");
		} else {
			System.out.println("dir is: " + dir.getAbsolutePath());
			LOG.debug("Selected dir is: " + dir.getAbsolutePath());
			
			List<String> fileFormats = new FileUtils().getExistingFormatsInDirByFormats(dir, new ExcelReader().getExcelFileFormats());
			if (!fileFormats.isEmpty()) {
				for (String fileFormat : fileFormats) {
				    LOG.debug("Found file format: " + fileFormat);
				}
				new NotificationWindow("DEBUG", "Following formats found: " + fileFormats.toString().substring(1, fileFormats.toString().length() - 1));
				this.inputExcelDirectory.setText(dir.getAbsolutePath());
				this.btnStartExcelConversion.setDisable(false);
			} else {
				// notifies the user that there are no excel files inside the chosen directory
				// TODO: exceldir->invalid (for weekly reports)
				new NotificationWindow("Information", "Das ausgewählte Verzeichnis enthält keine Excel-Dateien!");
			}
		}
	}

}
