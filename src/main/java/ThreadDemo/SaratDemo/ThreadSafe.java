package ThreadDemo.SaratDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ThreadSafe {
    public static void main(String[] args) {
        List<String>list= new ArrayList<>(1000);
        for (int i=0;i<1000;i++){
            list.add(i+"");
        }
        Thread1 R1=new Thread1(list);
        Thread t1=new Thread(R1);
        Thread t2=new Thread(R1);
        t1.start();
        t2.start();

    }
}
/**
 * 虽然synchronized关键字锁住了，没有让曾经报错误的代码包错误，但是没有达到我们的目的，——好像任然是单线程
 * 我们在 QueueDemo报中，利用队列再次是实现，以达到目的
 * */
class Thread1 implements Runnable{
    private static List<String>list;
    Iterator<String> iterator;
    public Thread1( List<String>list){
        this.list=list;
        iterator =list.iterator();
    }

    @Override
    public void run() {
        int cnt=0;
        synchronized (this) {
            iterator.next();
            iterator.remove();
            try {
                Thread.sleep ( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            cnt++;

        }
        System.out.println(Thread.currentThread().getName()+"----->"+cnt);

    }
}
