package tester;

import generator.MockGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import serializers.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Notification;

public class MonitorTester {
	
    public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		
    	String postUrl = "http://localhost:8000/load-notifications";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(postUrl);
		Random random = new Random();
		
		int numSeries = 5;
		System.out.format("\n%d series of random notifications will be generated:\n", numSeries);
		for (int i = numSeries; i > 0; i--){
			
			System.out.println();
			
			// generate notifications
			int amount = random.nextInt(20 - 5) + 5;  // random between 5 and 20
			System.out.format("Generating %d random notifications... ", amount);
			List<Notification> li = MockGenerator.createMockInstances(Notification.class, amount);
			System.out.println("Done");
			
			// serialize
			System.out.print("Serializing... ");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new DateSerializer())
				.create();	
			String jsonString = gson.toJson(li);
			System.out.println("Done");
	    	
			// sent to server
			System.out.format("Sending JSON message to monitor at %s... ", postUrl);
			post.setEntity(new StringEntity(jsonString));
			post.setHeader("Content-type", "application/json");			
			HttpResponse response = client.execute(post);
			System.out.println("Done");
	
			// read response and print to console
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()
			));
			System.out.println("Response from monitor:");
			String line;
			while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
			
			// wait
			if (numSeries > 1){
				System.out.println("Waiting 5 seconds before doing it again...");
				Thread.sleep(5000);
			}
		}
	}

}
