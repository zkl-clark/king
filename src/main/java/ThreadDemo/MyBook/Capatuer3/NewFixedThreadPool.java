package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kingcall 2017年-08月-01日,23时-33分
 * Descibe
 */
public class NewFixedThreadPool {
    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println ("当前时间："+System.currentTimeMillis ()+"，Thread ID："+Thread.currentThread ().getId ());
            try {
                Thread.sleep ( 5000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

        }
    }

    public static void main(String[] args) {
        MyTask task=new MyTask ();
        ExecutorService exec= Executors.newFixedThreadPool (5);
        for (int i = 0; i <20 ; i++) {
            exec.submit ( task );

        }
    }
}
