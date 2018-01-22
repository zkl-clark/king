package NetWork.StartDemo;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/31.
 */
public class SocketClient
{
    public static void main(String[] args) throws IOException {
        Socket socket=null;
        try{
            socket=new Socket ( "localhost",11111 );
        }catch (Exception e){
            e.printStackTrace ();
        }
        PrintWriter out=null;
        try {
            out=new PrintWriter ( socket.getOutputStream () );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        try {
            out.println ( "你好" );
            out.flush ();
        } catch (Exception e) {
            e.printStackTrace ();
        }

        InputStream inputStream=socket.getInputStream ();
        BufferedReader br=new BufferedReader ( new InputStreamReader ( inputStream ) );
        System.out.println ("来自服务端的数据:"+br.readLine ());

        try {
            Thread.sleep (1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        //第二次通话
        out.println ("来自星星的狗");
        out.flush ();
        System.out.println ("来自服务端的数据:"+br.readLine ());

        try {
            socket.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
