package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 */
public class SynchronizedMethod implements Runnable{
    static volatile int account=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread ( new SynchronizedMethod ());
        Thread t2=new Thread ( new SynchronizedMethod ());
        Thread t3=new Thread ( new SynchronizedMethod ());
        Thread t4=new Thread ( new SynchronizedMethod ());
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
            increase ();
        }
    }
    //对要同步的代码块进行加锁，形成锁方法,必须是静态方法
    public synchronized static void increase(){
        account++;
    }
}
