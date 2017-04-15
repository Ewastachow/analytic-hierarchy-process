package console_app;

import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;
import ahp_model_to_xml.AhpToXml;
import ahp_xml.AHP_xml;

import java.io.File;

/**
 * Created by yevvy on 02/04/2017.
 */
public class App {

    public static void main(String[] args) {

        CreateModel cm = new CreateModel();
        AhpToXml source = new AhpToXml();

        AHP model = cm.startAsking();
        source.createXmlFromAHP(model, "src/main/resources/tree1.xml");
    }

}
