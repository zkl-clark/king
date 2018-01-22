package ThreadDemo.MyBook.Capatuer2;

import java.util.concurrent.TimeUnit;

public class Daemon {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am Alive"+"\t"+System.currentTimeMillis());
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new DaemonT();
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000*10);//为了演示守护线程，让主线程等待10秒钟，然后让守护线程进行输出
    }
}
