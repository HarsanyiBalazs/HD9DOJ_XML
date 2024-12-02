package hu.domparse.hd9doj;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryHD9DOJ {
	
	public static void main(String arv[]){
		try{
			File inputFile = new File("XMLHD9DOJ.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			//a parse metodus elemzi az xml fájlt a document
			
			doc.getDocumentElement().normalize();
			//a dokumentum normalizálása segít a helyes eredmények elérésében
			
			System.out.print("gyökér elem: ");
			//megkapjuk a dok gyökérelemét
			System.out.println(doc.getDocumentElement().getNodeName());
			
			
			System.out.print("--------");
			System.out.print("Összes dolgozó emailjei");
			//a dolgozok listájában lekéri az emailek listáját majd kiírja a lista tartalmát
			NodeList nList = doc.getElementsByTagName("Dolgozo");
			
			
			for(int temp = 0 ;temp<nList.getLength();temp++){
				Node nNode = nList.item(temp);
				
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					
					System.out.println();
					
					NodeList emailLista = eElement.getElementsByTagName("E-mail");
					
					for (int count = 0;count < emailLista.getLength(); count++) {
						Node node1 = emailLista.item(count);
						
						if(node1.getNodeType() == node1.ELEMENT_NODE) {
						
							Element email = (Element) node1;
							System.out.print("email: ");
							System.out.println(email.getTextContent());
						}
					}
					
				}
			}
			
			System.out.print("--------");
			
			System.out.println("Összes dolgozó neve,akinek 100000-nél többe kerül a céges készüléke ");
			//az eszökzök listájában lekéri az összes ar listáját majd ha az nagyobb,mint 100000
			//akkor megnézi az idegen kulcsa melyik Dolgozó fő kulcsával egyez meg majd kiírja a dolgozó nevét
			NodeList dolgozoList = doc.getElementsByTagName("Dolgozo");
			NodeList eszkozList = doc.getElementsByTagName("Eszkoz");
			
			for(int temp = 0 ;temp<eszkozList.getLength();temp++){
				Node eszkozNode = eszkozList.item(temp);
				
				if (eszkozNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) eszkozNode;
					
					
					Node ar =eElement.getElementsByTagName("Ar").item(0);
					int arValue = Integer.valueOf(ar.getTextContent());
					
					if(arValue >100000) {
							
						NamedNodeMap attrEszkoz = eszkozNode.getAttributes();
						Node nodeAttrEszkoz = attrEszkoz.getNamedItem("E_D");
						String foreignKey = nodeAttrEszkoz.getTextContent();
						
						for(int temp2 = 0 ;temp2<dolgozoList.getLength();temp2++){
							Node dolgozoNode = dolgozoList.item(temp2);
							if (eszkozNode.getNodeType() == Node.ELEMENT_NODE){
								Element dElement = (Element) dolgozoNode;
								
								String primaryKey = dElement.getAttribute("id");
								
								
								if(primaryKey.equals(foreignKey)) {
									
									Node nodeNev = dElement.getElementsByTagName("Nev").item(0);
									System.out.println("Nev: "+ nodeNev.getTextContent() ) ;					                									
									
								}
								
								
							}
							
						} 
						
					}
					
				}
			}
			
			System.out.print("--------");
			System.out.print("Települések ahol az épületek találhatóak");
			//a épületek listájában lekéri a városok listáját majd kiírja a lista tartalmát
			NodeList epuletList = doc.getElementsByTagName("Epulet");
			
			
			for(int temp = 0 ;temp<epuletList.getLength();temp++){
				Node nNode = epuletList.item(temp);
				
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					
					System.out.println();
					
					NodeList cimLista = eElement.getElementsByTagName("Cim");
					
					for (int count = 0;count < cimLista.getLength(); count++) {
						Node node1 = cimLista.item(count);
						
						if(node1.getNodeType() == node1.ELEMENT_NODE) {
						
							Element cElement = (Element) node1;
							
							NodeList varosLista = cElement.getElementsByTagName("Varos");
							
							for (int i = 0;i < varosLista.getLength(); i++) {
								Node node2 = varosLista.item(i);
								
								if(node2.getNodeType() == node2.ELEMENT_NODE) {
								
									Element varos = (Element) node2;
									System.out.print("varos: ");
									System.out.println(varos.getTextContent());
								}
							}
							
						}
					}
					
				}
			}
			
			System.out.print("--------");
			System.out.print("Az osztályok nevei és id-jaik");
			//a osztályok listájában lekéri a osztályok neveinek listáját majd kiírja a lista tartalmát és az adott elem id attributomát
			NodeList oList = doc.getElementsByTagName("Osztaly");
			
			
			for(int temp = 0 ;temp<oList.getLength();temp++){
				Node nNode = oList.item(temp);
				
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element oElement = (Element) nNode;
					
					System.out.println();
					
					NodeList osztalyLista = oElement.getElementsByTagName("Osztaly_nev");
					
					for (int count = 0;count < osztalyLista.getLength(); count++) {
						Node node1 = osztalyLista.item(count);
						
						if(node1.getNodeType() == node1.ELEMENT_NODE) {
						
							Element osztaly = (Element) node1;
							System.out.print("osztaly: ");
							System.out.println(osztaly.getTextContent());
						}
					}
					
					String id = oElement.getAttribute("OID");
					System.out.print("id: ");
					System.out.println(id);
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
