package ThreadDemo.MyBook.Capatuer2;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/29.
 * 有时候正确，有时候错误，破坏了ArrayList的增长机制
 */
public class ThreadArrayList implements Runnable{
    static ArrayList<Integer> list=new ArrayList<Integer>();
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new ThreadArrayList());
        Thread t2=new Thread(new ThreadArrayList());
        t1.start();
        t2.start();
        //join()这两个方法必须有，这是因为我们必须等用户线程运行结束，才能查看集合大小
        t1.join();
        t2.join();
        System.out.println(list.size());
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }
}
