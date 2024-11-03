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

public class DomWriteHD9DOJ2 {
	
	public static void main(String argv[]) throws ParserConfigurationException,TransformerException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		//uj dokumentet hozz letre
		
		Element root = doc.createElementNS("DOMHD9DOJ","HB_orarend");
		doc.appendChild(root);
		//létrehozzunk egy gyökérelemet és hozzáadjuk a dokumentumhoz - appendChild()
		
		// a gyökérhez 3 gyerekelemet fûzünk
		root.appendChild(createOra(doc,"1","elmelet","Adatkezelés XML-ben","Kedd","10","12","Inf103","Bednarik László","Programtervező informatikus"));
		root.appendChild(createOra(doc,"2","gyakorlat","Adatkezelés XML-ben","Kedd","12","14","Inf103","Bednarik László","Programtervező informatikus"));
		root.appendChild(createOra(doc,"3","gyakorlat","Mesterséges intelligencia alapok","Szerda","10","12","Ea10","Fazekas Levente Áron","Programtervező informatikus"));
		root.appendChild(createOra(doc,"4","elmelet","Mesterséges intelligencia alapok","Szerda","16","18","Ea32","Kunné Dr. Tamás Judit","Programtervező informatikus"));
		root.appendChild(createOra(doc,"5","elmelet","Játék prototípusok","Csütörtök","12","14","Inf114","Kiss Áron","Programtervező informatikus"));
		root.appendChild(createOra(doc,"6","gyakorlat","Játék prototípusok","Csütörtök","14","16","Inf114","Kiss Áron","Programtervező informatikus"));
		
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
		
		File myFile = new File("orarend1HD9DOJ.xml");
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

	private static Node createOra(Document doc, String id, String tipus, String targy, String nap, String tol, String ig, String helyszin, String oktato, String szak) {
		
		Element ora = doc.createElement("ora");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		ora.setAttribute("id", id);
		ora.setAttribute("tipus", tipus);
		ora.appendChild(createOraElement(doc, "targy",targy));
		ora.appendChild(createOraElement(doc, "helyszin",helyszin));
		
		ora.appendChild(createIdopontElement(doc,nap,tol,ig));
		
		ora.appendChild(createOraElement(doc, "oktato",oktato));
		ora.appendChild(createOraElement(doc, "szak",szak));
		
		
		return ora;
		
	}
	
	
	private static Node createIdopontElement(Document doc,String nap, String tol, String ig) {
		Element idopont = doc.createElement("idopont");
		
		idopont.appendChild(createOraElement(doc, "nap",nap));
		idopont.appendChild(createOraElement(doc, "tol",tol));
		idopont.appendChild(createOraElement(doc, "ig",ig));
		
		return idopont;
	}
	
	//az elem hozzáadódik a szülőhöz a appendChild() metodussal és szöveges csomópont jön létre
	
	private static Node createOraElement(Document doc,String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	
	
}
