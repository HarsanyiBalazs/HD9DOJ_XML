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
			
			//az XPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//meg kell adni az el�r�si �t kifejez�st �s a csom�pont list�t
			
			//String bl = "/hallgatok/hallgato";
			//String bl ="/hallgatok/hallgato[attribute::id=02]";
			//String bl ="//hallgato";
			//String bl="/hallgatok/hallgato[2]";
			//String bl="/hallgatok/hallgato[last()]";
			//String bl="/hallgatok/hallgato[last()-1]";
			//String bl="/hallgatok/hallgato[position()<3]";
			//String bl="//hallgatok/*";
			
			//K�sz�tsen egy list�t majd a xPath kifejez�st le kell forditani �s ki kell �rt�kelni
			NodeList neptunKod = (NodeList) xPath.compile(bl).evaluate(document,XPathConstants.NODESET);
			
			// afor ciklus seg�ts�g�vel a NodeList csom�pontjain v�gig kell iter�lni
			for(int i = 0;i<neptunKod.getLength();i++){
				
				// Kivessz�k a NodeListb�l az aktu�lis Node elemet
				Node node = neptunKod.item(i);
				
				System.out.println("\n Aktu�lis elem: " + node.getNodeName());
				
				//meg kell vizsg�lni a csom�pontot, tesztelni kell a subelemet
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hallgato")){
					
					// a node elemet elementt� konvert�ljuk, hogy hozz�f�rhess�nk az elem specifikus met�dusaihoz
					Element element = (Element) node;
					
					//az id attrib�tumot ad vissza
					System.out.println("Hallgat� ID: " + element.getAttribute("id"));
					
					System.out.println("Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becen�v: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					
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
