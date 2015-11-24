package tester;

import generator.MockGenerator;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import serializers.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Notification;

public class MonitorTester {
	
    public static void main(String[] args) {
    	try{
    		String postUrl="http://localhost:8000/load-notifications";
    		HttpClient client = HttpClientBuilder.create().build();
    		HttpPost post = new HttpPost(postUrl);
    		//int amount = new Random().nextInt(100 - 40) + 40;
    		for(int i=3; i<8; i++){
    			List<Notification> li = MockGenerator.createMockInstances(Notification.class, i/*amount*/);

    			Gson gson = new GsonBuilder()
    					.registerTypeAdapter(Date.class, new DateSerializer())
    					.create();
		
    			String jsonString = gson.toJson(li);			
		    		
    			StringEntity postingString =new StringEntity(jsonString);
    			post.setEntity(postingString);
    			System.out.println("Message to sent: "+postingString);
    			//post.setHeader("Content-type", "application/json");			
    			HttpResponse response = client.execute(post);
		
    			System.out.println(response.toString());
    			Thread.sleep(5000); 
    		}
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    	}
	}

}
