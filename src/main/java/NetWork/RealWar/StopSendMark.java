package NetWork.RealWar;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kingcall 2017年-08月-27日,20时-46分
 * Descibe
 */
public class StopSendMark {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
            try {
                serverSocket=new ServerSocket(10008);
                Socket socket=serverSocket.accept();
                OutputStreamWriter os=new OutputStreamWriter(socket.getOutputStream());

                os.write("刘文强");
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

}
