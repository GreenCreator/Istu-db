package ru.akoval.monitoring;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.akoval.monitoring.controllers.ApplicationMainController;

public class Main extends Application {

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample.fxml")
            );

            Parent root = loader.load();

            ApplicationMainController controller = loader.getController();
            primaryStage.setTitle("Мониторинг физической подготовленности студентов ИРНИТУ");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}