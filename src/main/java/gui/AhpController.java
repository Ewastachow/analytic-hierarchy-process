package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AhpController {

    TreeItem<String> currentSelectedTreeItem;
    TreeItem<String> rootAlternativeItem;
    TreeItem<String> rootCriteriaItem;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mainLabel_1;

    @FXML
    private Pane manPane_1;

    @FXML
    private Button nextButton_1;
    @FXML
    private Button nextButton_3;
    @FXML
    private Button nextButton_4;
    @FXML
    private Button nextButton_5;
    @FXML
    private Label mainLabel_2;

    @FXML
    private Pane manPane_2;

    @FXML
    private TreeView<String> alternativeTreeView_2;

    @FXML
    private TreeView<String> criteriaTreeView_2;

    @FXML
    private TextField nameTextField_2;

    @FXML
    private Button removeButton_2;

    @FXML
    private Button addButton_2;

    @FXML
    private Button nextButton_2;


    @FXML
    void nextButtonOnAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if(event.getSource()==nextButton_1){
            stage=(Stage) nextButton_1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_2_set_criteria.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            init2();
        }else if(event.getSource()==nextButton_2){
            stage=(Stage) nextButton_2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_3_matrixes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if(event.getSource()==nextButton_3){
            stage=(Stage) nextButton_3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_4_save.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if(event.getSource()==nextButton_4){
            stage=(Stage) nextButton_4.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_5_goodbye.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            //todo zamknąć okno

        }
    }


    @FXML
    void init2(MouseEvent event) {
        rootAlternativeItem = new TreeItem<>("Alternatives");
//        alternativeTreeView_2.setShowRoot(false);
        alternativeTreeView_2.setRoot(rootAlternativeItem);

        rootCriteriaItem = new TreeItem<>("Criterias");
//        criteriaTreeView_2.setShowRoot(false);
        criteriaTreeView_2.setRoot(rootCriteriaItem);
    }

    @FXML
    void addButtonAction_2(ActionEvent event) {
        try{
            if (currentSelectedTreeItem != null && !nameTextField_2.getText().equals("")) {
                if (currentSelectedTreeItem.equals(rootAlternativeItem) ||
                        currentSelectedTreeItem.getParent().equals(rootAlternativeItem))
                    rootAlternativeItem.getChildren().add(new TreeItem<>(nameTextField_2.getText()));
                else currentSelectedTreeItem.getChildren().add(new TreeItem<>(nameTextField_2.getText()));
            }
            nameTextField_2.clear();
        }catch (Exception e){
            currentSelectedTreeItem.getChildren().add(new TreeItem<>(nameTextField_2.getText()));
            nameTextField_2.clear();
        }
    }

    @FXML
    void alternativeTreeViewClicked_2(MouseEvent event) {
        currentSelectedTreeItem = alternativeTreeView_2.getSelectionModel().getSelectedItem();
    }

    @FXML
    void criteriaTreeViewClicked_2(MouseEvent event) {
        currentSelectedTreeItem = criteriaTreeView_2.getSelectionModel().getSelectedItem();
    }

    @FXML
    void removeButtonAction_2(ActionEvent event) {
        if(currentSelectedTreeItem != null &&
                !(currentSelectedTreeItem==rootAlternativeItem) &&
                !(currentSelectedTreeItem==rootCriteriaItem))
            currentSelectedTreeItem.getParent().getChildren().remove(currentSelectedTreeItem);
    }

    @FXML
    void initialize() {
        assert mainLabel_1 != null : "fx:id=\"mainLabel_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert manPane_1 != null : "fx:id=\"manPane_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert nextButton_1 != null : "fx:id=\"nextButton_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert mainLabel_2 != null : "fx:id=\"mainLabel_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert manPane_2 != null : "fx:id=\"manPane_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
//        assert alternativeTreeView_2 != null : "fx:id=\"alternativeTreeView_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
//        assert criteriaTreeView_2 != null : "fx:id=\"criteriaTreeView_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert nameTextField_2 != null : "fx:id=\"nameTextField_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert removeButton_2 != null : "fx:id=\"removeButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert addButton_2 != null : "fx:id=\"addButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert nextButton_2 != null : "fx:id=\"nextButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";

    }
}
