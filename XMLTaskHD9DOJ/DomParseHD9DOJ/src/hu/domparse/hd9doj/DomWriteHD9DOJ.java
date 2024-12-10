package hu.domparse.hd9doj;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomWriteHD9DOJ {
	
	public static void main(String argv[]) throws ParserConfigurationException,TransformerException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		//uj dokumentet hozz letre
		
		Element root = doc.createElementNS("http://www.w3.org/2001/XMLSchema-instance","Vallalat");
		doc.appendChild(root);
		//létrehozzunk egy gyökérelemet és hozzáadjuk a dokumentumhoz - appendChild()
		
		// a gyökérhez gyerekelemeket fûzünk
		String[] emails1={"lakott@gmail.com","lakO@ceges.com","lakotthoni@freemail.com"};
		String[] emails2={"tthB@freemail.hu","toThB@ceges.com","tthB@yahoo.hu"};
		String[] emails3={"ttkr@freemail.hu","toKrB@ceges.com","ttKrB@yahoo.hu"};
		root.appendChild(createDolgozo(doc,"d1","o1","300000","Lakatos Otto","3744","Kazincbarcika","Tancsics Utca 22","Erettsegi",emails1));
		root.appendChild(createDolgozo(doc,"d2","o2","2000000","Toth Balazs","2252","Miskolc","Hosok ut 1","Diploma",emails2));
		root.appendChild(createDolgozo(doc,"d3","o3","120000","Toth Krisztina","2252","Miskolc","Hosok ut 1","Diploma",emails3));
		
		
		root.appendChild(createBeosztas(doc,"b1","d1","Erettsegi","Leltar Menedzser"));
		root.appendChild(createBeosztas(doc,"b2","d2","Diploma","Emberi eroforrások Menedzser"));
		root.appendChild(createBeosztas(doc,"b3","d3","Diploma","Programozo"));
		
		
		root.appendChild(createEszkoz(doc,"e1","d1","Telefon","100000"));
		root.appendChild(createEszkoz(doc,"e2","d2","Laptop","300000"));
		root.appendChild(createEszkoz(doc,"e3","d3","Laptop","300000"));
		
		
		root.appendChild(createOsztaly(doc,"o1","Leltar"));
		root.appendChild(createOsztaly(doc,"o2","Emberi eroforrasok"));
		root.appendChild(createOsztaly(doc,"o3","Informatika"));
		
		
		root.appendChild(createOEbeoszt(doc,"o1","ep1","2001-12-31"));
		root.appendChild(createOEbeoszt(doc,"o2","ep2","1999-04-12"));
		root.appendChild(createOEbeoszt(doc,"o3","ep3","2000-08-10"));
		
		
		root.appendChild(createEpulet(doc,"ep1","Raktar 1","3704","Berente","Berente ut 5"));
		root.appendChild(createEpulet(doc,"ep2","Fo epulet","3744","Kazincbarcika","Kazinc ut 5"));
		root.appendChild(createEpulet(doc,"ep3","Informatika epulet","3744","Kazincbarcika","Petofi ut 2"));
		
		
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
		
		File myFile = new File("XMLHD9DOJ1.xml");
		//írjunk egy konzolba és egy fájlba
		//StreamResult transzformációs eredménye
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		//a transform metódus átalakítja a source objektumot a streamresult objektummá
		//az xml-forrásokat a stream eredményekhez írjuk
		transf.transform(source,console);
		transf.transform(source,file);
		
	}
	
	// a create-() metódusokban új elemet hozunk létre amihez megadjuk a attributomokat és még tovább hozzáfűzünk elemeket

	//a dolgozo elem létrehozzása 
	private static Node createDolgozo(Document doc, String id, String foreignKey, String fizetes, String nev, String irszam, String varos, String utca, String vegzettseg, String[] emails) {
		
		Element dolgozo = doc.createElement("Dolgozo");
		
		
		dolgozo.setAttribute("id", id);
		dolgozo.setAttribute("D_O", foreignKey);
		dolgozo.appendChild(createBaseElement(doc, "Fizetes",fizetes));
		dolgozo.appendChild(createBaseElement(doc, "Nev",nev));
		dolgozo.appendChild(createCim(doc,irszam,varos,utca));
		dolgozo.appendChild(createBaseElement(doc, "Vegzettseg",vegzettseg));
		
		for(int i=0;i<emails.length;i++) {
			dolgozo.appendChild(createBaseElement(doc, "E-mail",emails[i]));
		}
		
		return dolgozo;
		
	}
	
	//a beosztas elem létrehozzása 
	private static Node createBeosztas(Document doc, String id, String foreignKey, String minVeg, String nev) {
		
		Element beosztas = doc.createElement("Beosztas");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		beosztas.setAttribute("BID", id);
		beosztas.setAttribute("B_D", foreignKey);
		beosztas.appendChild(createBaseElement(doc, "Minimum_vegzettseg",minVeg));
		beosztas.appendChild(createBaseElement(doc, "Beosztas_nev",nev));
		
		
		return beosztas;
		
	}
	
	//az eszkoz elem létrehozzása
	private static Node createEszkoz(Document doc, String id, String foreignKey, String nev, String ar) {
		
		Element eszkoz = doc.createElement("Eszkoz");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		eszkoz.setAttribute("EID", id);
		eszkoz.setAttribute("E_D", foreignKey);
		eszkoz.appendChild(createBaseElement(doc, "Eszkoz_nev",nev));
		eszkoz.appendChild(createBaseElement(doc, "Ar",ar));
		
		
		return eszkoz;
		
	}
	
	//az osztály elem létrehozzása
	private static Node createOsztaly(Document doc, String id, String nev) {
		
		Element osztaly = doc.createElement("Osztaly");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		osztaly.setAttribute("OID", id);
		osztaly.appendChild(createBaseElement(doc, "Osztaly_nev",nev));
		
		
		
		return osztaly;
		
	}
	
	//az osztályt és épületet összekötő elem létrehozzása
	private static Node createOEbeoszt(Document doc, String foreignKey1, String foreignKey2, String date) {
		
		Element oeBeoszt = doc.createElement("Osztaly-Epulet_beosztas");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		oeBeoszt.setAttribute("OE_O", foreignKey1);
		oeBeoszt.setAttribute("OE_E", foreignKey2);
		oeBeoszt.appendChild(createBaseElement(doc, "Mikortol",date));
		
		
		
		return oeBeoszt;
		
	}
	
	//az épület elem létrehozzása
	private static Node createEpulet(Document doc, String id, String nev, String irszam, String varos, String utca) {
		
		Element epulet = doc.createElement("Epulet");
		
		//az setAttribute() metódussal beálítjuk az elem attribútomát
		epulet.setAttribute("EPID", id);
		epulet.appendChild(createBaseElement(doc, "Epulet_nev",nev));
		epulet.appendChild(createCim(doc,irszam,varos,utca));
		
		return epulet;
		
	}
	
	//a cim elem létrehozzása
	private static Node createCim(Document doc,String irszam, String varos, String utca) {
		Element cim = doc.createElement("Cim");
		
		cim.appendChild(createBaseElement(doc, "Iranyito_szam",irszam));
		cim.appendChild(createBaseElement(doc, "Varos",varos));
		cim.appendChild(createBaseElement(doc, "Utca",utca));
		
		return cim;
	}
	
	//az elem hozzáadódik a szülőhöz a appendChild() metodussal és szöveges csomópont jön létre
	//olyan elemet hozz létre amiben csak szöveg van és nem lesz hozzákötve elem
	private static Node createBaseElement(Document doc,String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}

}
