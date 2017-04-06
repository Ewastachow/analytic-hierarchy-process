package console_app;

import Jama.Matrix;
import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yevvy on 02/04/2017.
 */
public class CreateModel {

    Scanner scanner;

    public CreateModel() {
        scanner = new Scanner(System.in);
    }

    public AHP startAsking(){ //todo
        AHP result = new AHP();
        result.alternativesList = askAlternatives();
        result.criteriasList = askCriterias(result.alternativesList.size(), new ArrayList<>());
        return result;
    }

    public List<Alternative> askAlternatives(){
        List<Alternative> result = new ArrayList<>();
        String ifNewAlt = null;
        System.out.print("Do you want to add new alternative? [y/n] \n");
        ifNewAlt = scanner.nextLine();
        while(!ifNewAlt.contains("n")){
            result.add(askAlternative(result.size()));
            System.out.print("Do you want to add new alternative? [y/n] \n");
            ifNewAlt = scanner.nextLine();
        }
        return result;
    }

    public Alternative askAlternative(int id){
        System.out.print("Write alternative name \n");
        String altName = scanner.nextLine();
        return new Alternative(id+1, altName);
    }

    public List<Criteria> askCriterias(int altNumber, List<String> path){
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

    public Criteria askCriterium(int altNumber, List<String> path){
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

    public void checkMatrixes(){
        List<String> currentPath = new ArrayList<>();
    }

    public Matrix askMatrix(){
        return null;
    }


}
