package ThreadDemo.SaratDemo.Tools;

import java.util.Random;

/**
 * Created by kingcall 2017年-08月-08日,23时-21分
 * Descibe
 * 使用这个工具类可以很简洁地编写出优美的多线程程序。
 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * ThreadLocal是如何做到为每一个线程维护变量的副本的呢？其实实现的思路很简单：在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，
 * 而值对应线程的变量副本。我们自己就可以提供一个简单的实现版本：
 * ，我们需要满足这样一个条件：变量是同一个，但是每个线程都使用同一个初始值，也就是使用同一个变量的一个新的副本。
 */
public class ThreadLocalDemo implements Runnable {
    private final static ThreadLocal studentLocal=new ThreadLocal ();

    public static void main(String[] args) {
        ThreadLocalDemo td = new ThreadLocalDemo();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();

    }

    @Override
    public void run() {
        accessStudent ();
    }
    public void accessStudent(){
        String currentThreadName=Thread.currentThread ().getName ();
        System.out.println (currentThreadName+" is Runing");
        Random random=new Random (  );
        int age=random.nextInt (100);
        System.out.println (currentThreadName+" set age: "+age);
        Student student=getStudent ();
        student.setAge ( age );
        System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());

    }
    protected Student getStudent(){
        //获取本地线程变量
        Student student=(Student)studentLocal.get ();
        //首次执行该方法返回色是null
        if (student==null){
            student=new Student ();
            studentLocal.set ( student );
        }
        return student;
    }
}
