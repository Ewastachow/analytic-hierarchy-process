package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;

public class ahpGuiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TreeView<?> alternativesTreeView;

    @FXML
    private TextField newCriteriaNameTextField;

    @FXML
    private Button addAlternativeButton;

    @FXML
    private Button deleteAlternativeButton;

    @FXML
    private TreeView<?> criteriasTreeView;

    @FXML
    private GridPane matrixGridPane;

    @FXML
    void addAlternativeButtonPressed(ActionEvent event) {

    }

    @FXML
    void deleteAlternativeButtonPressed(ActionEvent event) {

    }

    @FXML
    void newCriteriaNameTextFieldIDK(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert alternativesTreeView != null : "fx:id=\"alternativesTreeView\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
        assert newCriteriaNameTextField != null : "fx:id=\"newCriteriaNameTextField\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
        assert addAlternativeButton != null : "fx:id=\"addAlternativeButton\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
        assert deleteAlternativeButton != null : "fx:id=\"deleteAlternativeButton\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
        assert criteriasTreeView != null : "fx:id=\"criteriasTreeView\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
        assert matrixGridPane != null : "fx:id=\"matrixGridPane\" was not injected: check your FXML file 'ahp_skeleton.fxml'.";
    }
}
