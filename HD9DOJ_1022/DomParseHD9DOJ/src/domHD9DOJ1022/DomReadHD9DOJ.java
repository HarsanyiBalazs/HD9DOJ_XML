package domHD9DOJ1022;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadHD9DOJ {
	public static void main(String argv[]) throws SAXException,IOException, ParserConfigurationException
    {
        File xmlFile = new File("hallgatoHD9DOJ.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        //a parse metodus elemzi az xml f�jlt a document

        doc.getDocumentElement().normalize();
        //a dokumentum normaliz�l�sa seg�t a helyes eredm�nyek el�r�s�ben

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //megkapjuk a dok gy�k�relem�t

        NodeList nList= doc.getElementsByTagName("hallgato");
        //a getElementsByTagName seg�k megkapni a hallgato elem nodelistj�t a dokban

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if(nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("keresztnev").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
                String pname = node3.getTextContent();

                System.out.printf("User id: %s%n", uid);
                System.out.printf("First name: %s%n", fname);
                System.out.printf("Last name: %s%n", lname);
                System.out.printf("Profession: %s%n", pname);
            }
        }
    }
}
