package ihk.report.generator.view.util.dialogs;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Class YesNoDialog.
 *
 * <p>
 * DOCUMENT ME
 * </p>
 *
 * @author kluckow
 */
public class CloseProgramDialog extends Alert {

    /**
     * Instantiates a new close program dialog.
     *
     * @param owner the owner
     */
    @SuppressWarnings("restriction")
    public CloseProgramDialog(Stage owner) {
        super(AlertType.CONFIRMATION);
        setupLabels();
        processResult(owner);
    }

    /**
     * Process result.
     *
     * @param owner the owner
     */
    private void processResult(Stage owner) {

        Optional<ButtonType> result = showAndWait();
        if (result.get() == ButtonType.OK) {
            owner.close();
        } else {
            close();
        }
    }

    /**
     * Setup labels.
     */
    private void setupLabels() {

        setTitle("Programm schließen");
        setHeaderText(null);
        setContentText("Möchten sie das Programm wirklich schließen?");
    }

}
