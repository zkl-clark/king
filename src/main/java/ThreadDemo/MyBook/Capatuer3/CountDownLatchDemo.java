package ThreadDemo.MyBook.Capatuer3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kingcall 2017年-08月-01日,23时-10分
 * Descibe
 */
public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch END=new CountDownLatch ( 10 );//倒数十个数
    static final CountDownLatchDemo DEMO=new CountDownLatchDemo ();
    public static void main(String[] args) {
        ExecutorService exec= Executors.newFixedThreadPool ( 10 );
        for (int i=0;i<10;i++){
            exec.submit ( DEMO );
        }
        try {
            END.await ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        System.out.println ("火箭发射");
        END.countDown ();

    }

    @Override
    public void run() {
        try {

            Thread.sleep ( new Random (  ).nextInt (5)*1000 );
            System.out.println ("chech Complete");
            END.countDown ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }
}
