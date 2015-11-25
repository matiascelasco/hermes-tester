package tester;

import generator.MockGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
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
		ArrayList<Notification> innerBuffer = new ArrayList<Notification>();
		
		int numSeries = 8;
		System.out.format("\n%d series of random notifications will be generated:\n", numSeries);
		for (int i = numSeries; i > 0; i--){
			
			System.out.println();
			
			// generate notifications
			int amount = random.nextInt(10 - 5) + 5;  // random between 5 and 10
			System.out.format("Generating %d random notifications... ", amount);
			List<Notification> list = MockGenerator.createMockInstances(Notification.class, amount);
			innerBuffer.addAll(list);
			System.out.println("Done");
			
			// serialize
			System.out.print("Serializing... ");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new DateSerializer())
				.create();	
			String jsonString = gson.toJson(innerBuffer);
			System.out.println("Done");
	    	
			// sent to server
			System.out.format("Sending JSON message to monitor at %s... ", postUrl);
			post.setEntity(new StringEntity(jsonString));
			post.setHeader("Content-type", "application/json");
			//catch possible exception
			try{
				HttpResponse response = client.execute(post);
				// read response and print to console
			  	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				System.out.println("Response from monitor:");
				String line;
				while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }
				System.out.println("Done");				
				innerBuffer.clear();
				System.out.println("Size of buffered notification = "+String.valueOf(innerBuffer.size()));
			}catch(ConnectException e){
				System.out.println("there was an exception in the connection with the server");
				System.out.println("Size of buffered notification = "+String.valueOf(innerBuffer.size()));
			}
			catch(SocketException e){
				System.out.println("the connection was reseted");
				System.out.println("Size of buffered notification = "+String.valueOf(innerBuffer.size()));
			}
			// wait
			System.out.println("Waiting 5 seconds before doing it again...");
			Thread.sleep(5000);
			
		} //end for
	}

}
