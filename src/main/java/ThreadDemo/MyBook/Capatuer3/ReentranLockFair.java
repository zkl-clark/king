package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Authork:kingcall
 * @Description:sychronized就是不公平锁，不公平锁对排队的所有线程可能产生饥饿现象，但是公平锁就不会，但是会出现性能比较差
 *                         因为要维护一个队列,如果不是公平锁，一个线程会倾向再次获得该锁
 * @Date:$time$ $date$
 */
public class ReentranLockFair implements Runnable{

    public static ReentrantLock fairlock=new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                fairlock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");

            }finally {
                fairlock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        ReentranLockFair Fair=new ReentranLockFair();
        Thread t1=new Thread(Fair,"线程一");
        Thread t2=new Thread(Fair,"线程二");
        t1.start();
        t2.start();
    }
}
