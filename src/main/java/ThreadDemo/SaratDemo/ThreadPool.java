package ThreadDemo.SaratDemo;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ThreadPool extends Thread {
    public static void main(String[] args) {
        //ExecutorService仅仅是一个接口
        ExecutorService exec= Executors.newCachedThreadPool();
//        Future d=exec.submit(new ThreadPool());
        //当我们注销掉下面的不注销下面的方法，我们发现主线程阻塞了，并且线程池起了作用，
//        try {
//            System.out.println(d.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        exec.execute(new ThreadPool());
        //exec.submit(new Te());//submit可以提交一切线程对象，但是execute不行
        //方法invokeAny和invokeAll执行invokeAll执行最常用的形式，执行任务集合，然后等待至少一个或全部完成。
        // （类别ExecutorCompletionService可用于编写这些方法的自定义变体。）
       Future s= exec.submit(new ThreadPool());
        try {
           String ss= (String) s.get(1,TimeUnit.SECONDS);
            System.out.println(ss);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
    public void run(){

       for (int i=0;i<100;i++){
           System.out.println(Thread.currentThread().getName()+"--------->"+"测试"+"------>"+i);
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }

    }

}
class Te implements Callable<String>{
    @Override
   public String call()throws Exception{

       for(int i=0;i<10;i++){
           System.out.println("ceshi");
           TimeUnit.SECONDS.sleep(1);
       }

        return "ceshi";
   }
}

