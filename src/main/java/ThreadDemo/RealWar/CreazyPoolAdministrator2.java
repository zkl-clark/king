package ThreadDemo.RealWar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/27.
 */
public class CreazyPoolAdministrator2 {
    public static void main(String[] args) {

        List<Callable<Integer>> list=new ArrayList<>();
        InputThread2 input2=new InputThread2(10000);
        OutputThread2 output2=new OutputThread2(10000);
        ExecutorService exec= Executors.newCachedThreadPool();
        list.add(output2);
        list.add(input2);
        try {
            exec.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class InputThread2 implements Callable<Integer>{
    int size;
    public InputThread2(Integer n){
        size=n;
    }
    public synchronized int change(){
        int m=0;
        while (m<size){
            for (int i=0;i<5;i++) {
                m++;
                if (m>size)break;
            }
            if (m>size)break;
            System.out.println(Thread.currentThread().getName()+"注水之后------>"+" "+m+" "+"立方米");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return m;
    }
    public Integer call(){
        return change();
    }
}
class OutputThread2 implements Callable<Integer>{
    int size;
    public OutputThread2(Integer n){
        size=n;
    }
    public synchronized int change(){
        int m=0;
        while (m<size){
            for (int i=0;i<3;i++) {
                m--;
                if (m>size)break;
            }
            if (m>size)break;
            System.out.println(Thread.currentThread().getName()+"抽水之后------>"+" "+m+" "+"立方米");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return m;
    }
    public Integer call(){
       return change();
    }
}
