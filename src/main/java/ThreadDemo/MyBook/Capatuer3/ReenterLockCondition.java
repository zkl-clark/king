package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/7/30.
 *
 */
public class ReenterLockCondition implements Runnable{
    public static ReentrantLock lock=new ReentrantLock (  );
    public static Condition condition=lock.newCondition ();
    @Override
    public void run() {
        try {

            lock.lock ();
            System.out.println ("我执行到这里了");
            condition.await ();//会使当前线程等待，并释放锁，当其他线程调用 signal()或者signalAll()方法时，该线程会重新获得锁，并运行
            System.out.println ("The Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace ();
        }finally {
            lock.unlock ();
        }
    }

    public static void main(String[] args) {
        ReenterLockCondition ree=new ReenterLockCondition (  );
        Thread t1=new Thread ( ree );
        t1.start ();//直接启动后进入等待
        try {
            Thread.sleep ( 2*1000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        lock.lock ();//------------------------------>会发现，原因很简单，Condition是依赖于锁的，要想使用condition,必须获得锁
        condition.signal ();//获得锁之后继续运行
        lock.unlock ();//---------------------------->会发现，没有这两句依然会报错为什么

    }
}
