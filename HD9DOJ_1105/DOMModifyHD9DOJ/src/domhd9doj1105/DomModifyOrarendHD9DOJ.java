package domhd9doj1105;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyOrarendHD9DOJ {

public static void main(String arv[]){
		
		
		try{
			File inputFile = new File("HD9DOJ_orarend.xml");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(inputFile);
			
			Node orarend = doc.getFirstChild();
			
			NodeList orak = doc.getElementsByTagName("ora");
			
			Node ora = doc.getElementsByTagName("ora").item(0);
			
			// orak attributumainak módosítása
			for(int i=0; i< orak.getLength();i++ ){
				Node node = orak.item(i);
				NamedNodeMap attr = node.getAttributes();
				Node nodeAttr = attr.getNamedItem("tipus");
				
				if(nodeAttr.getTextContent().equals("gyakorlat")){
					nodeAttr.setTextContent("eloadas");
				}else{
					nodeAttr.setTextContent("gyakorlat");
				}
			}
			
			Element node = doc.createElement("oraado");
			node.appendChild(doc.createTextNode("Bednarik László"));
			
			ora.appendChild(node);
			
			//tartalom konzolra írása
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "2");
			
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