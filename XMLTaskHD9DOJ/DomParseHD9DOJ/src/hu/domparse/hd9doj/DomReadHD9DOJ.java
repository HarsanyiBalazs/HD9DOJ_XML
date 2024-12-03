package hu.domparse.hd9doj;

import java.io.File;
import java.io.IOException;
import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomReadHD9DOJ {
	public static void main(String argv[]) throws SAXException,IOException, ParserConfigurationException
    {
        File xmlFile = new File("XMLHD9DOJ.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        //a parse metodus elemzi az xml fájlt a document

        doc.getDocumentElement().normalize();
        //a dokumentum normalizálása segít a helyes eredmények elérésében
        
        PrintStream o = new PrintStream(new File("xmlDataHD9DOJ.txt"));
        PrintStream console = System.out;
        
        
        System.setOut(o);

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //megkapjuk a dok gyökérelemét
        System.setOut(console);
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        
        
        //dolgozo elemek
        NodeList nList= doc.getElementsByTagName("Dolgozo");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);

            

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("id");
                
                String foreignKey = elem.getAttribute("D_O");
                
                
                
                Node cimList = elem.getElementsByTagName("Cim").item(0);
                Element cim = (Element) cimList;
                Node node5 = cim.getElementsByTagName("Iranyito_szam").item(0);
                String irszam = node5.getTextContent();
               
                Node node6 = cim.getElementsByTagName("Varos").item(0);
                String varos = node6.getTextContent();
                
                Node node7 = cim.getElementsByTagName("Utca").item(0);
                String utca = node7.getTextContent();
                

                Node node1 = elem.getElementsByTagName("Fizetes").item(0);                             
                String fizetes = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Nev").item(0);
                String nev = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("Vegzettseg").item(0);
                String vegzettseg = node3.getTextContent();
                
                NodeList node4List = elem.getElementsByTagName("E-mail");
                
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("dolgozo id: %s%n", uid);
                System.out.printf("dolgozo foreign key: %s%n", foreignKey);
                System.out.printf("iranyito szam: %s%n", irszam);
                System.out.printf("varos: %s%n", varos);
                System.out.printf("utca: %s%n", utca);
                System.out.printf("fizetes: %s%n", fizetes);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("vegzettseg: %s%n", vegzettseg);
                
                for (int j=0; j<node4List.getLength();j++){
                	Node node4 = node4List.item(j);
                	String email=node4.getTextContent();
                	System.out.printf("email: %s%n", email);
                }
                
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("dolgozo id: %s%n", uid);
                System.out.printf("dolgozo foreign key: %s%n", foreignKey);
                System.out.printf("iranyito szam: %s%n", irszam);
                System.out.printf("varos: %s%n", varos);
                System.out.printf("utca: %s%n", utca);
                System.out.printf("fizetes: %s%n", fizetes);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("vegzettseg: %s%n", vegzettseg);
                
                for (int j=0; j<node4List.getLength();j++){
                	Node node4 = node4List.item(j);
                	String email=node4.getTextContent();
                	System.out.printf("email: %s%n", email);
                }
            }
        }
        
        
        
        //beosztas elemek
        nList= doc.getElementsByTagName("Beosztas");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("BID");
                
                String foreignKey = elem.getAttribute("B_D");                            

                Node node1 = elem.getElementsByTagName("Minimum_vegzettseg").item(0);                             
                String minVeg = node1.getTextContent();
                
                Node node2 = elem.getElementsByTagName("Beosztas_nev").item(0);
                String nev = node2.getTextContent();
                
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("beosztas id: %s%n", uid);
                System.out.printf("beosztas foreign key: %s%n", foreignKey);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("minimum vegzettseg: %s%n", minVeg);
                
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("beosztas id: %s%n", uid);
                System.out.printf("beosztas foreign key: %s%n", foreignKey);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("minimum vegzettseg: %s%n", minVeg);
                
            }
        }
        
        
      //eszkoz elemek
        nList= doc.getElementsByTagName("Eszkoz");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("EID");
                
                String foreignKey = elem.getAttribute("E_D");                            

                Node node1 = elem.getElementsByTagName("Ar").item(0);                             
                String ar = node1.getTextContent();
                
                Node node2 = elem.getElementsByTagName("Eszkoz_nev").item(0);
                String nev = node2.getTextContent();
                
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("eszkoz id: %s%n", uid);
                System.out.printf("eszkoz foreign key: %s%n", foreignKey);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("ar: %s%n", ar);
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("eszkoz id: %s%n", uid);
                System.out.printf("eszkoz foreign key: %s%n", foreignKey);
                System.out.printf("nev: %s%n", nev);
                System.out.printf("ar: %s%n", ar);
                
            }
        }
        
        
      //osztaly elemek
        nList= doc.getElementsByTagName("Osztaly");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);


            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("OID");
                                                                                       
                Node node = elem.getElementsByTagName("Osztaly_nev").item(0);
                String nev = node.getTextContent();
                
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("osztaly id: %s%n", uid);             
                System.out.printf("nev: %s%n", nev);
                
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("osztaly id: %s%n", uid);                
                System.out.printf("nev: %s%n", nev);

                
            }
        }
        
        
      //Osztaly-Epulet_beosztas elemek
        nList= doc.getElementsByTagName("Osztaly-Epulet_beosztas");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);


            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String foreignKey1 = elem.getAttribute("OE_O");
                
                String foreignKey2 = elem.getAttribute("OE_E");                            

                Node node1 = elem.getElementsByTagName("Mikortol").item(0);                             
                String mikortol = node1.getTextContent();
                               
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("Osztaly-Epulet_beosztas foreign key1: %s%n", foreignKey1);
                System.out.printf("Osztaly-Epulet_beosztas foreign key2: %s%n", foreignKey2);
                System.out.printf("mikortol: %s%n", mikortol);
                
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("Osztaly-Epulet_beosztas foreign key1: %s%n", foreignKey1);
                System.out.printf("Osztaly-Epulet_beosztas foreign key2: %s%n", foreignKey2);
                System.out.printf("mikortol: %s%n", mikortol);

                
            }
        }
        
        
      //epulet elemek
        nList= doc.getElementsByTagName("Epulet");
        

        for (int i=0; i<nList.getLength();i++){
            
            Node nNode = nList.item(i);


            if(nNode.getNodeType() == Node.ELEMENT_NODE){
            	

                Element elem = (Element) nNode;                

                String uid = elem.getAttribute("ÉID");                          

                Node node1 = elem.getElementsByTagName("Epulet_nev").item(0);                             
                String nev = node1.getTextContent();
                
                Node cimList = elem.getElementsByTagName("Cim").item(0);
                Element cim = (Element) cimList;
                Node node5 = cim.getElementsByTagName("Iranyito_szam").item(0);
                String irszam = node5.getTextContent();
               
                Node node6 = cim.getElementsByTagName("Varos").item(0);
                String varos = node6.getTextContent();
                
                Node node7 = cim.getElementsByTagName("Utca").item(0);
                String utca = node7.getTextContent();
                
                
                System.setOut(o);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("epulet id: %s%n", uid);
                System.out.printf("nev: %s%n", nev);              
                System.out.printf("iranyito szam: %s%n", irszam);
                System.out.printf("varos: %s%n", varos);
                System.out.printf("utca: %s%n", utca);
                
                System.setOut(console);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                System.out.printf("epulet id: %s%n", uid);
                System.out.printf("nev: %s%n", nev);              
                System.out.printf("iranyito szam: %s%n", irszam);
                System.out.printf("varos: %s%n", varos);
                System.out.printf("utca: %s%n", utca);
                
            }
        }
        
    }
}
