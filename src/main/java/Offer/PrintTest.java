package Offer;

/**
 * @Authork:kingcall
 * @Description:
 * @Date:$time$ $date$
 */
public class PrintTest {
    public static void main(String[] args) {
        String[] compute={"1/2+1/6","3/4+1/2"};
        computeFZFM(compute);
    }

    public static void computeFZFM(String[] compute){
        //数组的每一行作为一个计算单元
        for (int i = 0; i < compute.length; i++) {
            String tmpcompute=compute[i];
            String[] computeArray=tmpcompute.split("\\+");
            //分子一
            int m1=Integer.valueOf(computeArray[0].split("/")[0]);
            //分母一
            int m2=Integer.valueOf(computeArray[0].split("/")[1]);
            //分子二
            int n1=Integer.valueOf(computeArray[1].split("/")[0]);
            //分母二
            int n2=Integer.valueOf(computeArray[1].split("/")[1]);
            /*最终的分子与分母*/
            int lastFZ=m1*n2+n1*m2;
            int lastFM=n2*m2;
            System.out.print(lastFZ+"/"+lastFM+"====================>>");
            int maxDivide=getMaxDivided(lastFZ,lastFM);
            System.out.println((lastFZ/maxDivide)+"/"+(lastFM/maxDivide));
        }
    }
    /*群举法求最大公约数*/
    public static int getMaxDividedByAll(int a,int b){
        int value=1;
        int max;
        int min;
        if(a==b){
            return a;
        }
        if(a>b){
            max=a;
            min=b;
        }else{
            max=b;
            min=a;
        }
        for(int i=2;i<min;i++){
            if(0==max%i && 0==min%i){
                value=i;
            }
        }
        return value;
    }
    /*辗转相除法求最大公约数*/
    public static int getMaxDivided(int a,int b){
        if(a<b){
            int temp;
            temp=a;
            a=b;
            b=temp;
        }
        if(0==b){
            return a;
        }
        return getMaxDivided(b,a%b);
    }
}
