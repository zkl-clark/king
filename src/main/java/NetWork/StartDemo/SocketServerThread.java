package NetWork.StartDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/31.
 */
public class SocketServerThread implements Runnable {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket ( 10007 );
        if (serverSocket!=null){
            System.out.println ("服务器打开完成");
            Socket socket=null;
            while (true){
                try {
                    socket=serverSocket.accept ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
                new Thread ( new SocketServerThread (socket) ).start ();
                System.out.println ("服务器处理客户端请求完成");
            }
        }
    }

    private Socket socket;
    //alt+insert
    public SocketServerThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        System.out.println (socket.getInetAddress ().getHostName ()+".........connectde");
        Reader reader= null;
        try {
            reader = new InputStreamReader ( socket.getInputStream () );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        //socket只有字节流
        BufferedReader br=new BufferedReader ( reader );
        String clientmessage=null;
        try {
            clientmessage =br.readLine ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println ("客户端数据："+clientmessage);

        //回传信息给客户端
        PrintWriter out= null;
        try {
            out = new PrintWriter ( socket.getOutputStream () );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        out.println ( "这是来自火星的问候" );
        out.flush ();
        try {
            clientmessage =br.readLine ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println ("客户端数据："+clientmessage);
        out.println ( "来自星星的你吧！" );
        out.flush ();

        try {
            socket.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }
}
