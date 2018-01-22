package ThreadDemo.MyBook.Capatuer3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by kingcall 2017年-08月-01日,23时-01分
 * Descibe
 */
public class SemaphoreDemo {
    final static Semaphore semp=new Semaphore ( 5 );

    public static void main(String[] args) {

        Runnable t=()->{
            try {
                semp.acquire ();
                Thread.sleep ( 5*1000 );
                System.out.println (Thread.currentThread ().getId ()+"----done");
                semp.release ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

        };
        ExecutorService exec= Executors.newFixedThreadPool ( 20 );
        for (int i=0;i<20;i++){
            exec.submit ( t );
        }
    }

}
