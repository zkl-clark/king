package ThreadDemo.MyBook.Capatuer2;


/**
 * Created by Administrator on 2017/7/30.
 */

public class ThreadGroupDemo implements Runnable {
    public static void main(String[] args) {
        ThreadGroup tg=new ThreadGroup ( "PrintGroup");//创建线程组，并命名
        Thread t1=new Thread (tg, new ThreadGroupDemo (),"T1");
        Thread t2=new Thread (tg, new ThreadGroupDemo (),"T2");
        t1.start ();
        t2.start ();
        System.out.println (tg.activeCount ());
        System.out.println ("=======================================");
        tg.list ();

    }

    @Override
    public void run() {
        String groupname=Thread.currentThread ().getThreadGroup ().getName ()+"-----"+Thread.currentThread ().getName ();
        System.out.println (groupname);
    }
}







