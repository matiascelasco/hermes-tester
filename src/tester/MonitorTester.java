package tester;

import generator.MockGenerator;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import model.Notification;

public class MonitorTester {

    public static void main(String[] args) throws IOException {
		List<Notification> li = MockGenerator.createMockInstances(Notification.class, 40);

		String gson = new Gson().toJson(li);
		//TODO: no esta serializando bien la fecha (posible solución: http://www.studytrails.com/java/json/java-google-json-custom-serializer-deserializer.jsp)		
		
		File myFile = new File("test.txt");
		myFile.createNewFile();
		FileOutputStream fOut = new FileOutputStream(myFile);
		OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
		myOutWriter.append(gson);
		myOutWriter.close();
		fOut.close();
	}

}
