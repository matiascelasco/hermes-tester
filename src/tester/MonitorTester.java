package tester;

import generator.MockGenerator;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import serializers.DateSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Notification;

public class MonitorTester {
	
    public static void main(String[] args) throws IOException {
    	int amount = new Random().nextInt(100 - 40) + 40;
		List<Notification> li = MockGenerator.createMockInstances(Notification.class, amount);

		Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateSerializer())
			.create();
		
		String jsonString = gson.toJson(li);
		
		File myFile = new File("test.txt");
		myFile.createNewFile();
		FileOutputStream fOut = new FileOutputStream(myFile);
		OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
		myOutWriter.append(jsonString);
		myOutWriter.close();
		fOut.close();
	}

}
