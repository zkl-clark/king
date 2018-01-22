package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Authork:kingcall
 * @Description:重入锁的中断相应，在陷入死锁的情况下，取消对锁的请求，也就是中断操作
 * @Date:$time$ $date$
 */
public class ReentranLockInterrupt implements Runnable{
    int lock;

    public ReentranLockInterrupt(int m) {
        lock = m;
    }

    public static ReentrantLock lock1 = new ReentrantLock ();//两个构造函数，公平锁和非公平锁
    public static ReentrantLock lock2 = new ReentrantLock ();//两个构造函数，公平锁和非公平锁

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread ( new ReentranLockInterrupt ( 1 ) );
        Thread t2 = new Thread ( new ReentranLockInterrupt ( 2 ) );
        t1.start ();
        t2.start ();
        Thread.sleep ( 2 * 1000 );
        //如果没有下面这句，该程序永远停不下来，因为程序进入死锁
        //另外一个优点也显示出来，即使线程没有在休眠中（休眠中也停不下来），也能使一个线程停止运行，
        t1.interrupt ();
    }

    @Override
    public void run() {
        if (lock == 1) {
            //锁定锁1，除非被打断
            try {
                lock1.lockInterruptibly ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            //该线程休眠2秒
            try {
                Thread.sleep ( 2 * 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            //锁定锁2，除非被打断
            try {
                lock2.lockInterruptibly ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        } else {
            //锁定锁2，除非被打断
            try {
                lock2.lockInterruptibly ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            try {
                Thread.sleep ( 2 * 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            //锁定锁1，除非被打断
            try {
                lock1.lockInterruptibly ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
        System.out.println ( Thread.currentThread ().getName () + ":执行结束" );
    }

}
