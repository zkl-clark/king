package ThreadDemo.MyBook.Capatuer2;

/**
 * 线程组是为了对线程进行分类的，方便管理
 */

public class ThreadGroupClass implements Runnable{
    public static void main(String[] args) {
        ThreadGroup tg=new ThreadGroup("PrintGroup");
        Thread t1=new Thread(tg,new ThreadGroupClass(),"T1");
        Thread t2=new Thread(tg,new ThreadGroupClass(),"T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }

    @Override
    public void run() {
        String groupAndNAme=Thread.currentThread().getThreadGroup().getName()+"————"+Thread.currentThread().getName();
        while (true){
            System.out.println("I am "+groupAndNAme);
        }

    }
}
