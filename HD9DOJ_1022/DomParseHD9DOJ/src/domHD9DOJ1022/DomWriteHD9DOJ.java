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
		doc.appendChild(root);
		//létrehozzunk egy gyökérelemet és hozzáadjuk a dokumentumhoz - appendChild()
		
		// a gyökérhez 3 gyerekelemet fûzünk
		root.appendChild(createUser(doc,"1","Peter","Nagy","Web Developer"));
		root.appendChild(createUser(doc,"2","Piroska","Vigh","Java programozo"));
		root.appendChild(createUser(doc,"3","Ferenc","Kiss","Associate professor"));
		
		//most következik az xml fájlba írás
		//transformert használj xml fájl létrehozzására
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		//beállítjuk a dokumentum kódolását és behúzását
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "2");
		
		//egy bemeneti forrás létrehozzása egy dom csomóponttal. ez DOMsource tartalmazza a DOM fát
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("hallgatok5.xml");
		//írjunk egy konzolba és egy fájlba
		//StreamResult transzformációs eredménye
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		//a transform metódus átalakítja a source objektumot a streamresult objektummá
		//az xml-forrásokat a stream eredményekhez írjuk
		transf.transform(source,console);
		transf.transform(source,file);
		
	}
	
	// a creatuser() metódusban új felhasználói elemet hozunk létre - createElement()

	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		
		Element user = doc.createElement("hallgato");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "keresztnev",firstName));
		user.appendChild(createUserElement(doc, "vezeteknev",lastName));
		user.appendChild(createUserElement(doc, "foglalkozas",profession));
		
		return user;
		
	}
	
	//az elem hozzáadódik a szülőhöz a appendChild() metodussal és szöveges csomópont jön létre
	
	private static Node createUserElement(Document doc,String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	
}