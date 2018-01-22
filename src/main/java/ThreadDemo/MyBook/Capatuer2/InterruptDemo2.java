package ThreadDemo.MyBook.Capatuer2;

/**
 * Created by Administrator on 2017/7/30.
 */
public class InterruptDemo2 extends Thread {
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo2 in2=new InterruptDemo2();
        in2.start();
        Thread.sleep ( 2000 );//主线程休眠的意义在哪里
        in2.sleep ( 1000 );//在主线程中让用户线程休眠的意义在于——次数不是很多，就让它等等其他线程，也不会有异常
        in2.interrupt();//这里仅仅只是设置终止标记，由于线程没有处于休眠状态，导致连异常都没有报

    }
    public void run(){
        while (true){
            System.out.println("测试线程正在运行");
            if (this.isInterrupted()){
                System.out.println("我已经被终止");
                break;
            }
        }
    }
}
