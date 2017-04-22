package ahp_model;

import Jama.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yevvy on 26/03/2017.
 */

public class Criteria implements Element{

    public Matrix matrix;
    public String name;
    public boolean hasSubcriteria;
    public List<Criteria> subcriteriaList;

    public Criteria(String name) {
        this.name = name;
        this.hasSubcriteria = false;
        this.subcriteriaList = null;
    }

    public Criteria(String name, List<Criteria> subcriteriaList) {
        this.name = name;
        this.hasSubcriteria = true;
        this.subcriteriaList = subcriteriaList;
    }

    public Criteria(Matrix matrix, String name) {
        this.matrix = matrix;
        this.name = name;
        this.hasSubcriteria = false;
        this.subcriteriaList = null;
    }

    public Criteria(Matrix matrix, String name, List<Criteria> subcriteriaList) {
        this.matrix = matrix;
        this.name = name;
        this.hasSubcriteria = true;
        this.subcriteriaList = subcriteriaList;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addSubCrit(String name){
        if(hasSubcriteria){
            subcriteriaList.add(new Criteria(name));
            matrix = addListElemMatrix((subcriteriaList.size()-1),matrix);
        }else{
            hasSubcriteria = true;
            subcriteriaList = new ArrayList<>();
            subcriteriaList.add(new Criteria(name));
            double[][] matrixTab = {{1,1},{1,1}};
            matrix = new Matrix(matrixTab);
        }
        //todo TEST IT, IT CAN't BE RIGHT

    }

    public Matrix addListElemMatrix(int element, Matrix oldMatrix){
        double[][] newMatrixTab = new double[(oldMatrix.getColumnDimension()+1)][(oldMatrix.getColumnDimension()+1)];

        for (int i = 0; i < (oldMatrix.getColumnDimension()+1); i++)
            for (int j = 0; j < (oldMatrix.getColumnDimension()+1); j++)
                if((i==element)||(j==element))
                    newMatrixTab[i][j] = 1;
                else if(i < element)
                    if(j < element)
                        newMatrixTab[i][j] = oldMatrix.get(i,j);
                    else newMatrixTab[i][j] = oldMatrix.get(i,(j-1));
                else if(j < element)
                    newMatrixTab[i][j] = oldMatrix.get((i-1),j);
                else newMatrixTab[i][j] = oldMatrix.get((i-1),(j-1));
        //todo write tdd
        Matrix newMatrix = new Matrix(newMatrixTab);
        return newMatrix;
    }
}
