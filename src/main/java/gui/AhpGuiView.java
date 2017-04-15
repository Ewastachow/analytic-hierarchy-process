package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class AhpGuiView {

    TreeItem<String> currentSelectedTreeItem;
    TreeItem<String> rootAlternativeItem;
    TreeItem<String> rootCriteriaItem;

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
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button applyMatrix;

    @FXML
    private Label alternativesLabel;

    @FXML
    private Label criteriasLabel;

    @FXML
    private FlowPane matrixFlowPane;

    @FXML
    private TextField nameTextField;

    @FXML
    void addButtonAction(ActionEvent event) {
        if (currentSelectedTreeItem != null && !nameTextField.getText().equals("")) {
            if (currentSelectedTreeItem.getParent().equals(rootAlternativeItem))
                rootAlternativeItem.getChildren().add(new TreeItem<>(nameTextField.getText()));
            else currentSelectedTreeItem.getChildren().add(new TreeItem<>(nameTextField.getText()));
        }
        nameTextField.clear();
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        if(currentSelectedTreeItem != null)
            currentSelectedTreeItem.getParent().getChildren().remove(currentSelectedTreeItem);
    }

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
    void alternativesTreeViewOnClick(MouseEvent event) {
        currentSelectedTreeItem = alternativesTreeView.getSelectionModel().getSelectedItem();
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
    void criteriasTreeViewOnClick(MouseEvent event) {
        currentSelectedTreeItem = criteriasTreeView.getSelectionModel().getSelectedItem();
    }

    @FXML
    void initialize() {
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert criteriasTreeView != null : "fx:id=\"criteriasTreeView\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert alternativesTreeView != null : "fx:id=\"alternativesTreeView\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert applyMatrix != null : "fx:id=\"applyMatrix\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert alternativesLabel != null : "fx:id=\"alternativesLabel\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert criteriasLabel != null : "fx:id=\"criteriasLabel\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert matrixFlowPane != null : "fx:id=\"matrixFlowPane\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'ahp_single_skeleton.fxml'.";

//        TreeItem<String> rootAlternativeItem= new TreeItem<>("Alternatives");
//        rootAlternativeItem.setExpanded(true);
//        for (int i = 1; i < 6; i++) rootAlternativeItem.getChildren().add(new TreeItem<String> ("Messagers" + i));
//        alternativesTreeView.setRoot(rootAlternativeItem);

        rootAlternativeItem = new TreeItem<>("Alternatives");
        alternativesTreeView.setShowRoot(false);
        alternativesTreeView.setRoot(rootAlternativeItem);

        rootCriteriaItem = new TreeItem<>();
        criteriasTreeView.setShowRoot(false);
        criteriasTreeView.setRoot(rootCriteriaItem);

//        rootAlternativeItem.getChildren().addAll(
//                new TreeItem<>(rootAlternativeItem.getChildren().size()+"Sodoma"),
//                new TreeItem<>(rootAlternativeItem.getChildren().size()+"Gomora"),
//                new TreeItem<>(rootAlternativeItem.getChildren().size()+"Lama"));

        rootAlternativeItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Lama"));
        rootAlternativeItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Alpaka"));
        rootAlternativeItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Foka"));

        rootCriteriaItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Lama"));
        rootCriteriaItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Alpaka"));
        rootCriteriaItem.getChildren().add(new TreeItem<>((rootAlternativeItem.getChildren().size()+1)+" "+"Foka"));




    }
}

