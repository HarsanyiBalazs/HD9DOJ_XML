package hd9doj1112;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class JSONReadHD9DOJ {
	public static void main(String[] args){
		
		try(FileReader reader = new FileReader("orarendHD9DOJ.json")){
			//Parse
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			
			//root majd óra lista lekérése
			JSONObject root = (JSONObject) jsonObject.get("HB_orarend");
			JSONArray bl = (JSONArray) root.get("ora");
			
			//óra adatok kiírása
			System.out.println("orarend: progterv 2024 \n");
			
			for(int i = 0; i<bl.size();i++){
				JSONObject lesson= (JSONObject) bl.get(i);
				JSONObject time= (JSONObject) lesson.get("idopont");
				System.out.println("Targy: " +lesson.get("targy"));
				System.out.println("Idopont: " +time.get("nap")+ " " + time.get("tol") + " " + time.get("ig"));
				System.out.println("Helyszin: " +lesson.get("helyszin"));
				System.out.println("Oktato: " +lesson.get("oktato"));
				System.out.println("Szak: " +lesson.get("szak")+"\n");
			}
		}catch(Exception e){
			
		}
	}

}
