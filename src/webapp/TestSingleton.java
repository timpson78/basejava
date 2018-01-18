package webapp;


import webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }


    public static void main(String[] args){


        System.out.println(TestSingleton.getInstance().toString());

    for (SectionType type:SectionType.values()) {
            System.out.println(type.getTitle());
         }

    }



}
