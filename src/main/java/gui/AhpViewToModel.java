package gui;

import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yevv on 22.04.17.
 */
public class AhpViewToModel {

    public Criteria criteriasTreeToModel(TreeItem<String> treeItem){
        Criteria result;
        int size = treeItem.getChildren().size();
        if(size==0){
            result = new Criteria(treeItem.getValue());
        }else{
            List<Criteria> subCritList = new ArrayList<>();
            for(int i = 0; i < size; i++)
                subCritList.add(criteriasTreeToModel(treeItem.getChildren().get(i)));
            result = new Criteria(treeItem.getValue(), subCritList);
        }
        return result;
    }

    public List<Alternative> alternativesTreeToModel(TreeItem<String> treeItem){
        List<Alternative> result = new ArrayList<>();
        int size = treeItem.getChildren().size();
        for(int i = 0; i < size; i++)
            result.add(new Alternative(i, treeItem.getChildren().get(i).getValue()));
        return result;
    }

    public AHP createAHP(TreeItem<String> alternativeRoot, TreeItem<String> criteriaRoot){
        AHP result = new AHP();
        result.alternativesList = alternativesTreeToModel(alternativeRoot);
        result.mainCriterium = criteriasTreeToModel(criteriaRoot);
        return result;
    }
}
