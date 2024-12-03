package SaxHD9DOJ1203;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
 
import java.util.*;
import java.io.*;

public class SaxHD9DOJ {

	public static void main(String[] args) {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SaxHandler saxHandler = saxParser.parse("HD9DOJ_kurzusfelvetel.xml",);
		
		}catch (Exception ex) {
		}
	}
}
