package ThreadDemo.SaratDemo;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ThreadPool2 {
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        //匿名类或接口的方式
        exec.submit(new Runnable() {
            public void run(){
                System.out.println("你好");
            }
        });
        //创建变量的方式
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println("龟儿子的");
            }
        };
        exec.submit(r);
        //lambda表达式的方式,需要注意的是，由于是函数表达式，所以我们的大括号里面只是所有的方法体，默认的给了
        //该接口里面唯一的方法了
        Callable<String>task=()->{
                System.out.println("你妈的");
                return "我是中国人";
        };
        Future<String>s=exec.submit(task);
        try {
            System.out.println(s.get());
        }catch (Exception e){}
    }

}
