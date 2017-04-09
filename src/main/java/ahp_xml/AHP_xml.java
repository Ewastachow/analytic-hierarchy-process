package ahp_xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by EwaStachow on 15/03/2017.
 */

public class AHP_xml {

    private Document documentXML;

    public AHP_xml(File file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            documentXML = dBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentXML.getDocumentElement().normalize();
    }

    public double[] createVectorWag() {

        Element root = documentXML.getDocumentElement();
        Element criteriaStart = (Element)root.getElementsByTagName("criteria").item(0);
        return postorder(criteriaStart,1).vector;
    }

    private VectorWag postorder(Element element, double wagValue){
        if(element.getElementsByTagName("criteria").getLength() == 0){
            VectorWag partVectorWag = new VectorWag();
            partVectorWag.wag = wagValue;
            partVectorWag.vector = createVectorFromMatrix(createAlternativeMatrix(element, "comparing"));
            return partVectorWag;
        } else {
            NodeList childrenList = element.getChildNodes();
            List<VectorWag> criteriaList = new ArrayList<>();
            int tabSize = 0;
            for(int i = 0; i < childrenList.getLength(); i++)
                if(childrenList.item(i).getNodeName().equals("criteria"))
                    tabSize++;
            double[] tmpVector = createVectorFromMatrix(createAlternativeMatrix(element, "wag"));

            double[] subcriteriasWagVeector = new double[tabSize];

            for(int i = 0; i< subcriteriasWagVeector.length; i++)
                if(tmpVector.length > i) subcriteriasWagVeector[i] = tmpVector[i];
                else subcriteriasWagVeector[i] = 0;

            int tabIterator = 0;
            for(int i = 0; i < childrenList.getLength(); i++)
                if (childrenList.item(i).getNodeName().equals("criteria")){
//                    System.out.print("vector size: " + subcriteriasWagVeector.length + " ; TabIt:  "  + tabIterator + "\n");
                    criteriaList.add(postorder((Element)childrenList.item(i), subcriteriasWagVeector[tabIterator]));
                    tabIterator++;
                }
//            System.out.print("Lama \n");
            return new VectorWag(createDecisionVector(criteriaList),1);
        }
    }

    private Matrix createAlternativeMatrix(Element criteriaElement, String categoryName) {
        String matrixString = criteriaElement.getElementsByTagName(categoryName).item(0).getTextContent();
        String[] matrixStringTab = matrixString.split(" ");
        int matrixSize = (int)Math.sqrt(matrixStringTab.length);
        double[][] futureMatrix = new double[matrixSize][matrixSize];
        for(int i = 0; i < matrixSize; i++)
            for(int j = 0; j < matrixSize; j++)
                futureMatrix[i][j] = Double.parseDouble(matrixStringTab[matrixSize*i+j]);
        return new Matrix(futureMatrix);
    }

    private double[] createVectorFromMatrix(Matrix matrix) {
        double maxEignevalue = 0;
        int index = 0;

        EigenvalueDecomposition decomposedMatrix = matrix.eig();
        double[] realEignevalues = decomposedMatrix.getRealEigenvalues();
        for (int i = 0; i < realEignevalues.length; i++) {
            realEignevalues[i] = Math.abs(realEignevalues[i]);
            if (realEignevalues[i] > maxEignevalue) {
                maxEignevalue = realEignevalues[i];
                index = i;
            }
        }
        Matrix vector = decomposedMatrix.getV();
        int vectorSize = vector.getColumnDimension();
        double[] resultVector = new double[vectorSize];
        for (int i = 0; i < vectorSize; i++)
            resultVector[i] = vector.get(i, index);
        double suma = 0;
        for (int j = 0; j < vectorSize; j++)
            suma += resultVector[j];
        for (int j = 0; j < vectorSize; j++)
            resultVector[j] = resultVector[j] / suma;

        return resultVector;
    }

    private double[] createDecisionVector(List<VectorWag> vectorList) {
        int vectorSize = vectorList.get(0).vector.length;
        double[] resultVector = new double[vectorSize];
        for (VectorWag i : vectorList)
            for(int j = 0; j < vectorSize; j++)
                resultVector[j] += i.vector[j] * i.wag;
        double suma = 0;
        for (int i = 0; i < vectorSize; ++i)
            suma += resultVector[i];
        for (int i = 0; i < vectorSize; ++i)
            resultVector[i] = resultVector[i] / suma;
        return resultVector;
    }
}
