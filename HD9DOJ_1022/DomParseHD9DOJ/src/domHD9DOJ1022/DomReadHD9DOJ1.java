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

public class DomReadHD9DOJ1 {
	public static void main(String argv[]) throws SAXException,IOException, ParserConfigurationException
    {
        File xmlFile = new File("HD9DOJ_orarend.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        //a parse metodus elemzi az xml fájlt a document

        doc.getDocumentElement().normalize();
        //a dokumentum normalizálása segít a helyes eredmények elérésében

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //megkapjuk a dok gyökérelemét

        NodeList nList= doc.getElementsByTagName("ora");
        //a getElementsByTagName segík megkapni a hallgato elem nodelistjét a dokban

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("id");
                
                String tipus = elem.getAttribute("tipus");
                
                
                
                Node idoList = elem.getElementsByTagName("idopont").item(0);
                Element idoPont = (Element) idoList;
                Node node5 = idoPont.getElementsByTagName("nap").item(0);
                String nap = node5.getTextContent();
               
                Node node6 = idoPont.getElementsByTagName("tol").item(0);
                String tol = node6.getTextContent();
                
                Node node7 = idoPont.getElementsByTagName("ig").item(0);
                String ig = node7.getTextContent();
                

                Node node1 = elem.getElementsByTagName("targy").item(0);                             
                String targy = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("oktato").item(0);
                String oktato = node3.getTextContent();
                
                Node node4 = elem.getElementsByTagName("szak").item(0);
                String szak = node4.getTextContent();

                System.out.printf("ora id: %s%n", uid);
                System.out.printf("ora tipus: %s%n", tipus);
                System.out.printf("targy: %s%n", targy);
                System.out.printf("helyszin: %s%n", helyszin);
                System.out.printf("oktato: %s%n", oktato);
                System.out.printf("szak: %s%n", szak);
                System.out.printf("nap: %s%n", nap);
                System.out.printf("tol: %s%n", tol);
                System.out.printf("ig: %s%n", ig);
            }
        }
    }
}
