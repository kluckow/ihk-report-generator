package ihk.report.generator;

import java.io.IOException;
import java.net.URL;

import ihk.report.generator.view.util.dialogs.NotificationWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The Class App.
 */
@SuppressWarnings("restriction")
public class App extends Application {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

    	loadGUI(primaryStage);
    }

	/**
	 * Load gui.
	 *
	 * @param primaryStage the primary stage
	 */
	public void loadGUI(Stage primaryStage) {
		
		try {
            prepareStage(primaryStage);
    	} catch (IOException e) {
    		new NotificationWindow("Programmfehler", "Das Programm konnte leider nicht gestartet werden!");
    	}
	}

	/**
	 * Prepare stage.
	 *
	 * @param primaryStage the primary stage
	 * @param fxmlUrl the fxml url
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void prepareStage(Stage primaryStage) throws IOException {
		
		URL fxmlUrl = getClass().getClassLoader().getResource("ihk-report-view.fxml");
		StackPane page = (StackPane) FXMLLoader.load(fxmlUrl);
		Scene scene = new Scene(page);
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			primaryStage.close();
		});
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("IHK Report Generator");
		primaryStage.show();
	}
}
