package domHD9DOJ1022;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomWriteHD9DOJ {
	
	public static void main(String argv[]) throws ParserConfigurationException,TransformerException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		//uj dokumentet hozz letre
		
		Element root = doc.createElementNS("DOMHD9DOJ","hallgatok");
		doc.appentChild(root);
		//létrehozzunk egy gyökérelemet és hozzáadjuk a dokumentumhoz - appendChild()
		
		// a gyökérhez 3 gyerekelemet fûzünk
		root.appendChild(creatUser(doc,"1","Peter","Nagy","Web Developer"));
		root.appendChild(creatUser(doc,"2","Piroska","Vigh","Java programozo"));
		root.appendChild(creatUser(doc,"3","Ferenc","Kiss","Associate professor"));
		
		//most következik az xml fájlba írás
		//transformert használj xml fájl létrehozzására
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		
	}
}