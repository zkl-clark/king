package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/7/30.
 * 特点：操作明显，使用简洁灵活，但是还是替代不了 wait和notify机制
 */
public class ReentrantLockDemo implements Runnable{
    public static int account=0;
    public static ReentrantLock lock=new ReentrantLock (  );//两个构造函数，公平锁和非公平锁
    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread ( new ReentrantLockDemo () );
        Thread t2=new Thread ( new ReentrantLockDemo () );
        Thread t3=new Thread ( new ReentrantLockDemo () );
        t1.start ();
        t3.start ();
        t2.start ();
        t1.join ();
        t3.join ();
        t2.join ();
        System.out.println (account);
    }

    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            lock.lock ();
            account++;
            lock.unlock ();
        }

    }
}
