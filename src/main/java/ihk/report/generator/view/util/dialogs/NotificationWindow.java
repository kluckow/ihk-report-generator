package ihk.report.generator.view.util.dialogs;

import javafx.scene.control.Alert;

/**
 * Class YesNoDialog.
 *
 * <p>
 * DOCUMENT ME
 * </p>
 *
 * @author kluckow
 */
public class NotificationWindow extends Alert {
    
    /**
     * Instantiates a new notification window.
     *
     * @param title the title
     * @param message the message
     */
    public NotificationWindow(String title, String message) {
        super(AlertType.INFORMATION);
        setTitle(title);
        setHeaderText(null);
        setContentText(message);
        showAndWait();
    }

}
