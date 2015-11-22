package tester;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Notification;

public class MonitorTester {

	public static void main(String[] args) {
		//creo las notificaciones
		Notification n1,n2;
		
		n1 = new Notification();
		n2 = new Notification();
		
		n1.setCategory("Emociones");
		n1.setContent("Alegre");
		n1.setContext("Pista");
		n1.setKid("Dick Grayson");
		n1.setSent(new Date());
		
		n2.setCategory("Emociones");
		n2.setContent("Alegre");
		n2.setContext("Pista");
		n2.setKid("Jimmy Olsen");
		n2.setSent(new Date());		
		
		//creo una lista de notificaciones
		List li = new ArrayList<Notification>();
		li.add(n1);
		li.add(n2);
		
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
