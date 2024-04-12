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

/**
 * run GUI
 *
 * */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Demo 3");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * run main
     *
     * */
    public static void main(String[] args) {
        if (args != null) {
            if (args.length > 0) {
                File testFile = new File(args[0]);
                HelloController controller = new HelloController();
                controller.shellLoad(testFile);
                launch(args);
            } else {
                System.out.println("Please provide a file as an argument.");
            }
        }
    }
}



