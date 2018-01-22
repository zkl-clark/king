package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/29.
 * 仅仅有volatile关键字是不行的
 * synchronized关键字必须加在静态方法上，普通方法不行，因为静态方法是属于类的
 */
public class SynchronizedErrorCount implements  Runnable{
    static volatile int i=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new SynchronizedErrorCount());
        Thread t2=new Thread(new SynchronizedErrorCount());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            crease2();
        }
        System.out.println(Thread.currentThread().getName()+"----"+i);

    }
    /*错误的方法，加到了不同的对象上去*/
    public  synchronized  void  crease(){
        i++;
    }
    /*这个是对的，锁到了静态方法也就是class上*/
    public synchronized static   void  crease2(){
        i++;
    }

}
