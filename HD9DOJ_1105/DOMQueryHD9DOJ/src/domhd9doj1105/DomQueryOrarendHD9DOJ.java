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

public class DomQueryOrarendHD9DOJ {

	public static void main(String arv[]){
		try{
			File inputFile = new File("HD9DOJ_orarend.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			
			doc.getDocumentElement().normalize();
			
			System.out.print("gyökér elem: ");
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("ora");
			System.out.print("--------");
			
			for(int temp = 0 ;temp<nList.getLength();temp++){
				Node nNode = nList.item(temp);
				System.out.println("\nAktuális elem: ");
				System.out.print(nNode.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					
					System.out.println(eElement.getAttribute("Ora"));
					
					NodeList targyNevLista = eElement.getElementsByTagName("targy");
					
					for (int count = 0;count < targyNevLista.getLength(); count++) {
						Node node1 = targyNevLista.item(count);
						
						if(node1.getNodeType() == node1.ELEMENT_NODE) {
						
							Element targy = (Element) node1;
							System.out.print("targy neve: ");
							System.out.println(targy.getTextContent());
						}
					}
					
				}
			}
			
			System.out.println("--------");
			
			for(int temp = 0 ;temp<nList.getLength();temp++){
				Node nNode = nList.item(temp);
				System.out.println("\nAktuális elem: ");
				System.out.print(nNode.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					
					System.out.println(eElement.getAttribute("Ora"));
					
					NodeList oktatoNevLista = eElement.getElementsByTagName("oktato");
					
					for (int count = 0;count < oktatoNevLista.getLength(); count++) {
						Node node1 = oktatoNevLista.item(count);
						
						if(node1.getNodeType() == node1.ELEMENT_NODE) {
						
							Element oktato = (Element) node1;
							System.out.print("oktato neve: ");
							System.out.println(oktato.getTextContent());
						}
					}
					
				}
			}
			
			System.out.println("--------");
			
			Node ora = doc.getElementsByTagName("ora").item(0);
			

                Element elem = (Element) ora;                

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

               /* System.out.printf("ora id: %s%n", uid);
                System.out.printf("ora tipus: %s%n", tipus);
                System.out.printf("targy: %s%n", targy);
                System.out.printf("helyszin: %s%n", helyszin);
                System.out.printf("oktato: %s%n", oktato);
                System.out.printf("szak: %s%n", szak);
                System.out.printf("nap: %s%n", nap);
                System.out.printf("tol: %s%n", tol);
                System.out.printf("ig: %s%n", ig);*/
            
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc2 = builder.newDocument();
			Element root = doc2.createElementNS("DOMHD9DOJ","ora");
			doc2.appendChild(root);
			
			
			
			root.setAttribute("id", uid);
			root.setAttribute("tipus", tipus);
			
			
			
			Element targyNode = doc2.createElement("targy");
			targyNode.appendChild(doc2.createTextNode(targy));
			
			Element helyszinNode = doc2.createElement("helyszin");
			helyszinNode.appendChild(doc2.createTextNode(helyszin));
			
			Element oktatoNode = doc2.createElement("oktato");
			oktatoNode.appendChild(doc2.createTextNode(oktato));
			
			Element szakNode = doc2.createElement("szak");
			szakNode.appendChild(doc2.createTextNode(szak));
			
			
			Element napNode = doc2.createElement("nap");
			napNode.appendChild(doc2.createTextNode(nap));
			
			Element tolNode = doc2.createElement("tol");
			tolNode.appendChild(doc2.createTextNode(tol));
			
			Element igNode = doc2.createElement("ig");
			igNode.appendChild(doc2.createTextNode(ig));
			
			
			
			Element idopontNode = doc2.createElement("idopont");
			
			idopontNode.appendChild(napNode);
			idopontNode.appendChild(tolNode);
			idopontNode.appendChild(igNode);
			
			
			
			root.appendChild(targyNode);
			root.appendChild(helyszinNode);
			root.appendChild(oktatoNode);
			root.appendChild(szakNode);
			root.appendChild(idopontNode);
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transf = transformerFactory.newTransformer();
			
			transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transf.setOutputProperty(OutputKeys.INDENT, "yes");
			transf.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc2);
			
			StreamResult consoleResult = new StreamResult(System.out);
			transf.transform(source, consoleResult);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
