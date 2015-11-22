package tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
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
		ArrayList<Notification> li = new ArrayList<Notification>();
		li.add(n1);
		li.add(n2);
				
		String gson = new Gson().toJson(li);
		//escribe a un archivo
		try{
			File myFile = new File("test.txt");
			myFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
			myOutWriter.append(gson);
			myOutWriter.close();
			fOut.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		//lee desde el archivo
		try{
		File myFile = new File("test.txt");
        FileInputStream fIn = new FileInputStream(myFile);
        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
        String aDataRow = "";
        String aBuffer = ""; //Holds the text
        while ((aDataRow = myReader.readLine()) != null) 
        {
            aBuffer += aDataRow ;
        }
        myReader.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
