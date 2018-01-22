package ThreadDemo.MyBook.Capatuer5;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/7/30.
 */
public class SingelDesignDemo {
    public static void main(String[] args) {
        //Single single=new Single ();
//        Single s=Single.getSingle ();
//        System.out.println (s.Status);
        System.out.println (Single.Status);
        Calendar s=Calendar.getInstance ();


    }

}
class Single{
    //确保了你不能直接在其他类中创建对象了
    static int Status=0;
    private Single(){
        System.out.println ("对象被创建");
    }
    private static Single singleInstance=new Single ();//private保证了你不能通过类名访问，什么叫只能在当前类中访问
    //这个对象在类被加载的时候已经创建了，所以接下来不管你通过什么方式获得对象，都是同一个
    public static Single getSingle(){
        return singleInstance;
    }

}
