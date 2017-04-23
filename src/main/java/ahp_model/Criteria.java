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

}
