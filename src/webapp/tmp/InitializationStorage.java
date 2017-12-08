package webapp.tmp;

public class InitializationStorage {

    static int initialization(String str){
        System.out.println(str);
        return 0;
    }



    static class A {

        int i0=initialization("i0");
        A() { int i2=initialization("i2");};


        {int i1=initialization("i1");}

        static {int i7=initialization("i7");}
    }
    static class B extends A {
        int i4=initialization("i4");
        B(){int i5=initialization("i5");}
    }


    public static void main(String[] args)  {
        new B();
    }
}
