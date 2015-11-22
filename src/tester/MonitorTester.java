package tester;

import generator.MockGenerator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import annotations.Mock;

import com.google.gson.JsonArray;


import model.Notification;

public class MonitorTester {

	public static void main(String[] args) throws Exception {
		List li = new ArrayList<Notification>();
        for(Notification n: MockGenerator.createMockInstances(Notification.class, 3)){
        	li.add(n);
        }
		
		JsonArray jsonArray = new JsonArray();
		//JsonElement jel = Json
		
		//jsonArray.add(new JsonObject(n1));
		
								
		System.out.println(jsonArray);
		
		/*for (int i = 0, size = jsonArray.length(); i < size; i++){
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			System.out.println(objectInArray);
		}*/
		
		
	}

}
