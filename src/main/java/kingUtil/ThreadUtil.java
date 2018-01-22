package kingUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ThreadUtil {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }
    /**
     * isBlock是否阻塞标志位，是的话等待所有线程完成后统一返回，否则线程已启动立即返回
     * 参数是 Callable类型的线程集合，Callable的范型是什么都可以
     * */
    public static List<Future<Object>> runCheckable(List<Callable<Object>>list, boolean isBlock){
       //对象检查,这里的范型object,会使UTIL的使用面更广泛
        if (list==null||list.size()<1) return new ArrayList<>();
        //确保每一个都不为空
        for (Callable<?> call:list){
            if (call==null) return new ArrayList<>();
        }
        if (!isBlock) return new ArrayList<>();
        //创建线程池，批量执行任务
        ExecutorService exec=Executors.newCachedThreadPool();
        List<Future<Object>>futureList= null;//返回值的集合
        try {
            futureList = exec.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futureList;
    }

    /**
     * 该方法对callable的返回值集合进行处理
     * */
    public static List<Object> getAllCallableReturn(List<Future<Object>> futureList) throws InterruptedException{
        List<Object> returnValue=new ArrayList<>(futureList.size());
        while (true){
            //每隔10s就获取一次
            Iterator<Future<Object>>iterator=futureList.iterator();
            while (iterator.hasNext()){
                Future<Object>future=iterator.next();
                if (future.isDone()){
                    Object o= null;
                    try {
                        o = future.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    returnValue.add(o);
                    iterator.remove();
                }

            }
            if (futureList.size()==0)
                break;
            TimeUnit.SECONDS.sleep(10);
        }
        return returnValue;
    }
}
