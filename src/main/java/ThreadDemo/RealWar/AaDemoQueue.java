package ThreadDemo.RealWar;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/7/27.
 */
public class AaDemoQueue {
    public static void main(String[] args) {
        Strbuffers stringBu = new Strbuffers();
        ThtreadCreater create = new ThtreadCreater(stringBu);
        ThtreadCustomer delete = new ThtreadCustomer(stringBu);
        create.start();
        delete.start();
    }
}

class ThtreadCreater extends Thread{
    Strbuffers strbuffer;
    public ThtreadCreater(Strbuffers strbuffer){
        this.strbuffer=strbuffer;
    }
    public void  run(){
        while (true){
            strbuffer.create();
        }
    }
}
class ThtreadCustomer extends Thread{
    Strbuffers strbuffer;
    public ThtreadCustomer(Strbuffers strbuffer){
        this.strbuffer=strbuffer;
    }
    public void  run(){
        while (true){
            strbuffer.delete();
        }
    }
}
class Strbuffers{
    BlockingQueue <Character>stringB = new ArrayBlockingQueue(1000);
    Random random = new Random();

    public  void create() {
        char a = (char) (random.nextInt(26) + 65);
        try {
            stringB.put(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-----生产了" + a);
    }

    public void delete() {
        char a='c';
        try {
           a = stringB.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-----删除了" + a);
        }
 }

