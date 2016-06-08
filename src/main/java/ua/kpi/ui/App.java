package ua.kpi.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.kpi.controller.MainController;


/**
 * Created by dmytryguly on 5/18/16.
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
        Scene scene = new Scene((Parent) controller.getView(), 600, 400);
        primaryStage.setTitle("App");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
