package InterFace.Lambda;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by kingcall 2017年-08月-01日,09-50
 */
public class Interlambda {
    public static void main(String[] args) {
        //每个 Lambda 表达式都能隐式地赋值给函数式接口，例如，
        // 我们可以通过 Lambda 表达式创建 Runnable 接口的引用。
        //这里t,就是引用
//        Runnable t=(Socket so)->{
//            try {
//                OutputStream out=so.getOutputStream ();
//            } catch (IOException e) {
//                e.printStackTrace ();
//            }
//
//        };
        //当不指明函数式接口时，编译器会自动解释这种转化：
        new Thread ( ()->System.out.println ("你好") );

    }
    //声明了一个函数接口
    @FunctionalInterface
    public interface  work{
        public  void dowork() ;
    }
}

