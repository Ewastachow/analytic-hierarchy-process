package console_app;

import Jama.Matrix;
import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;

import java.util.*;

import static ahp_model.AHPConsistency.checkConsistency;

/**
 * Created by yevvy on 02/04/2017.
 */
public class CreateModel {

    Scanner scanner;

    public CreateModel() {
        scanner = new Scanner(System.in);
    }

    public AHP startAsking(){
        AHP result = new AHP();
        result.alternativesList = askAlternatives();
        result.mainCriterium = new Criteria(new Matrix(1,1,1),
                "start",askCriterias(result.alternativesList.size(),
                Collections.singletonList("start")));
        System.out.print("Set consistency ratio:");
        double ratio = scanner.nextDouble();
        checkMatrixes(result.mainCriterium, result.alternativesList, ratio);
        return result;
    }

    private List<Alternative> askAlternatives(){
        List<Alternative> result = new ArrayList<>();
        String ifNewAlt = null;
        ifNewAlt = "y";
        while(!ifNewAlt.contains("n")){
            result.add(askAlternative(result.size()));
            System.out.print("Do you want to add new alternative? [y/n] \n");
            ifNewAlt = scanner.nextLine();
        }
        return result;
    }

    private Alternative askAlternative(int id){
        System.out.print("Write alternative name \n");
        String altName = scanner.nextLine();
        return new Alternative(id+1, altName);
    }

    private List<Criteria> askCriterias(int altNumber, List<String> path){
        System.out.print(path.toString()+"\n");
        List<Criteria> result = new ArrayList<>();
        String ifNewCrit = "y";
        while(!ifNewCrit.contains("n")){
            result.add(askCriterium(altNumber, path));
            System.out.print(path.toString()+"\n");
            System.out.print("Do you want to add new criterium? [y/n] \n");
            ifNewCrit = scanner.nextLine();
        }
        return result;
    }

    private Criteria askCriterium(int altNumber, List<String> path){
        System.out.print("Write criterium name \n");
        String critName = scanner.nextLine();
        System.out.print("Do you want to add subcriteria? [y/n] \n");
        String ifHasSubCrit = scanner.nextLine();
        if(!ifHasSubCrit.contains("n")) {
            List<String> tmpPath = new ArrayList<>(path);
            tmpPath.add(critName);
            List<Criteria> subCritList = askCriterias(altNumber,tmpPath);
            Matrix resultMatrix = new Matrix(subCritList.size(), subCritList.size(), 1);
            return new Criteria(resultMatrix, critName, subCritList);
        }
        Matrix resultMatrix = new Matrix(altNumber, altNumber, 1);
        return new Criteria(resultMatrix, critName);
    }

    private void checkMatrixes(Criteria crit, List<Alternative> altList, double ratio){

        if(crit.hasSubcriteria){
            System.out.print("[ "+crit.name+" ] Compare Criteria \n");
            crit.matrix = askMatrix(stringListFromCritList(crit.subcriteriaList), ratio);
            for(Criteria i : crit.subcriteriaList)
                checkMatrixes(i,altList, ratio);
        }else{
            System.out.print("[ "+crit.name+" ] Compare Alternative \n");
            crit.matrix = askMatrix(stringListFromAltList(altList), ratio);
        }
    }

    private Matrix askMatrix(List<String> compareList, double ratio){
        double[][] matrix = new double[compareList.size()][compareList.size()];
        for(int i=0; i<compareList.size(); i++){
            for(int j=i+1; j<compareList.size(); j++){
                System.out.print("How better is "+ compareList.get(i) + " then " + compareList.get(j) +" \n");
                Double among = scanner.nextDouble();
                matrix[i][j] = among;
            }
        }
        for(int i=0; i<compareList.size(); i++){
            for(int j=0;  j<compareList.size(); j++){
                if(i==j) matrix[i][j] = 1;
                else if(i>j) matrix[i][j] = 1/matrix[j][i];
            }
        }
        Matrix result = new Matrix(matrix);
        if(!checkConsistency(result, ratio)){
            System.out.print("Wrong!!!! Try once again \n");
            return askMatrix(compareList, ratio);
        }
        return result;
    }

    private List<String> stringListFromCritList(List<Criteria> list){
        List<String> result = new ArrayList<>();
        for(Criteria i: list)
            result.add(i.name);
        return result;
    }

    private List<String> stringListFromAltList(List<Alternative> list){
        List<String> result = new ArrayList<>();
        for(Alternative i: list)
            result.add(i.name);
        return result;
    }
}
