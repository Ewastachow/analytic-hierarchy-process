import ahp_model.AHP;
import ahp_model_to_xml.AhpToXml;
import ahp_xml.AHP_xml;
import console_app.CreateModel;

import java.io.File;

/**
 * Created by EwaStachow on 19/03/2017.
 */

public class ConsoleMain {

    public static void main(String[] args) {
        CreateModel cm = new CreateModel();
        AhpToXml source = new AhpToXml();

        AHP model = cm.startAsking();
        source.createXmlFromAHP(model, "src/main/resources/tree1.xml");
    }

}