package webapp;


import webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println(field);
        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        try {
            // r.getClass().getMethod("toString", null).invoke(r.getClass().newInstance(), null)
            Class myClass = r.getClass();
            Object  objectNew = myClass.getMethod("toString", null).invoke(r, null);
            System.out.println( (String)objectNew);
        } catch (Exception e) {

            System.out.println("Ooops...");
        }

    }
}
