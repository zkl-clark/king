package ThreadDemo.SaratDemo;
/**
 * 好处一：弥补了单继承的缺陷
 * 好初二：你不会忘记实现run函数
 * */
public class T2 implements Runnable {
    public static void main(String[] args) {
        T2 t2=new T2();
        /*设置线程名字的第三种方式*/
        Thread thread=new Thread(t2,"线程一");
        thread.start();


    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+"------>"+"你好");

    }
}
