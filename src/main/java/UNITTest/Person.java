package UNITTest;

import java.io.*;


/**
 * Created by kingcall 2017年-08月-06日,10时-54分
 * Descibe
 */
public class Person {
    public static void main(String[] args) {
        UserBean userBean1=new UserBean (  );
        userBean1.setId ( "123" );
        userBean1.setType ( "UserBean" );
        userBean1.setUsername ( "黄生" );
        userBean1.setPassword ( "1234" );

        UserBean userBean2=new UserBean (  );
        userBean2.setId ( "1234" );
        userBean2.setType ( "UserBean" );
        userBean2.setUsername ( "刘文强" );
        userBean2.setPassword ( "1234" );


        //将连个对象进行输出
        ObjectOutputStream objectOutputStream=null;
        try {
            FileOutputStream fo=new FileOutputStream ( "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt" ,true);
            objectOutputStream=new ObjectOutputStream ( fo );
            long pos=fo.getChannel().position()-4;//追加的时候去掉头部aced 0005
            fo.getChannel().truncate(pos);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        try {
            objectOutputStream.writeObject ( userBean1 );
            objectOutputStream.writeObject ( userBean2 );
        } catch (IOException e) {
            e.printStackTrace ();
        }



        //将对象那个进行输入
        ObjectInputStream oi=null;
        try {
            oi=new ObjectInputStream ( new FileInputStream ( "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt" ) );
        } catch (IOException e) {
            e.printStackTrace ();
        }

        userBean1=null;
        userBean2=null;
        UserBean userBean3=null;
        try {
           userBean1= (UserBean) oi.readObject ();
           userBean2= (UserBean) oi.readObject ();
           //userBean3= (UserBean) oi.readObject ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
        System.out.println (userBean1.getId ());
        System.out.println (userBean2.getId ());
        System.out.println (userBean1.getUsername());
        System.out.println (userBean2.getUsername());

    }
}
