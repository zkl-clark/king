package InterFace.Stream;

import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kingcall 2017年-08月-03日,17时-15分
 * Descibe
 */
public class StreamDemo {
    //支持顺序和并行聚合操作的一系列元素
    //集合和流动，同时具有一些表面上的相似之处，具有不同的目标。 集合主要关注其元素的有效管理和访问。 相比之下，流不提供直接访问或操纵其元素的
    //手段，而是关心声明性地描述其源和将在该源上进行聚合的计算操作。
    public static void main(String[] args) {
        String[] strings=new String[]{"abc","sdf","fdds","dsfsdff"};
        //匹配此流中的所有元素是否全部满足条件，其中allMatch()方法里面的形成了一个接口predict
        System.out.println (Arrays.stream ( strings ).allMatch ( s->s.length ()>2 ));
        Predicate<String> predicate=new Predicate<String> () {
            @Override
            public boolean test(String s) {
               return s.length ()>2;
            }
        };
        System.out.println (Arrays.stream ( strings ).allMatch ( predicate ));
        //可以看出两者的效果是一模一样的，当然也有一个对应的方法anyMatch(),只要有一个满足即可
        /**
         * 统计流中的元素个数
         * */
        System.out.println (Arrays.stream ( strings ).count ());

        Function<String,String>sFunction=new Function<String, String> () {
            @Override
            public String apply(String s) {
                return s+"中国人";
            }
        };
       Arrays.stream ( strings ).forEach ( s->sFunction.apply ( s ).length ());
       //流中集合的顺序
       List<Integer> nums=Arrays.asList ( 1,2,3,4 );
       List<Integer>same=nums.stream ().collect( Collectors.toList());
       System.out.println (same);
       Set<Integer> numbers=new HashSet<> ( Arrays.asList (8,1,2,3,4,5,6 ) );
       List<Integer>sames=numbers.stream ().collect( Collectors.toList());//将set直接转换成了List,在java中这是没有的List<Integer>s=new ArrayList<> ( numbers );
       //Collectors.toXXX()任何你想要的集合类型都可以
        System.out.println (sames);


    }
}
