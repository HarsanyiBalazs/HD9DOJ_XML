package hu.domparse.hd9doj;

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
			File inputFile = new File("XMLHD9DOJ.xml");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(inputFile);
			
			// dolgozó 2 és 3 idjainak megcserélése
			// megkapja az elementeket amelyeknek az attributomait változtatjuk, lekéri az attributumaikat 
			// lekeri az id attributomot majd új szöveget ad meg értéknek
			Node dolgoz1 = doc.getElementsByTagName("Dolgozo").item(1);
			Node dolgoz2 = doc.getElementsByTagName("Dolgozo").item(2);
			
			NamedNodeMap attr1 = dolgoz1.getAttributes();
			Node nodeAttr1 = attr1.getNamedItem("id");
			nodeAttr1.setTextContent("d3");
			
			NamedNodeMap attr2 = dolgoz2.getAttributes();
			Node nodeAttr2 = attr2.getNamedItem("id");
			nodeAttr2.setTextContent("d2");
			
			
			//elso dolgozo nevének átírása
			//végig megy az összes dolgozó elem listájának összes elemén
			//amelyik elem megegyezik a Nev elemmel ott megnézi, hogy az értéke megegyezik a cserélnedő név értékével
			NodeList dlist = doc.getElementsByTagName("Dolgozo");
			
			
			
			for(int temp = 0; temp < dlist.getLength(); temp++){
				Node node = dlist.item(temp);
				
				NodeList list = node.getChildNodes();
				
				for(int count = 0; count < list.getLength(); count++){
					
					Node node1 = list.item(count);
					
					if(node1.getNodeType() == Node.ELEMENT_NODE){
						Element nElement = (Element) node1;
						
						if("Nev".equals(nElement.getNodeName())){
							if("Lakatos Ottó".equals(nElement.getTextContent())){
								nElement.setTextContent("Lakatos Ferenc");
							}
						}									
					}
				}
				
			}
			
			//a laptop eszkoznevek átírása számítógépre
			//végig megy az összes eszköz elem listájának összes elemén
			//amelyik elem megegyezik a Eszkoz_nev elemmel ott megnézi, hogy az értéke megegyezik a cserélnedő név értékével
			NodeList elist = doc.getElementsByTagName("Eszkoz");
			
			
			
			for(int temp = 0; temp < elist.getLength(); temp++){
				Node node = elist.item(temp);
				
				NodeList list = node.getChildNodes();
				
				for(int count = 0; count < list.getLength(); count++){
					
					Node node1 = list.item(count);
					
					if(node1.getNodeType() == Node.ELEMENT_NODE){
						Element nElement = (Element) node1;
						
						if("Eszkoz_nev".equals(nElement.getNodeName())){
							if("Laptop".equals(nElement.getTextContent())){
								nElement.setTextContent("Szamitogep");
							}
						}									
					}
				}
				
			}
			
			// A attributomba megadni, hogy be vane töltve a beosztas
			//végig megy az összes beosztas elemen
			//ha létezik olyan attributom amelyik a foreign key szerepét tölti be,akkor
			//a betoltve attributomba igen értéket ad meg egyébként nemet
			
			NodeList blist = doc.getElementsByTagName("Beosztas");
			
			for(int temp = 0; temp < blist.getLength(); temp++){
				Node node = blist.item(temp);
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element nElement = (Element) node;
					
					if(nElement.hasAttribute("B_D")) {
						nElement.setAttribute("betoltve", "igen");
					}else {
						nElement.setAttribute("betoltve", "nem");
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
