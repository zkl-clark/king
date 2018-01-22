package ThreadDemo.MyBook.Capatuer2;

import java.util.HashMap;
import java.util.Map;

public class ThreadHashMap {
    static Map<String,String> map=new HashMap<>(100);
    public static class AddThread implements Runnable{
        int start=0;
        public AddThread(int start){
            this.start=start;
        }
        @Override
        public void run() {
            for (int i=start;i<10000;i+=1){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AddThread(0));
        Thread t2=new Thread(new AddThread(0));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
