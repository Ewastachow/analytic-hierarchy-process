package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class AhpGuiView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TreeView<String> criteriasTreeView;

    @FXML
    private TreeView<String> alternativesTreeView;

    @FXML
    private Button applyMatrix;

    @FXML
    private Label alternativesLabel;

    @FXML
    private Label criteriasLabel;

    @FXML
    private FlowPane matrixFlowPane;

    @FXML
    void alternativesTreeViewEditCancel(ActionEvent event) {

    }

    @FXML
    void alternativesTreeViewEditCommit(ActionEvent event) {

    }

    @FXML
    void alternativesTreeViewEditStart(ActionEvent event) {

    }

    @FXML
    void criteriasTreeViewEditCancel(ActionEvent event) {

    }

    @FXML
    void criteriasTreeViewEditCommit(ActionEvent event) {

    }

    @FXML
    void criteriasTreeViewEditStart(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert criteriasTreeView != null : "fx:id=\"criteriasTreeView\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert alternativesTreeView != null : "fx:id=\"alternativesTreeView\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert applyMatrix != null : "fx:id=\"applyMatrix\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert alternativesLabel != null : "fx:id=\"alternativesLabel\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert criteriasLabel != null : "fx:id=\"criteriasLabel\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert matrixFlowPane != null : "fx:id=\"matrixFlowPane\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";

        TreeItem<String> rootAlternativeItem= new TreeItem<>("Alternatives");
        rootAlternativeItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);
            rootAlternativeItem.getChildren().add(item);
        }
//        TreeView<String> tree = new TreeView<String> (rootAlternativeItem);
        alternativesTreeView.setRoot(rootAlternativeItem);



    }
}

