package ahp_model;

import Jama.Matrix;

import java.util.List;

/**
 * Created by yevvy on 26/03/2017.
 */

public class Criteria {

    public Matrix matrix;
    public String name;
    public boolean hasSubcriteria;
    public List<Criteria> subcriteriaList;

}
