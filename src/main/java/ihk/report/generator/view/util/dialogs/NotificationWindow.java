package ihk.report.generator.view.util.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class NotificationWindow.
 */
@SuppressWarnings("restriction")
public class NotificationWindow {
	
	/** The Constant OK_BUTTON_TEXT. */
	private static final String OK_BUTTON_TEXT = "Ok";

	/** The window. */
	private Stage window;
	
	/** The Constant APPLICATION_TITLE. */
	private static final String APPLICATION_TITLE = "PDF Generator";
	
	/** The Constant WIDTH_CONFIRM_DIALOG. */
	private static final int WIDTH_CONFIRM_DIALOG = 400;
	
	/** The Constant HEIGHT_CONFIRM_DIALOG. */
	private static final int HEIGHT_CONFIRM_DIALOG = 200;
	
	/** The Constant CSS_STYLE_APPLICATION_BACKGROUND. */
//	private static final String CSS_STYLE_APPLICATION_BACKGROUND = "-fx-background-color: #DFF2FA";
	
	/** The Constant CSS_STYLE_NOTIFICATION_BACKGROUND. */
//	private static final String CSS_STYLE_NOTIFICATION_BACKGROUND = "-fx-background-color: #DFF2FA";

	/** The Constant CSS_CONTENT_INSETS. */
	private static final Insets CSS_CONTENT_INSETS = new Insets(10);
	
	/** The label. */
	private String label;
	
	/**
	 * Instantiates a new notification window.
	 *
	 * @param title the title
	 * @param label the label
	 */
	public NotificationWindow(String title, String label) {
		window = new Stage();
		window.setTitle(APPLICATION_TITLE + " - " + title);
		window.setWidth(WIDTH_CONFIRM_DIALOG);
		window.setHeight(HEIGHT_CONFIRM_DIALOG);
		this.setLabel(label);
		setupScene();
		setupConfig();
	}

	/**
	 * Setup config.
	 */
	public void setupConfig() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setResizable(false);
		window.showAndWait();
	}
	
	/**
	 * Setup scene.
	 */
	public void setupScene() {
		
		Label label = new Label(this.getLabel());
		HBox topHorizontalBox = new HBox();
		topHorizontalBox.getChildren().add(label);
		topHorizontalBox.setAlignment(Pos.CENTER);
		
		Button okButton = new Button(OK_BUTTON_TEXT);
		okButton.setOnAction(e -> {
//			e.consume();
			window.close();
		});
		HBox bottomHorizontalBox = new HBox();
		bottomHorizontalBox.getChildren().add(okButton);
		bottomHorizontalBox.setAlignment(Pos.CENTER);
		
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(topHorizontalBox, bottomHorizontalBox);
//		vBox.setStyle(CSS_STYLE_NOTIFICATION_BACKGROUND);
		
		StackPane pane = new StackPane();
		pane.setPadding(CSS_CONTENT_INSETS);
//		pane.setStyle(CSS_STYLE_APPLICATION_BACKGROUND);
		pane.getChildren().add(vBox);
		
		Scene scene = new Scene(pane);
		window.setScene(scene);
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
