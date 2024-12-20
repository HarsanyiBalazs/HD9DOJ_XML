package domhd9doj1105;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomModifyHD9DOJ {

	public static void main(String arv[]){
		
		
		try{
			File inputFile = new File("hallgatoHD9DOJ.xml");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(inputFile);
			
			Node hallgatok = doc.getFirstChild();
			
			Node hallgat = doc.getElementsByTagName("hallgato").item(0);
			
			// hallgat attributumainak módosítása
			NamedNodeMap attr = hallgat.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			nodeAttr.setTextContent("01");
			
			//loop the hallgat child node
			NodeList list = hallgat.getChildNodes();
			
			for(int temp = 0; temp < list.getLength(); temp++){
				Node node = list.item(temp);
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					
					if("keresztnev".equals(eElement.getNodeName())){
						if("Pal".equals(eElement.getTextContent())){
							eElement.setTextContent("Olivia");
						}
					}
					
					if("vezeteknev".equals(eElement.getNodeName())){
						if("Kiss".equals(eElement.getTextContent())){
							eElement.setTextContent("Vigh");
						}
					}
					
					
				}
				
				
				
			}
			
			//tartalom konzolra írása
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//ez domsource tatrtalmazza a dom fát
			DOMSource source = new DOMSource(doc);
			
			System.out.println("---Módosított fájl---");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
		}	catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}