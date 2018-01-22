package ThreadDemo.RealWar;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import kingUtil.FileUtil;
import kingUtil.CMDUtil;
import kingUtil.ThreadUtil;

/**
 * Created by Administrator on 2017/7/28.
 */
public class RealFireSummary {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入要监测的文件夹路径：");
        String pathfrom=CMDUtil.folderpath(sc);
        System.out.print("请输入目标的文件夹路径：");
        String pathto=CMDUtil.folderpath(sc);
        Timer timer=new Timer();
        timer.schedule(new Copy(pathfrom,pathto),new Date(),2*60*1000);
    }

}
/**
 * 由于要设计该文件检测系统对某一个目录要进行不断的检测，所以该任务要不断的定时执行
 * 新的问题是定时任务中不应该出现输入监测路径
 * */
class Copy extends TimerTask {
    private static String pathfrom;
    private static String pathto;
    public Copy(String pathfrom,String pathto){
        this.pathfrom=pathfrom;
        this.pathto=pathto;
    }

    public static boolean filecopy(){
        //对于初始化大小这个问题，能不能想一个比较好的办法

        int len=new File(pathfrom).list().length;
        ArrayBlockingQueue< Map<String,List<String>>> contextlist=new ArrayBlockingQueue<Map<String, List<String>>>(len);
        List<Callable<Object>> list=new ArrayList<>(2);
        ReadFileThread readFileThread=new ReadFileThread(contextlist,pathfrom,pathto);
        WriteFileThread writeFileThread=new WriteFileThread(contextlist,pathto);
        list.add(writeFileThread);
        list.add(readFileThread);
        List<Future<Object>>futureList= ThreadUtil.runCheckable(list,true);
        return true;
    }

    @Override
    public void run() {
        System.out.println("监测时间："+new SimpleDateFormat("yyyy-MM-dd,hh-mm-ss").format(new Date()));
        System.out.println();
        System.out.println("=============================================================检测结果=============================================================");
        filecopy();
    }
}
/**
 * 文件夹读取线程，读取一个文件夹下面的所有线程
 * @return boolean 是否全部读取成功 是则返回true,否则返回false
 * @param
 * */
class ReadFileThread implements Callable<Object>{
    private static File file;
    String pathfrom;
    String pathto;
    ArrayBlockingQueue< Map<String,List<String>>> contextlist;
    public ReadFileThread(ArrayBlockingQueue< Map<String,List<String>>> queue,String path,String pathto){
        contextlist=queue;
        pathfrom=path;
        this.pathto=pathto;
    }
    /**
     *  filecopy函数仅仅是通过调用读线程和写线程，完成了文件的复制
     *  其实文件的监控，合理的应该是每隔一段时间，对两个文件夹下面的文件进行对比——很明显这是一个定时任务
     *  对比有两个标准，第一个，判断两个文件夹下面的文件个数差异，第二个对比的是时间差异
     *  @param file 要对比的文件
     *  @param pathto 要遍历的文件夹
     * @param file */
    public static boolean superview(File file, String pathto){
        String fileName=file.getName();
        long fileLastTime=file.lastModified();
        FileFilter fileFilter=(file1)->{
            if (file1.isFile())
                return true;
            else return false;
        };
        //对文件，进行了过滤，确保我们筛选出来的都是文件，而不是文件夹
        File[] files=new File(pathto).listFiles(fileFilter);
        //由于一个文件夹下面，不能包含两个相同的文件，
        Map<String,Long>filesInformation=new HashMap<>();
        for (File file1:files){
            filesInformation.put(file1.getName(), file1.lastModified());
        }
        //不包含返回true，包含返回false
        SimpleDateFormat sf=new SimpleDateFormat (  "yyyy-MM-dd,hh-mm-ss");
        if (filesInformation.keySet().contains(fileName)){
            //对于复制过来的文件修改时间和复制时间是一样的
            if (filesInformation.get(fileName)>fileLastTime) {
                return false;
            }
            else {
                System.out.println("***********"+ fileName+"： 是修改文件，修改时间是"+sf.format(new Date(fileLastTime))+
                ",上一次的修改时间是："+sf.format ( new Date ( filesInformation.get(fileName))));
                return true;
            }
        }
        else {
            System.out.println("--------------- "+ fileName+" 是新增文件，创建时间是"+new SimpleDateFormat("yyyy-MM-dd,hh-mm-ss").format(new Date(fileLastTime)));
            return true;
        }

    }
    @Override
    public String call(){
        boolean flag=FileUtil.isDirectory(pathfrom);
        if (!flag) return "valid sourcepath";
        FileFilter fileFilter=(file1)->{
            if (file1.isFile())
                return true;
            else return false;
        };
        //对文件，进行了过滤，确保我们筛选出来的都是文件，而不是文件夹
        List<File> fileList=Arrays.asList(new File(pathfrom).listFiles(fileFilter));
        //遍历每一个文件
        fileList.forEach((File file) -> {
            boolean flag2= superview(file, pathto);
            if (flag2){
                //读取一个文件的内容
                List<String> listcont=new ArrayList<String>();
                try {
                    listcont=FileUtil.readFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //对内容进行处理
                List<String> listcontext=new ArrayList<String>();
                listcont.forEach(line->{
                    listcontext.add( line.replace("党国放心","平西王大事必成"));
                });
                //将处理后的内容加进阻塞式队列
                Map<String,List<String>> map=new HashMap<String, List<String>>();
                map.put(file.getName(),listcontext);
                try {
                    contextlist.offer(map,20,TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return "Read All File Success";
    }
}
/**
 * 写文件到特定的文件夹下面
 * */
class WriteFileThread implements Callable<Object>{
    String pathto;
    ArrayBlockingQueue< Map<String,List<String>>> contextlist;
    public WriteFileThread(ArrayBlockingQueue< Map<String,List<String>>> queue,String path){
        contextlist=queue;
        pathto=path;
    }
    @Override
    public String call() {
        boolean flag = FileUtil.isDirectory(pathto);
        if (!flag) {
            return "valid destinationpath";
        }
        while (true) {
            //得到一个文件的map对象
            Map<String, List<String>> contmap = null;
            try {
                contmap = contextlist.poll(20, TimeUnit.SECONDS);//最多等待100秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //简单判断
            if (contmap == null)
                break;
            Set<String> nameset = contmap.keySet();
            List<String> filecont = new ArrayList<>();
            String filename = "";
            for (String s : nameset) {
                filename = s;
            }
            filecont = contmap.get(filename);
            try {
                flag = FileUtil.writeFile(pathto, filename, filecont, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "All changed File Write Success";
    }
}