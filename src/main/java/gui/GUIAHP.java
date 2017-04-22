package gui;/**
 * Created by yevvy on 26/03/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIAHP extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("ahp_single_skeleton.fxml"));
        loader.setLocation(this.getClass().getResource("ahp_skeleton_1_welcome.fxml"));

        Pane pane = loader.load();

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Analytic Hierarchy Process");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}