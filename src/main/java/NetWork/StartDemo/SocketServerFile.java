package NetWork.StartDemo;

import kingUtil.FileUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/7/31.
 */
public class SocketServerFile{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket ( 10007 );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        if (serverSocket==null){
            System.out.println ("服务器启动失败");
            return;
        }else {
            System.out.println ("服务器开启成功");
        }
        ExecutorService exec= Executors.newCachedThreadPool ();
        //开启线程
        while (true){
            Socket socket=null;
            try {
                socket=serverSocket.accept ();
                String ip=socket.getInetAddress ().getHostAddress ();
                String hostname=socket.getInetAddress ().getHostName ();
                System.out.println ("主机名:"+hostname+"  IP："+ip+".............已经连接");
            }catch (Exception e){
                e.printStackTrace ();
            }
            //口令验证
            BufferedReader br=null;
            try {
                br=new BufferedReader ( new InputStreamReader ( socket.getInputStream () ) );
            }
            catch (Exception e){
                e.printStackTrace ();
            }

            String password= br.readLine ();
            if (!"明知山有虎".equals ( password)){
                System.out.println (password);
                break;
            }
            System.out.println ("口令验证完毕，口令是："+password);
            exec.submit ( new SerVer ( socket ));
        }

    }
}
class SerVer implements Runnable{
    private  Socket socket;
    String Filefolder="D:\\test\\NetWork";
    public SerVer(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        //构造流输入流,读取文件名
        BufferedReader br=null;
        String filename=null;//从客户端读取文件文件名，服务器端路径固定，
       try {
           br=new BufferedReader ( new InputStreamReader ( socket.getInputStream () ) );
           filename=socket.getInetAddress ().getHostAddress ()+br.readLine ();
           System.out.println ("要接收文件的名字是："+filename);
       }
       catch (Exception e){
           e.printStackTrace ();
       }
       //获取文件的全路径
       String fallpath= FileUtil.getFullPath ( Filefolder,filename );
        //构造流输入流,读取文件内容
       Path path=Paths.get ( fallpath );
        InputStream inputStream=null;
        try {
            inputStream=socket.getInputStream ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        //完成文件复制
        try {
            Files.copy ( inputStream,path);//目的地在后面
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
