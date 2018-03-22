package webapp.utils;

public class LazySingleton {
    private static volatile LazySingleton INSTANCE;

    double sin=Math.sin(13.);

    public static LazySingleton getInstance() {
        return INSTANCE;
    }

    private LazySingleton() {
        if (INSTANCE==null){
            synchronized (LazySingleton.class) {
                if (INSTANCE==null){
                    INSTANCE = new LazySingleton();
                }
            }
        }
    }
}
