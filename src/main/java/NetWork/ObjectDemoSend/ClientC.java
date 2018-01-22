package NetWork.ObjectDemoSend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by kingcall 2017年-08月-27日,14时-46分
 * Descibe
 */
public class ClientC {
    public static void main(String[] args) {
        Socket socket=null;
        try {
            socket=new Socket("localhost",10800);
            ObjectInputStream oi=new ObjectInputStream(socket.getInputStream());
            try {
                Object o= oi.readObject();
                UserBean u= (UserBean)o;
                System.out.println(u.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
