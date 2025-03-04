package src.com.webapp;

import src.com.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field[] fields = r.getClass().getDeclaredFields();
        fields[0].setAccessible(true);
        System.out.println(fields[0].getName());
        System.out.println(r);
        fields[0].set(r, "new");
        System.out.println(r);
        Method toStringMethod = r.getClass().getMethod("toString");
        String result = toStringMethod.invoke(r).toString();
        System.out.println("Result of toString() " + result);
    }

}
