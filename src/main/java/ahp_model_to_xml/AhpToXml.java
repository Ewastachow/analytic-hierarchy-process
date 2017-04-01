package ahp_model_to_xml;

import Jama.Matrix;
import ahp_model.AHP;
import ahp_model.Alternative;
import ahp_model.Criteria;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

/**
 * Created by yevvy on 01/04/2017.
 */
public class AhpToXml {

    private Document documentXML;

    public AhpToXml() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            documentXML = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    void createXmlFromAHP(AHP ahpModel, String path){
        Element rootElement = documentXML.createElement("LAMA");
        documentXML.appendChild(rootElement);
        rootElement.appendChild(addAlternatives(ahpModel.alternativesList));
        rootElement.appendChild(addCriterias(ahpModel.criteriasList));




        //todo: Implement

        saveToFile(path);
    }

    Element addAlternatives(List<Alternative> altList){
        Element alternatives = documentXML.createElement("alternatives");
        for (int i = 0; i < altList.size(); i++) {
            Element alternative = documentXML.createElement("alternative");
            Attr attr = documentXML.createAttribute("id");
            attr.setValue(Integer.toString(altList.get(i).id));
            alternative.setAttributeNode(attr);
            alternative.appendChild(documentXML.createTextNode(altList.get(i).name));
            alternatives.appendChild(alternative);
        }
        return alternatives;
    }

    Element addCriterias(List<Criteria> critList){
        Element criterias = documentXML.createElement("criterias");
        for (int i = 0; i < critList.size(); i++)
            criterias.appendChild(addCriterium(critList.get(i)));
        return criterias;
    }

    Element addCriterium(Criteria crit){
        Element criterium = documentXML.createElement("criteria");

        Attr attr = documentXML.createAttribute("name");
        attr.setValue(crit.name);
        criterium.setAttributeNode(attr);

        if(crit.hasSubcriteria==true){
            Element wag = documentXML.createElement("wag");
            wag.appendChild(documentXML.createTextNode(stringFromMatrix(crit.matrix)));
            criterium.appendChild(wag);
            for (int i=0; i<crit.subcriteriaList.size(); i++)
                criterium.appendChild(addCriterium(crit.subcriteriaList.get(i)));
        }else{
            Element comparing = documentXML.createElement("comparing");
            comparing.appendChild(documentXML.createTextNode(stringFromMatrix(crit.matrix)));
            criterium.appendChild(comparing);
        }
        return criterium;
    }

    String stringFromMatrix(Matrix matrix){
        String pauze = " ";
        String result = "";

        //todo Implement

        return result;
    }

    void saveToFile(String path){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documentXML);
            StreamResult result = new StreamResult(new File(path));
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
