package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Authork:kingcall
 * @Description:对于死锁除了外部通知，还有就是限时等待，在一定的时间内还没有获取锁的话，就放弃
 * @Date:$time$ $date$
 */
public class ReentranLockWaitTime implements Runnable{
    public static ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
               /* lock.tryLock();  尝试获取锁，获取不到立即返回*/
                Thread.sleep(1000*6);
            }else {
                System.out.println(Thread.currentThread().getName()+"5 秒内获取锁失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        ReentranLockWaitTime time=new ReentranLockWaitTime();
        Thread t1=new Thread(time,"线程一");
        Thread t2=new Thread(time,"线程二");
        t1.start();
        t2.start();
    }


}
