package Offer.sortDemo;

/**
 * @Authork:kingcall
 * @Description:
 * @Date:$time$ $date$
 */
public class SelectSort {
    public static void main(String[] args) {
        int temp=0;
        int[] sortArray={10,1,3,8,6,2,9,7,5,4};
        for (int i = 0; i < sortArray.length; i++) {
            int minindex=i;
            for (int j=i+1;j<sortArray.length;j++){
                if (sortArray[j]<sortArray[minindex]){
                    minindex=j;
                }
            }
            temp=sortArray[i];
            sortArray[i]=sortArray[minindex];
            sortArray[minindex]=temp;
        }
        for (int i = 0; i < sortArray.length; i++) {
            System.out.print(sortArray[i]+"\t");
        }
    }
}
