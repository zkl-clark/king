package ThreadDemo.MyBook.Capatuer2;

import java.io.Serializable;

public class ThreadTest extends Thread {
    public static int count=0;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count++;
           if (i==9999)
               System.out.println(Thread.currentThread().getName()+"  运行结束了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new ThreadTest();
        t1.setName("线程一");
        Thread t2=new ThreadTest();
        t2.setName("线程二");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
