import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IStudentServerClient extends Application {
    // Declare a Student instance
    private IStudentServer student;

    private Button btGetScore = new Button("Get Score");
    private TextField tfName = new TextField();
    private TextField tfScore = new TextField();

    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.add(new Label("Name"), 0, 0);
        gridPane.add(new Label("Score"), 0, 1);
        gridPane.add(tfName, 1, 0);
        gridPane.add(tfScore, 1, 1);
        gridPane.add(btGetScore, 1, 2);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(gridPane, 250, 250);
        primaryStage.setTitle("StudentServerInterfaceClient");
        primaryStage.setScene(scene);
        primaryStage.show();

        initializeRMI();
        btGetScore.setOnAction(e -> getScore());
    }

    private void getScore() {
        try {
            // Get student score
            double score = student.findScore(tfName.getText().trim());

            // Display the result
            if (score < 0)
                tfScore.setText("No permission to access");
            else
                tfScore.setText(score + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initializeRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            student = (IStudentServer) registry.lookup("IStudentServerImplementation");
            System.out.println("Server object " + student + " found");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
