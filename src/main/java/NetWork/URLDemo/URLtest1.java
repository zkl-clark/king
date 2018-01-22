package NetWork.URLDemo;

import java.io.*;
import java.net.URL;

/**
 * Created by kingcall 2017年-08月-27日,18时-50分
 * Descibe
 */
public class URLtest1 {
    public static void main(String[] args) {
        try
        {
            URL myURL = new URL("http://www.baidu.com");

            String protocal = myURL.getProtocol();
            String host = myURL.getHost();
            String file = myURL.getFile();
            int port = myURL.getPort();
            String ref = myURL.getRef();
            System.out.println("获取连接的相关属性"+protocal + ", " + host + ", " + file + ", "
                    + port + ", " + ref);
            //InputStream is=myURL.openConnection().getInputStream();
            InputStream is=myURL.openStream();

            FileOutputStream os=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
            System.out.println(is.available());
            byte[] buffer=new byte[is.available()];
            int length=0;
            while (-1 != (length = is.read(buffer, 0, buffer.length)))
            {
                os.write(buffer, 0, length);
            }
            os.close();
        }
        catch (Exception e)
        {
            // exception handler code here
        }

    }
}
