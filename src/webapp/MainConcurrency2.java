package webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainConcurrency2 {

    private static final int THREAD_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK =new Object();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());
        Thread thread0=new Thread(){
            @Override
            public void run(){
                System.out.println(getName()+","+ getState());
            }
        };
            thread0.start();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+","+ Thread.currentThread().getState());
            }
        });

        System.out.println(thread0.getState());
        System.out.println(thread1.getState());
        final MainConcurrency2 mainConcurrency= new MainConcurrency2();
        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread=new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                       mainConcurrency.inc();
                    }
                }
            });



            thread.start();
            threads.add(thread);

            threads.forEach(new Consumer<Thread>() {
                @Override
                public void accept(Thread t) {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }



        //Thread.sleep(500);
        System.out.println(counter);
    }
    private  void inc(){
       /* double a= Math.sin(13.);
        synchronized (this){
            System.out.println(this);
            counter++;
        }*/

        counter++;
    }
}
