package ThreadDemo.SaratDemo;

/**
 * Created by Administrator on 2017/7/27.
 */
public class T12 extends Thread{
    private int m=10;

    public static void main(String[] args) {
        T12 t12=new T12();
        System.out.println(t12.m);
        t12.run();
        System.out.println(t12.m);
        //线程的start方法不仅仅是单纯的调用了run方法，想通过线程去改变类变量怎么实现
        t12.start();
        System.out.println(t12.m);
    }
    @Override
    public void  run(){
        this.m=20;
    }

}
