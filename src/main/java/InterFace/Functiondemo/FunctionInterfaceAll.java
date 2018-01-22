package InterFace.Functiondemo;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Comparator;
import java.util.function.*;
import java.util.stream.Stream;

import static java.util.stream.Stream.*;

/**
 * Created by kingcall 2017年-08月-01日,12-12
 */
public class FunctionInterfaceAll {
    public static void main(String[] args) {
        //Runable
        //Comparable
        //Comparator

        //Function函数接口
        int num=10;//要求申明为常亮的，但是不知道为什么可以这样做,一旦你尝试修改它的时候，就会提示错误
        Function<Integer,Integer> Convert=(from)->from*num;
        System.out.println (Convert.apply ( 4 ));//将function对象应用到输入的参数上，并且输出结果
        Function<Integer,String> ConvertString=(from)->"我是中国人"+from.toString ();
        //
        Consumer<String> consumer=new Consumer<String> () {
            @Override
            public void accept(String s) {
                s=s+"10";
            }
        };
        String m="10";
        consumer.accept ( m );
        System.out.println (m);

        //
        Comparator<String> sc=new Comparator<String> () {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };

        //
        Function<String,String> function = (x) -> {System.out.print(x+": ");return "Function";};
        function.apply("中国人");
        System.out.println(function.apply("hello world"));

        //Predicate<T> -T作为输入，返回的boolean值作为输出
        Predicate<String> pre = (x) ->{System.out.print(x);return false;};
        System.out.println(": "+pre.test("hello World"));

        //Consumer<T> - T作为输入，执行某种动作但没有返回值
        Consumer<String> con = (x) -> {System.out.println(x+x);};
        con.accept("hello world");

        //Supplier<T> - 没有任何输入，返回T
        Supplier<String> supp = () -> {return "Supplier";};
        System.out.println(supp.get());



        //BinaryOperator<T> -两个T作为输入，返回一个T作为输出，对于“reduce”操作很有用
        BinaryOperator<String> bina = (x, y) ->{System.out.print(x+" "+y);return "BinaryOperator";};
        System.out.println("  "+bina.apply("hello ","world"));
        Runnable t= ()->System.out.println ("我就是这么任性");
        new Thread ( ()-> System.out.println ("我更加任性了") ).start ();
        //源码
        BinaryOperator<String> bs=new BinaryOperator<String> () {
            @Override
            public String apply(String s, String s2) {
                return s+s2;
            }
        };
        System.out.println (bs.apply ( "你好","黄胜" ));

    }
}
