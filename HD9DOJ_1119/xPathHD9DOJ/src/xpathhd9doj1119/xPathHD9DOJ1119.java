package xpathhd9doj1119;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathHD9DOJ1119 {
	
	public static void main(String[] args){
		
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentHD9DOJ.xml");
			
			
			document.getDocumentElement().normalize();
			
			//az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//meg kell adni az elérési út kifejezést és a csomópont listát
			
			//String bl = "/hallgatok/hallgato";
			//String bl ="/hallgatok/hallgato[attribute::id=02]";
			//String bl ="//hallgato";
			//String bl="/hallgatok/hallgato[2]";
			//String bl="/hallgatok/hallgato[last()]";
			//String bl="/hallgatok/hallgato[last()-1]";
			//String bl="/hallgatok/hallgato[position()<3]";
			//String bl="//hallgatok/*";
			
			//Készítsen egy listát majd a xPath kifejezést le kell forditani és ki kell értékelni
			NodeList neptunKod = (NodeList) xPath.compile(bl).evaluate(document,XPathConstants.NODESET);
			
			// afor ciklus segítségével a NodeList csomópontjain végig kell iterálni
			for(int i = 0;i<neptunKod.getLength();i++){
				
				// Kivesszük a NodeListbõl az aktuális Node elemet
				Node node = neptunKod.item(i);
				
				System.out.println("\n Aktuális elem: " + node.getNodeName());
				
				//meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hallgato")){
					
					// a node elemet elementté konvertáljuk, hogy hozzáférhessünk az elem specifikus metódusaihoz
					Element element = (Element) node;
					
					//az id attribútumot ad vissza
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
					
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				
				}
			}
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(XPathExpressionException e){
			e.printStackTrace();
		}
	}

}
