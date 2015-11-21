import java.util.List;

import model.Notification;
import generator.MockGenerator;


public class Sarasa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Notification> notifications = MockGenerator.createMockInstances(Notification.class, 3);
		for (Notification n: notifications){
			System.out.println(n);
		}
	}

}
