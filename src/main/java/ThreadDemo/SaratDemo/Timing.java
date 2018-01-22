package ThreadDemo.SaratDemo;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/7/27.
 */
public class Timing extends TimerTask {

    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Timer timer=new Timer();
        //定时任务是循环执行的
        timer.schedule(new Timing(),calendar.getTime(),1000);
    }
    public void run(){
        System.out.println("定时任务已经确定");
    }
}
