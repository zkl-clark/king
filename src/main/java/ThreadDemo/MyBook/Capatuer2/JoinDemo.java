package ThreadDemo.MyBook.Capatuer2;

public class JoinDemo {
    public volatile static int i=0;
    public static class AddThread extends Thread{

        @Override
        public void run() {
            super.run();
            for (i=0;i<10000000;i++){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread=new AddThread();
        addThread.start();
        addThread.join();//这个线程加入了主线程，也就是主线程要等这个线程
        System.out.println(i);
    }
}
