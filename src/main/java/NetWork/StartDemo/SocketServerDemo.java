package NetWork.StartDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/31.
 */
public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket (  11111);//监听一个端口
        Socket s=ss.accept ();//阻塞式方法
        System.out.println (s.getInetAddress ().getHostName ()+".........connectde");
        BufferedReader br=new BufferedReader ( new InputStreamReader ( s.getInputStream () ) );
        String clientmessage=br.readLine ();
        System.out.println ("客户端数据："+clientmessage);

        //回传信息给客户端
        PrintWriter out=new PrintWriter ( s.getOutputStream () );
        out.println ( "这是来自火星的问候" );
        System.out.println ("客户端数据："+br.readLine ());
        out.flush ();
        out.flush ();
        s.close ();
        ss.close ();
    }
}

