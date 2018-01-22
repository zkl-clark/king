package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 * 这个机制是用来在多线程之间进行通信的
 * 本来线程一只有一个等待方法，线程二只有一个唤醒方法，但是容易发生死锁，就是线程二先运行，之后线程一运行，然后等待，接着没有线程去唤醒线程一了
 */
public class WaitNotify {
    static Object object=new Object ();

    public static void main(String[] args) {
        T1 t1=new T1 ( object );
        T2 t2=new T2 ( object );
        t1.start();
        t2.start();
    }
}
class T1 extends Thread{
    Object object;
    public T1( Object object){
        this.object=object;
    }
    public void run(){
        synchronized (object){
            System.out.println (System.currentTimeMillis ()+"线程一正在运行");
            try {
                System.out.println (System.currentTimeMillis ()+"线程一尝试等待");
               // object.notify ();
               object.wait ();//加不加时间参数，加时间一过，自行尝试获得，不加，直到别人通知
                //object.notify ();
            }catch (Exception e){
                e.printStackTrace ();
            }
            System.out.println (System.currentTimeMillis ()+"线程一再次运行");
        }
    }
}
class T2 extends Thread{
    Object object;
    public T2( Object object){
        this.object=object;
    }
    public void run(){
        synchronized (object){
            System.out.println (System.currentTimeMillis ()+"线程二正在运行");
            try {
                System.out.println (System.currentTimeMillis ()+"线程二尝试唤醒");//会发现唤醒之后，没有立即运行，因为它还没有对象的监视器
                //只有在线程二运行结束之后，它才运行的
                object.notify ();
               // object.wait();
                this.sleep ( 2000 );//发现了wait和sleep的区别，线程一等待，释放了对象监视器，线程二运行，但是线程二并没有因为休眠而放弃对象监视器
                //让线程一先运行，而是继续霸占着对象所，直到线程而运行结束
            }catch (Exception e){
                e.printStackTrace ();
        }
            System.out.println (System.currentTimeMillis ()+"线程二还在次运行");
        }
    }
}
