package NetWork.ObjectDemoSend;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kingcall 2017年-08月-27日,14时-45分
 * Descibe
 */
public class ServerC {
    public static void main(String[] args) {
        ServerSocket serverSocket =null;
        Socket socket=null;
        UserBean userBean2=new UserBean(  );
        userBean2.setId ( "1234" );
        userBean2.setType ( "UserBean" );
        userBean2.setUsername ( "刘文强" );
        userBean2.setPassword ( "1234" );
        try {
            serverSocket=new ServerSocket(10800);
            socket=serverSocket.accept();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(userBean2);
            objectOutputStream.flush();
            System.out.println("发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
