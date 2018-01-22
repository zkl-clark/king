package ThreadDemo.MyBook.Capatuer2;

public class VolatileDemo {
    static  volatile int i=0;
    public static class PlustTask implements Runnable{
        @Override
        public void run() {
            /*注意一下，在这里要验证这个错误，每个循环的线程的次数一定要多一点*/
            for (int k=0;k<1000;k++){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PlustTask task=new PlustTask();
        Thread[] threads=new Thread[10];
        for (int i=0;i<10;i++){
            threads[i]=new Thread(task );
            threads[i].start();
        }
        for (int i1 = 0; i1 < threads.length; i1++) {
            threads[i1].join();
        }
        System.out.println(i);

    }
}
