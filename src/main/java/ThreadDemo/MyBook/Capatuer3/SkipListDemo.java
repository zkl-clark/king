package ThreadDemo.MyBook.Capatuer3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingcall 2017年-08月-03日,20时-54分
 * Descibe 跳表的演示事例
 */
public class SkipListDemo {
    public static void main(String[] args) {
        Map<Integer,Integer>IntegerMap=new HashMap<> ( 10 );
        for (int i=0;i<10;i++){
            IntegerMap.put ( i,i );
        }
        for (Integer i:IntegerMap.keySet ())
            System.out.println (IntegerMap.get ( i ));
    }
}
