package NetWork.RealWar;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Socket;

/**
 * Created by kingcall 2017年-08月-27日,21时-02分
 * Descibe
 */
public class StopSendMarkClient {
    public static void main(String[] args) {
        Socket socket=null;
        try {
            socket=new Socket("localhost",10008);
            InputStream is=socket.getInputStream();
            byte[] buffer=new byte[10];
            is.read(buffer);
            System.out.println(new String(buffer));
            is.read(buffer);
            System.out.println(new String(buffer));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
