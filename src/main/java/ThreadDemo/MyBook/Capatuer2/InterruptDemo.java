package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 * 没有休眠的线程是不能打断的——仅仅是告诉该线程有人希望你退出，退不退出，线程自行决定，它就是抛个异常给你
 */
public class InterruptDemo implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo interruptDemo=new InterruptDemo();
        Thread thread=new Thread(interruptDemo,"测试线程");
        thread.start();
        Thread.sleep(2000);//在主线程中是串行执行的
        thread.interrupt();//加上停止标记，一般情况下，不会停止的，如果线程有休眠给个异常，没休眠，照样运行，而且还会发现，由于异常的抛出，中断标记
        //被清除了，否则应该不断地抛出异常才对
        System.out.println(thread.isInterrupted());//这里测试线程是否被中断的意思是，看有没有人希望它停止，或者看有没有加上停止标记


    }
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"我正在运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
