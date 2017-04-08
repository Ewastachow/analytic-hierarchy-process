import ahp_xml.AHP_xml;

import java.io.File;

/**
 * Created by EwaStachow on 19/03/2017.
 */

public class Main {

    public static void main(String[] args) {

        File file = new File("src/main/resources/treeAHPtest2.xml");
        AHP_xml ahpTree = new AHP_xml(file);

        double[] ahpVector = ahpTree.createVectorWag();

        for( int i = 0; i < ahpVector.length; i++)
            System.out.print((i+1) + ": " + ahpVector[i] + "  \n" );
    }
}