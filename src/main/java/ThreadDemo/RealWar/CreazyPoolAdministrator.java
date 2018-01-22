package ThreadDemo.RealWar;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/27.
 */
public class CreazyPoolAdministrator {
    public static void main(String[] args) {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(10000);
        InputThread input=new InputThread(blockingQueue);
        OutputThread output=new OutputThread(blockingQueue);
        List<Callable<String>>list=new ArrayList<Callable<String>>();
        list.add(input);
        list.add(output);
        ExecutorService exec= Executors.newCachedThreadPool();
        try {
            exec.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class InputThread implements Callable<String>{
    ArrayBlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(10000);
    public InputThread(ArrayBlockingQueue queue){
        blockingQueue=queue;
    }

    public String call() throws Exception {

        while(true){
            boolean flag=true;
            for (int i=0;i<5;i++){
                flag= blockingQueue.offer("一立方米");
                if (!flag)
                    break;
            }
            if (!flag)break;
           TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"注水之后------>"+" "+blockingQueue.size()+" "+"立方米");
        }
        return "水已经注满了";
    }

}

class OutputThread implements Callable<String>{
    ArrayBlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(10000);
    public OutputThread(ArrayBlockingQueue queue){
        blockingQueue=queue;
    }

    public String call() throws Exception {

        while(blockingQueue.size()<10000){
            String flag="";
            for (int i=0;i<3;i++){
                flag= blockingQueue.poll(1,TimeUnit.SECONDS);
            }
            System.out.println(Thread.currentThread().getName()+"抽水之后------>"+" "+blockingQueue.size()+" "+"立方米");
            TimeUnit.SECONDS.sleep(1);
        }
        return "水已经注满了";
    }

}
