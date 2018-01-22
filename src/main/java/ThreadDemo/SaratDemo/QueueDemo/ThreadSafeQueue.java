package ThreadDemo.SaratDemo.QueueDemo;

import sun.security.provider.NativePRNG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ThreadSafeQueue {
    public static <T> void main(String[] args) {
        ArrayBlockingQueue<String> list= new ArrayBlockingQueue<>(1000);
        for (int i=0;i<1000;i++) list.offer(i + "");
        System.out.println("阻塞式队列的大小是"+list.size());
        ThreadRun threadRun=new ThreadRun(list);
        ThreadRun threadRun2=new ThreadRun(list);
        ExecutorService exec= Executors.newCachedThreadPool();
        List<Callable<String>>list1=new ArrayList<>();
        list1.add(threadRun);
        list1.add(threadRun2);
        try {
            exec.invokeAll(list1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

/**
 * 虽然synchronized关键字锁住了，没有让曾经报错误的代码包错误，但是没有达到我们的目的，——好像任然是单线程
 * 我们在 QueueDemo报中，利用队列再次是实现，以达到目的
 * 最终发现执行的效果很好，不过对于inVokeAll()这个方法而言，其参数list的类型不许是callable
 * */
class ThreadRun implements Callable<String>{
    private static ArrayBlockingQueue<String> list;
    public ThreadRun( ArrayBlockingQueue<String>list){
        this.list=list;
    }
    @Override
    public String call() {
        int cnt=0;
        while (true){
            String ob=list.poll();
            //有一个线程取出的结果为空，说明队列中再无字符串
            if (ob==null)
                break;;
            cnt++;


        }
        System.out.println(Thread.currentThread().getName()+"----->"+cnt);
        return null;

    }
}
