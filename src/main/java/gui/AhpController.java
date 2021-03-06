package gui;

import java.io.File;
import java.io.IOException;

import Jama.Matrix;
import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;
import ahp_model_to_xml.AhpToXml;
import ahp_xml.AHP_xml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import static ahp_model.AHPConsistency.checkConsistency;

public class AhpController {

    private static Integer windowConter;

    private TreeItem<String> currentSelectedTreeItem;
    private TreeItem<String> rootAlternativeItem;
    private TreeItem<String> rootCriteriaItem;
    private TreeItem<Criteria> rootCriteriaTree;
    private TreeItem<Criteria> currentSelectedCriterium;

    private AhpViewToModel controller = new AhpViewToModel();
    private static AHP ahp = new AHP();
    private double consistencyRatio = 0.1;
    private double[][] currentMatrix;

    private String xmlPath;

    @FXML
    private Label mainLabel_1;
    @FXML
    private Pane manPane_1;
    @FXML
    private Button nextButton_1;
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
    private Label mainLabel_3;
    @FXML
    private Pane manPane_3;
    @FXML
    private TreeView<Criteria> criteriaTreeView_3;
    @FXML
    private Button checkConsistencyButton_3;
    @FXML
    private Label consistencyLabel_3;
    @FXML
    private Button changeConsistencyButton_3;
    @FXML
    private Label ratioLabel_3;
    @FXML
    private TextField ratioTextField_3;
    @FXML
    private Button nextButton_3;
    @FXML
    private FlowPane matrixFlowPane;
    @FXML
    private VBox leftWhatCompareFlowPane;
    @FXML
    private VBox upWhatCompareFlowPane;
    @FXML
    private TextField xmlFileNameTextField_4;
    @FXML
    private Label messegesLabel_4;
    @FXML
    private VBox vectorFlowPane_4;

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
        }else if(event.getSource()==nextButton_2){
            ahp = controller.createAHP(rootAlternativeItem, rootCriteriaItem);
            stage=(Stage) nextButton_2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_3_matrix.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if(event.getSource()==nextButton_3){
            stage=(Stage) nextButton_3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ahp_skeleton_4_save.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void addButtonAction_2(ActionEvent event) {
        TreeItem<String> newObject = new TreeItem<>(nameTextField_2.getText());
        newObject.setExpanded(true);
        try{
            if (currentSelectedTreeItem != null && !nameTextField_2.getText().equals("")) {
                if (currentSelectedTreeItem.equals(rootAlternativeItem) ||
                        currentSelectedTreeItem.getParent().equals(rootAlternativeItem))
                    rootAlternativeItem.getChildren().add(newObject);
                else currentSelectedTreeItem.getChildren().add(newObject);
            }
            nameTextField_2.clear();
        }catch (Exception e){
            assert currentSelectedTreeItem != null;
            currentSelectedTreeItem.getChildren().add(newObject);
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
        currentSelectedTreeItem = null;
    }

    @FXML
    void changeConsistencyButtonAction_3(ActionEvent event) {
        if(!ratioTextField_3.getText().isEmpty()){
            try{
                consistencyRatio = Double.parseDouble(ratioTextField_3.getText());
            }catch(Exception ex){
                System.out.print("Text inside");
            }
            ratioLabel_3.setText(String.valueOf(consistencyRatio));
            ratioTextField_3.clear();
        }
    }

    @FXML
    void checkConsistencyButtonAction_3(ActionEvent event) {
        if(currentSelectedCriterium!=null){
            if(checkConsistency(currentSelectedCriterium.getValue().matrix, consistencyRatio))
                consistencyLabel_3.setText("Yes");
            else
                consistencyLabel_3.setText("No");
        }
    }

    @FXML
    void criteriaTreeViewClicked_3(MouseEvent event) {
        currentSelectedCriterium = criteriaTreeView_3.getSelectionModel().getSelectedItem();
        if(currentSelectedCriterium!=null){
            createMatrixFlowPane(currentSelectedCriterium.getValue());
        }
        consistencyLabel_3.setText("");
    }

    @FXML
    void generateXMLFileButtonAction_4(ActionEvent event) {
        if(ahp.alternativesList!=null &&
                ahp.alternativesList.size()!=0 &&
                ahp.mainCriterium.subcriteriaList!=null &&
                ahp.mainCriterium.subcriteriaList.size()!=0){
            if(!xmlFileNameTextField_4.getText().isEmpty()){
                String name = xmlFileNameTextField_4.getText();
                xmlPath = "src/main/resources/"+name+".xml";
                AhpToXml source = new AhpToXml();
                source.createXmlFromAHP(ahp, xmlPath);
                messegesLabel_4.setText("Successfuly generated");
            }else{
                messegesLabel_4.setText("Wrong name");
            }
        }else messegesLabel_4.setText("Wrong AHP Created");
        xmlFileNameTextField_4.setText("");
    }

    @FXML
    void generateVectorButtonAction_4(ActionEvent event) {
        vectorFlowPane_4.getChildren().clear();
        if(xmlPath!=null && !xmlPath.isEmpty()){
            File file = new File(xmlPath);
            AHP_xml ahpTree = new AHP_xml(file);
            double[] ahpVector = ahpTree.createVectorWag();
            int amongAltSize = 0;
            for(Alternative i : ahp.alternativesList)
                if(i.name.length()>amongAltSize) amongAltSize = i.name.length();
            for(int i = 0; i < ahpVector.length; i++){
                Label label = new Label();
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(160);
                String doubleString = String.valueOf(ahpVector[i]);
                if(doubleString.length() > 4) doubleString = doubleString.substring(0,4);
                StringBuilder name = new StringBuilder(ahp.alternativesList.get(i).name);
                while(name.length() < amongAltSize)
                    name.append(" ");
                label.setText(name+" - "+doubleString);
                vectorFlowPane_4.getChildren().add(label);
            }
        }
    }

    @FXML
    void initialize() {
        assert mainLabel_1 != null : "fx:id=\"mainLabel_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert manPane_1 != null : "fx:id=\"manPane_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert nextButton_1 != null : "fx:id=\"nextButton_1\" was not injected: check your FXML file 'ahp_template.fxml'.";
        assert mainLabel_2 != null : "fx:id=\"mainLabel_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert manPane_2 != null : "fx:id=\"manPane_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert alternativeTreeView_2 != null : "fx:id=\"alternativeTreeView_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert criteriaTreeView_2 != null : "fx:id=\"criteriaTreeView_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert nameTextField_2 != null : "fx:id=\"nameTextField_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert removeButton_2 != null : "fx:id=\"removeButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert addButton_2 != null : "fx:id=\"addButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert nextButton_2 != null : "fx:id=\"nextButton_2\" was not injected: check your FXML file 'ahp_skeleton_2_set_criteria.fxml'.";
        assert mainLabel_3 != null : "fx:id=\"mainLabel_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert manPane_3 != null : "fx:id=\"manPane_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert criteriaTreeView_3 != null : "fx:id=\"criteriaTreeView_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert checkConsistencyButton_3 != null : "fx:id=\"checkConsistencyButton_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert consistencyLabel_3 != null : "fx:id=\"consistencyLabel_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert changeConsistencyButton_3 != null : "fx:id=\"changeConsistencyButton_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert ratioLabel_3 != null : "fx:id=\"ratioLabel_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert ratioTextField_3 != null : "fx:id=\"ratioTextField_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";
        assert nextButton_3 != null : "fx:id=\"nextButton_3\" was not injected: check your FXML file 'ahp_skeleton_3_matrixes.fxml'.";

        if(windowConter==null){
            windowConter=1;
        }else if(windowConter==1){
            windowConter=2;
            init2();
        }else if(windowConter==2){
            windowConter=3;
            init3();
        }else if(windowConter==3) {
            windowConter = 4;
        }
    }

    private void init2() {
        rootAlternativeItem = new TreeItem<>("Alternatives");
        alternativeTreeView_2.setRoot(rootAlternativeItem);
        rootAlternativeItem.setExpanded(true);

        rootCriteriaItem = new TreeItem<>("Criterias");
        criteriaTreeView_2.setRoot(rootCriteriaItem);
        rootCriteriaItem.setExpanded(true);
    }

    private void init3() {
        ratioLabel_3.setText(String.valueOf(consistencyRatio));
        if(ahp.mainCriterium!=null &&
                ahp.mainCriterium.subcriteriaList!=null &&
                ahp.mainCriterium.subcriteriaList.size()!=0 &&
                ahp.alternativesList!=null &&
                ahp.alternativesList.size()!=0){
            rootCriteriaTree = createCriteriaItem(ahp.mainCriterium);
            criteriaTreeView_3.setRoot(rootCriteriaTree);
            createMatrix(ahp.mainCriterium);
        }
        matrixFlowPane.setOnMouseClicked(this::clickedOnCell);
    }

    private TreeItem<Criteria> createCriteriaItem(Criteria crit){
        TreeItem<Criteria> result = new TreeItem<>(crit);
        result.setExpanded(true);
        if(crit.hasSubcriteria)
            for(Criteria i : crit.subcriteriaList)
                result.getChildren().add(createCriteriaItem(i));
        return result;
    }

    @FXML
    private void clickedOnCell(MouseEvent event){
        double posX = event.getX();
        double posY = event.getY();
        double w, h;
        int x = 0;
        int y = 0;
        int size = currentSelectedCriterium.getValue().matrix.getColumnDimension();
        w = (matrixFlowPane.getPrefWidth() - (matrixFlowPane.getPrefWidth() % size)) / size;
        h = (250 - (250 % (double)size)) / (double)size;
        while (posX > w) {
            x++;
            posX -= w;
        }
        while (posY > h) {
            y++;
            posY -= h;
        }
        if( x > y ){
            if(event.getButton()== MouseButton.PRIMARY){
                if ( currentMatrix[y][x] >= 1 && currentMatrix[y][x] < 9){
                    currentMatrix[y][x] = currentMatrix [y][x] +1;
                }else if(1/currentMatrix[y][x] > 1 && 1/currentMatrix[y][x] < 3){
                    currentMatrix[y][x] = 1;
                }else if(currentMatrix[y][x] > 0 && currentMatrix[y][x] < 0.5){
                    Double tmp = 1 / currentMatrix[y][x];
                    int tmp2 = tmp.intValue();
                    tmp2 = tmp2-1;
                    double tmp3 = (double) tmp2;
                    currentMatrix[y][x] = 1/tmp3;
                }
            }else if(event.getButton() == MouseButton.SECONDARY){
                if ( currentMatrix[y][x] > 1 && currentMatrix[y][x] < 10){
                    currentMatrix[y][x] = currentMatrix [y][x] - 1;
                }else if(currentMatrix[y][x] > 0.12 && currentMatrix[y][x] <= 1){
                    Double tmp = 1 / currentMatrix[y][x];
                    int tmp2 = tmp.intValue();
                    tmp2 = tmp2+1;
                    double tmp3 = (double) tmp2;
                    currentMatrix[y][x] = 1/tmp3;
                }
            }
        }
        currentSelectedCriterium.getValue().matrix = tabToMatrix(currentMatrix);
        createMatrixFlowPane(currentSelectedCriterium.getValue());
    }

    private void createMatrixFlowPane(Criteria crit){
        matrixFlowPane.getChildren().clear();

        matrixFlowPane.setMaxWidth(250);
        matrixFlowPane.setPrefWidth(250);
        matrixFlowPane.setMinWidth(250);

        int among = crit.matrix.getColumnDimension();
        double size = (250 / among) - 4;

        currentMatrix = matrixToTab(crit.matrix);

        for(int i=0; i < among; i++)
            for(int j=0; j < among; j++){
                Label label = new Label();
                label.setAlignment(Pos.CENTER);
                label.setMaxWidth(size);
                label.setPrefWidth(size);
                label.setMinWidth(size);
                label.setMaxHeight(size);
                label.setPrefHeight(size);
                label.setMinHeight(size);

                if(j > i){
                    String text = String.valueOf(currentMatrix[i][j]);
                    if(text.length() > 4) text = text.substring(0,4);
                    label.setText(text);
                    label.setStyle("-fx-background-color: #a7adbe;");
                }else{
                    label.setText("");
                    label.setTextFill(Paint.valueOf("#000000"));
                    label.setStyle("-fx-background-color: #d9d9d9;");
                }
                matrixFlowPane.getChildren().add(label);
            }
        leftWhatCompareFlowPane.getChildren().clear();
        upWhatCompareFlowPane.getChildren().clear();
        if(crit.hasSubcriteria){
            for(Criteria i : crit.subcriteriaList){
                Label label = new Label();
                label.setMaxHeight(size);
                label.setPrefHeight(size);
                label.setMinHeight(size);
                label.setText(i.name);
                Label labelClone = new Label();
                labelClone.setMaxHeight(size);
                labelClone.setPrefHeight(size);
                labelClone.setMinHeight(size);
                labelClone.setText(i.name);
                leftWhatCompareFlowPane.getChildren().add(label);
                upWhatCompareFlowPane.getChildren().add(labelClone);
            }
        }else{
            for(Alternative i : ahp.alternativesList){
                Label labelClone = new Label();
                labelClone.setPrefHeight(size);
                labelClone.setMaxHeight(size);
                labelClone.setMinHeight(size);
                labelClone.setText(i.name);
                Label label = new Label();
                label.setPrefHeight(size);
                label.setMinHeight(size);
                label.setMaxHeight(size);
                label.setText(i.name);
                leftWhatCompareFlowPane.getChildren().add(label);
                upWhatCompareFlowPane.getChildren().add(labelClone);
            }
        }
    }

    private double[][] matrixToTab(Matrix matrix){
        return matrix.getArray();
    }

    private Matrix tabToMatrix(double[][] tab){
        int size = currentSelectedCriterium.getValue().matrix.getColumnDimension();
        for(int i = 0; i < size; i++ )
            for(int j = 0; j < size; j++ ){
                if(i == j){
                    tab[i][j] = 1;
                }else if(i > j){
                    tab[i][j] = 1 / tab[j][i];
                }
            }
        return new Matrix(tab);
    }

    private void createMatrix(Criteria crit){
        crit.matrix = setNewMatrix(crit);
        if(crit.hasSubcriteria){
            for(Criteria i : crit.subcriteriaList){
                createMatrix(i);
            }
        }
    }

    private Matrix setNewMatrix(Criteria crit){
        double[][] futureMatrix;
        if(crit.hasSubcriteria){
            int size = crit.subcriteriaList.size();
            futureMatrix = new double[size][size];
            for(int i = 0; i < size; i++)
                for(int j = 0; j < size; j++){
                    futureMatrix[i][j] = 1;
                }
        }else{
            int size = ahp.alternativesList.size();
            futureMatrix = new double[size][size];
            for(int i = 0; i < size; i++)
                for(int j = 0; j < size; j++){
                    futureMatrix[i][j] = 1;
                }
        }
        return new Matrix(futureMatrix);
    }
}





























