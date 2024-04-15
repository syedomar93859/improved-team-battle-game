/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  April 12, 2024
 *  T12
 */

package ca.ucalgary.groupprojectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class HelloApplication extends Application {

    /**
     * The start method of the application.
     *
     * @param stage the initial stage to be set
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Final Project v1.0");
        stage.setScene(scene);
        stage.show();

        // Get the file path from the application parameters
        Parameters params = getParameters();
        List<String> args = params.getRaw();

        // if there is an argument, run Shell.main()
        if (!args.isEmpty()) {
            // Load the file
            HelloController controller = fxmlLoader.getController();
            File testFile = new File(args.get(0));
            controller.shellLoad(testFile);
        }
    }
    /**
     * Allows main to run from IDE
     *
     * @param args IDE arguments.
     */
    public static void main(String[] args) {
        Application.launch(args);

    }
}

class Shell {

    /**
     * Allows main to run from terminal
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }
}
