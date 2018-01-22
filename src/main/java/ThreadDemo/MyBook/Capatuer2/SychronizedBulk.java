package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 */
public class SychronizedBulk implements Runnable{
    static volatile int account=0;
    public static String string="big clock";
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread ( new SychronizedBulk ());
        Thread t2=new Thread ( new SychronizedBulk ());
        Thread t3=new Thread ( new SychronizedBulk ());
        Thread t4=new Thread ( new SychronizedBulk ());
        t1.start ();
        t2.start ();
        t4.start ();
        t3.start ();
        t1.join();
        t2.join ();
        t3.join ();
        t4.join ();
        System.out.println (account);

    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
             synchronized (string){
                 account++;
             }

        }
    }
}

