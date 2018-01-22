package ThreadDemo.SaratDemo;

public class T1 extends Thread{
    public T1(){

    }
    public T1(String name){

        super(name);

    }
    public static void main(String[] args) {
        /*初始化线程的优先级——默认是5*/
        T1 thread1=new T1();
        thread1.start();
        /*线程的初始优先级由所创建的线程决定*/
        Thread.currentThread().setPriority(2);
        T1 thread2=new T1();
        thread2.start();
        /*线程的名字的设定——1*/
        T1 thread3=new T1();
        thread3.setName("线程三");
        thread3.start();
        /*线程的名字的设定——2*/
        T1 thread4=new T1("线程四");
        thread4.start();


    }
    @Override
    public void run(){
        for (int i=0;i<1;i++){
            System.out.println(Thread.currentThread().getName()+"----->"+Thread.currentThread().getPriority());
        }

    }
}
