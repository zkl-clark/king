package ThreadDemo.MyBook.Capatuer2;

public class SynchronizedIntegerEror implements Runnable {
    static SynchronizedIntegerEror instance =new SynchronizedIntegerEror();
    public static Integer i=0;

    @Override
    public void run() {
        for (int j = 0; j <10000 ; j++) {
            synchronized (i){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
