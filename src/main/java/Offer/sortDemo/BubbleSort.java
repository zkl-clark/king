package Offer.sortDemo;

import java.util.Random;

/**
 * @Authork:kingcall
 * @Description:
 * @Date:$time$ $date$
 */
public class BubbleSort {
    public static void main(String[] args) {
       int[] sortArray={10,1,3,8,6,2,9,7,5,4};
        int temp=0;
        for (int i = 0; i < sortArray.length-1; i++) {
            for (int j=0;j<sortArray.length-1-i;j++){
                if (sortArray[j]>sortArray[j+1]){
                    temp=sortArray[j+1];
                    sortArray[j+1]=sortArray[j];
                    sortArray[j]=temp;
                }
            }

        }
        for (int i = 0; i < sortArray.length; i++) {
            System.out.print(sortArray[i]+"\t");
        }
    }

}
