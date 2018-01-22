package NetWork.StartDemo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import kingUtil.CMDUtil;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/31.
 * 对象能不能直接发送
 */
public class SocketClientFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket=new Socket ( "localhost" ,10007);
        OutputStream out=socket.getOutputStream ();
        PrintWriter writer=new PrintWriter ( out);
        //第一次验证口令，如果口令正确，服务器端才会为你开启线程，否则不开启
        writer.println("明知山有虎");
        writer.flush ();//-------------------->这是个坑啊
        Scanner scanner=new Scanner ( System.in );
        String select="";//有一个默认的选择退出
        while (true){
            System.out.println ("请选择 ：   发送文件单个（1）   发送文件夹（2）   退出（0）");
            select=scanner.nextLine ();
            if ("2".equals ( select )) System.out.println ("暂时不提供该功能");
            if ("0".equals ( select ))System.exit ( 0 );
            if ("1".equals ( select )){
                System.out.print ("请输入文件路径");
                String pa= CMDUtil.folderpath ( scanner );
                File sendFile=new File ( pa );
                String filename=sendFile.getName ();
                writer.println (filename);//发送文件名字
                System.out.println ("要发送的文件名："+filename);
                writer.flush ();
                Files.copy (sendFile.toPath (), out);//目的地在后面
                out.flush ();
            }
            else
                break;
        }
    }
}
