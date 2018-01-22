package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 */
public class SynchronizedInstance implements Runnable{
    static volatile int account=0;
    static SynchronizedInstance Instance=new SynchronizedInstance ();
    public static void main(String[] args) throws InterruptedException {
        //根据树上的说法，这里的构造是错误的  应该是 Thread t1=new Thread ( Instance);
        Thread t1=new Thread ( Instance);
        Thread t2=new Thread ( Instance);
        Thread t3=new Thread ( Instance);
        Thread t4=new Thread (Instance);
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

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
              synchronized (this){
                  account++;
              }

        }
    }
}
