package ahp_model_to_xml;

import Jama.Matrix;
import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yevvy on 02/04/2017.
 */
public class AhpToXmlTest {
    @Test
    public void createXmlFromAHP() throws Exception {
        AHP ahpTest = new AHP();
        List<Alternative> altList = new ArrayList<>();
        altList.add(new Alternative(1, "lama"));
        altList.add(new Alternative(2, "alpaka"));
        altList.add(new Alternative(3, "mialmial"));
        ahpTest.alternativesList = altList;
        double[][] matrix1 = new double[2][2];
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[1][0] = 0.5;
        matrix1[1][1] = 1;
        double[][] matrix2 = new double[3][3];
        matrix2[0][0] = 1;
        matrix2[0][1] = 2;
        matrix2[0][2] = 3;
        matrix2[1][0] = 0.5;
        matrix2[1][1] = 1;
        matrix2[1][2] = 2;
        matrix2[2][0] = 0.3;
        matrix2[2][1] = 0.5;
        matrix2[2][2] = 1;
        List<Criteria> subcritList = new ArrayList<>();
        subcritList.add(new Criteria(new Matrix(matrix2), "emilia"));
        subcritList.add(new Criteria(new Matrix(matrix2), "lolia"));
        List<Criteria> critList = new ArrayList<>();
        critList.add(new Criteria(new Matrix(matrix1),"hasSubcrit", subcritList));
        critList.add(new Criteria(new Matrix(matrix2), "telast"));
        Criteria crit = new Criteria(new Matrix(matrix1), "start", critList);
        ahpTest.mainCriterium = crit;

        AhpToXml lama = new AhpToXml();
        lama.createXmlFromAHP(ahpTest,"src/test/resources/ahp_model_to_xml/createTest2.xml");
    }

}