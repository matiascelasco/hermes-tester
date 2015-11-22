package generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import annotations.Mock;
import annotations.MockStringAttribute;
import annotations.MockTodayAttribute;

public class MockGenerator {
	private static Random random = new Random();
	public static <T> List<T> createMockInstances(Class<T> objClass, int amount) {
		if (!objClass.isAnnotationPresent(Mock.class)){
			throw new RuntimeException(String.format("'%s' class is not annotated with Mock", objClass.getName()));
		}
		
		List<T> objects = new ArrayList<T>();
		try {
			Constructor<T> constructor = objClass.getConstructor();
			for (int i = 0; i < amount; i++){
				T obj = (T) constructor.newInstance();
				for (Field field: objClass.getFields()){
					if (field.isAnnotationPresent(MockStringAttribute.class)){
						MockStringAttribute annotation = field.getAnnotationsByType(MockStringAttribute.class)[0];
						field.set(obj, annotation.value()[random.nextInt(annotation.value().length)]);
					}
					if (field.isAnnotationPresent(MockTodayAttribute.class)){
						field.set(obj, new GregorianCalendar().getTime());
					}
				}
				objects.add(obj);
			}
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return objects;
	}
}
