package InterFace.Lambda;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.File;
import java.io.FileFilter;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/31.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        new Thread ( new Runnable () {
            @Override
            public void run() {
                System.out.println ("你好");
            }
        } ).start ();
        new Thread ( ()->System.out.println ("你好")).start ();
        //怎么他in家具体的参数
        Runnable t = ()-> System.out.println ("你好");
        new Thread ( t ).start ();
        FileFilter java = (File f) ->  f.getName().endsWith("*.java");

    }
}
