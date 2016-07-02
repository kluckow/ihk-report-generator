package ihk.report.generator;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    	loadGUI(primaryStage);
    }

	public void loadGUI(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getClassLoader().getResource("ihk-report-view.fxml");
            StackPane page = (StackPane) FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(page);
            primaryStage.setOnCloseRequest(e -> {
    			e.consume();
    			primaryStage.close();
    		});
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("IHK Report Generator");
            primaryStage.show();
    	} catch (IOException e) {
    		System.out.println("asdf");
    	}
	}
}
