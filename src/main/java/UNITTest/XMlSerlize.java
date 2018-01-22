package UNITTest;

/*import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by kingcall 2017年-08月-27日,22时-43分
 * Descibe
 */
public class XMlSerlize {
    public static void main(String[] args) {
        UserBean userBean1=new UserBean (  );
        userBean1.setId ( "123" );
        userBean1.setType ( "UserBean" );
        userBean1.setUsername ( "黄生" );
        userBean1.setPassword ( "1234" );

      /*  XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(UserBean.class);
        FileOutputStream fo=null;
        try {
            fo=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\1.txt");
            xs.toXML(userBean1,fo);
            String s=xs.toXML(userBean1);
            System.out.println(s);
            userBean1=null;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fis =null;
        try {
            fis= new FileInputStream("C:\\Users\\Administrator\\Desktop\\1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(userBean1);
        xs.fromXML(fis, userBean1);
        System.out.println(userBean1);*/

    }

}
