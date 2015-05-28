package stats;

import java.io.FileReader;

import net.minidev.json.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

public class WebCalls {
	private final String date;
	
	public WebCalls(String date){
		this.date = date;
	}
	
	JSONArray rows;
	JSONObject jsonObj;
	public String getStats() {
		String returnString = "{\"games\": [";
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("lib/stats.json"));
			jsonObj = new JSONObject(obj.toString());
			jsonObj = jsonObj.getJSONObject("stats");
			rows = jsonObj.getJSONArray("game");
			
			for(int i=0; i < rows.length(); i++) {
	            JSONObject row = rows.getJSONObject(i);
	            if (row.getString("date").equals(this.date)) {
	            	if (returnString.equals("{\"games\": [")){
	            		returnString = returnString + row.toString();
	            	}
	            	else{
	            		returnString = returnString + "," + row.toString();
	            	}
	            }
	        }
			if (returnString.equals("{\"games\": [")){
				return "{\"team\":\"none\"}";
			}
			else{
				returnString = returnString + "]}";
				return returnString;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public float addValue(float value) {
		return (value + 10);
	}
}
