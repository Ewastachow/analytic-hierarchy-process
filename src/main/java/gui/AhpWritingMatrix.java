//package gui;
//
//import Jama.Matrix;
//import ahp_model.Alternative;
//import ahp_model.Criteria;
//import javafx.scene.control.Label;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static ahp_xml.AHPConsistency.checkConsistency;
//
///**
// * Created by yevv on 22.04.17.
// */
//public class AhpWritingMatrix {
//
//    private void checkMatrixes(Criteria crit, List<Alternative> altList, double ratio, Label label){
//        if(crit.hasSubcriteria){
////            System.out.print("[ "+crit.name+" ] Compare Criteria \n");
//
//            crit.matrix = askMatrix(stringListFromCritList(crit.subcriteriaList), ratio, label);
//            for(Criteria i : crit.subcriteriaList)
//                checkMatrixes(i,altList, ratio, label);
//        }else{
//            System.out.print("[ "+crit.name+" ] Compare Alternative \n");
//            crit.matrix = askMatrix(stringListFromAltList(altList), ratio, label);
//        }
//    }
//
//    private Matrix askMatrix(List<String> compareList, double ratio, Label label){
//        label.setText("");
//        double[][] matrix = new double[compareList.size()][compareList.size()];
//        for(int i=0; i<compareList.size(); i++){
//            for(int j=i+1; j<compareList.size(); j++){
////                System.out.print("How better is "+ compareList.get(i) + " then " + compareList.get(j) +" \n");
//                String tmp = label.getText();
//                label.setText(tmp+"How better is "+ compareList.get(i) + " then " + compareList.get(j) +" \n");
//                Double among = scanner.nextDouble();
//                matrix[i][j] = among;
//            }
//        }
//        for(int i=0; i<compareList.size(); i++){
//            for(int j=0;  j<compareList.size(); j++){
//                if(i==j) matrix[i][j] = 1;
//                else if(i>j) matrix[i][j] = 1/matrix[j][i];
//            }
//        }
//        Matrix result = new Matrix(matrix);
//        if(!checkConsistency(result, ratio)){
//            System.out.print("Wrong!!!! Try once again \n");
//            return askMatrix(compareList, ratio, label);
//        }
//        return result;
//    }
//
//    private List<String> stringListFromCritList(List<Criteria> list){
//        List<String> result = new ArrayList<>();
//        for(Criteria i: list)
//            result.add(i.name);
//        return result;
//    }
//
//    private List<String> stringListFromAltList(List<Alternative> list){
//        List<String> result = new ArrayList<>();
//        for(Alternative i: list)
//            result.add(i.name);
//        return result;
//    }
//}
