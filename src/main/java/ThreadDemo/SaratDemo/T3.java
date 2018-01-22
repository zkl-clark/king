package ThreadDemo.SaratDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 带返回值的线程
 * 具有反省的特点
 *
 * */
public class T3 implements Callable<String> {
    public static void main(String[] args) {
        FutureTask<String> fa=new FutureTask<String>(new T3());
        FutureTask<String> fb=new FutureTask<String>(new T3());
        Thread thread=new Thread(fa);
        Thread thread2=new Thread(fb);
        thread.start();
        System.out.println("-------*"+fa.isDone());
        thread2.start();
        //get()方法是个阻塞式方法，当被调用之后，阻塞在调用者线程
        try {
            System.out.println(fa.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

    @Override
    public String call() throws Exception {
        for(int i=0;i<10;i++){
            Thread.sleep(1*1000);
            System.out.println(Thread.currentThread().getName()+"-------->"+i);
        }
        return "平西王早晚会回来的";
    }
}
