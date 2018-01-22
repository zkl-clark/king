package Offer.sortDemo;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Authork:kingcall
 * @Description:
 * @Date:$time$ $date$
 */
public class MergeSort {
    public static void main(String[] args) {
        int len = 20;
        int[] A = new int[len];
        int[] B = new int[len];
        for (int i = 0; i < len; i++) {
            A[i] = ThreadLocalRandom.current().nextInt(100);
        }
        System.out.println(Arrays.toString(A));
        MergeSort sort = new MergeSort();
        sort.mergeSort(A, 0, len - 1, B);
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
    }
    public void mergeSort(int[] A, final int i, final int j, int[] B) {
        if (i >= j) {
            // 递归边界：此时数组中只有一个元素，无须排序
            return;
        }
        int m = i + (j - i) / 2;
        mergeSort(A, i, m, B);
        mergeSort(A, m + 1, j, B);
        int x = i;
        int y = m + 1;
        int index = i;
        while (x <= m || y <= j) {
            if (x > m || (y <= j && A[y] <= A[x])) {
                B[index++] = A[y++];
            } else {
                B[index++] = A[x++];
            }
        }
        for (int k = i; k <= j; k++) {
            A[k] = B[k];
        }
    }
}
