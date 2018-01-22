package ThreadDemo.MyBook.Capatuer3;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Administrator on 2017/7/31.
 */
public class ForkJoin extends RecursiveTask<Long>{
    private static final int THRESHOLD=10000;
    private  long start;
    private  long end;
    public ForkJoin(long start,long end){
        this.start=start;
        this.end=end;

    }
    @Override
    protected Long compute() {
        long sum=0;
        boolean canCompute=(end-start)<THRESHOLD;
        if (canCompute){
            for (long i = start; i <=end ; i++) {
                sum+=i;

            }
        }
        else {
            long step=(start+end)/100;//将工作分成100个小任务
            ArrayList<ForkJoin> subTasks=new ArrayList<> (  );
            long pos=start;
            for (int i = 0; i < 100; i++) {
                long LastOne=pos+step;
                if (LastOne>end)
                    LastOne=end;
                ForkJoin subTask=new ForkJoin ( pos,LastOne );
                pos+=step+1;
                subTasks.add ( subTask );
                subTask.fork ();
                for (ForkJoin task:subTasks)
                {
                    sum+=task.join ();
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
