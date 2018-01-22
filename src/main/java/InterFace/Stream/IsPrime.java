package InterFace.Stream;

import java.io.InputStream;
import java.util.stream.IntStream;

/**
 * Created by kingcall 2017年-08月-02日,09时-18分
 * Descibe
 */
public class IsPrime {
    public static void main(String[] args) {
        IntStream m=IntStream.range ( 1,100000000 );
        long timestart=System.currentTimeMillis ();
        long co=m.parallel ().filter ( PrimeUtil::isPrime ).count ();
        long timeend=System.currentTimeMillis ();
        System.out.println ((timeend-timestart)+"--------->"+co);
    }
}
class PrimeUtil{
    public static boolean isPrime(int num){
        int tmp=num;
        if (tmp<2)
            return false;
        for (int i = 2; i < Math.sqrt ( tmp ); i++) {
            if (tmp%i==0)
                return  false;
        }
        return true;
    }
}
