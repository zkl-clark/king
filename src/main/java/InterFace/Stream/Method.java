package InterFace.Stream;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kingcall 2017年-08月-03日,23时-18分
 * Descibe
 */
public class Method {
    public static void main(String[] args) throws InterruptedException {
//       // List<String>collected= Stream.of ( "a" ,"asd","sdfdsf");
//        Stream<String>stringStream=Stream.of ( "a" ,"asd","sdfdsf" ).map ( s -> s.toUpperCase () );
//        stringStream.forEach ( System.out::println );
//        Thread.sleep ( 1000 );
//        Stream<String>stringStreams=Stream.of ( "a" ,"asd","sdfdsf" ).filter ( s -> s.startsWith ( "a" ) )  ;
//        stringStreams.forEach ( System.out::println );
//
//        List<Integer>together=Stream.of ( Arrays.asList(1,2) ,Arrays.asList(3,4)).flatMap ( numbers->numbers.stream () ).collect( Collectors.toList());
//        System.out.println (together.size ());
//        //min
//        List<String> list=new ArrayList<> (  );
//        list.add ( "a" );
//        list.add ( "ab" );
//        list.add ( "abc" );
//        list.add ( "asdf" );
//        String ss=list.stream ().min ( Comparator.comparing ( s -> s.length () ) ).get ();//为什么要必需要传这个接口
//        System.out.println (ss);
//
//
//        //
//        int count=Stream.of ( 1,2,3 ).reduce ( 0,(acc,ele)->acc+ele );
//        System.out.println (count);
//
//        Optional<Integer> counts=Stream.of ( 1,2,3 ).reduce ( (acc, ele)->acc+ele );
//        System.out.println (count);

//         IntStream intstream= IntStream.range ( 1,100 );
//         long acount=  intstream.filter ( i->i%2==0 ).count ();
//         System.out.println ("能被二整除的数有"+acount+"个");

/*        List<Integer> integers = new ArrayList<> ();
        integers.add ( 10 );
        integers.add ( 10 );
        integers.add ( 1 );
        integers.add ( 8 );
        integers.add ( 8 );
        integers.add ( 9 );
        integers.add ( 5 );
        integers.add ( 5 );
        for (int i : integers)
            System.out.print ( i + "   " );
        System.out.println ();
        integers.stream ().distinct ().forEach ( s-> System.out.print (s+"    ") );*/
    }
}
