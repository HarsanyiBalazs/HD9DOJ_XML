package hd9doj1112;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriteHD9DOJ {

	public static void main(String[] args){
		
		JSONArray bl = new JSONArray();
		bl.add(createLesson("Adatkezelés XML-ben","Kedd","10","12","Inf103","Bednarik László","Programtervezõ informatikus"));
		bl.add(createLesson("Adatkezelés XML-ben","Kedd","12","14","Inf103","Bednarik László","Programtervezõ informatikus"));
		bl.add(createLesson("Mesterséges intelligencia alapok","Szerda","10","12","Ea10","Fazekas Levente Áron","Programtervezõ informatikus"));
		bl.add(createLesson("Mesterséges intelligencia alapok","Szerda","16","18","Ea32","Kunné Dr. Tamás Judit","Programtervezõ informatikus"));
		bl.add(createLesson("Játék prototípusok","Csütörtök","10","12","Inf114","Kiss Áron","Programtervezõ informatikus"));
		bl.add(createLesson("Játék prototípusok","Csütörtök","12","14","Inf114","Kiss Áron","Programtervezõ informatikus"));
		
		JSONObject root = new JSONObject();
		root.put("ora", bl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("HB_orarend", root);
		
		fileWrite(jsonObject,"orarendHB.json");
		consoleWrite(jsonObject);
	}
	
	private static void fileWrite(JSONObject jsonObject, String fileName){
		try(FileWriter writer=  new FileWriter(fileName)){
			writer.write(indentJson(jsonObject.toJSONString()));
		}catch(Exception E){
			
		}
	}
	
	private static void consoleWrite(JSONObject jsonObject){
		System.out.println("JSON dokumentum tartalma:\n");
		JSONObject root = (JSONObject) jsonObject.get("HB_orarend");
		JSONArray bl = (JSONArray) root.get("ora");
		
		for(int i=0;i<bl.size();i++){
			JSONObject lesson= (JSONObject) bl.get(i);
			JSONObject time= (JSONObject) lesson.get("idopont");
			System.out.println("Targy: " +lesson.get("targy"));
			System.out.println("Idopont: " +time.get("nap")+ " " + time.get("tol") + " " + time.get("ig"));
			System.out.println("Helyszin: " +lesson.get("helyszin"));
			System.out.println("Oktato: " +lesson.get("oktato"));
			System.out.println("Szak: " +lesson.get("szak")+"\n"); 
		}
		
	}
	
	private static String indentJson(String json){
		String out ="";
		int indent = 0;
		for(int i=0;i<json.length()-1;i++){
			out += json.charAt(i);
			if (json.charAt(i)==','){
				out += "\n" + " ".repeat(indent>0 ? indent : 0);
			}else if (json.charAt(i)== '{' | json.charAt(i)== '['){
				indent++;
				out += "\n" + " ".repeat(indent>0 ? indent : 0);
			}else if((json.charAt(i+1)== '}' || json.charAt(i+1)== ']')){
				indent--;
				out += "\n" + " ".repeat(indent>0 ? indent : 0);
			}
		}
		out+=json.charAt(json.length()-1);
		return out;
	}
	
	private static JSONObject createLesson(String subject,String day,String from,String to,String place,String teacher, String major){
		JSONObject lesson = new JSONObject();
		JSONObject time = new JSONObject();
		time.put("nap", day);
		time.put("tol", from);
		time.put("ig", to);
		lesson.put("targy", subject);
		lesson.put("idopont", time);
		lesson.put("helyszin", place);
		lesson.put("oktato", teacher);
		lesson.put("szak", major);
		
		return lesson;
	}
}
