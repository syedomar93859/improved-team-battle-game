package ca.ucalgary.groupprojectgui;
/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  April 12, 2024
 *  T12
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * run GUI
 * */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Demo 3");
        stage.setScene(scene);
        stage.show();

        // Get the file path from the application parameters
        Parameters params = getParameters();
        List<String> args = params.getRaw();
        if (!args.isEmpty()) {
            // Load the file
            HelloController controller = (HelloController) fxmlLoader.getController();
            File testFile = new File(args.get(0));
            controller.shellLoad(testFile);
        }
    }

    public static void main(String[] args) {
        // This main method will be used when running from the IDE
        Application.launch(args);
    }
}

/**
 * run main
 */
class Shell {
    public static void main(String[] args) {
        // This main method will be used when running from the terminal
        Application.launch(HelloApplication.class, args);
    }
}








