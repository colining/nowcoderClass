package colining.course2;

import java.util.Arrays;

/**
 * Created by colin on 2017/7/27.
 */
public class MonoSum {
    /*
       现定义数组单调和为所有元素i的f(i)值之和。
       这里的f(i)函数定义为元素i左边(不包括其自身)小于等于它的数字之和。
       请设计一个高效算法，计算数组的单调和。
     */
    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,2,4,6};
        int[] result = new int[array.length];
        MonoSum monoSum = new MonoSum();
        System.out.println(monoSum.calcMonoSum(array, array.length));
    }
    public int calcMonoSum(int[] A, int n) {
        int[] result = new int[A.length];
        mergeSort(A, result, 0, n - 1);
        return sum;
    }

    int sum = 0;
    /*
    核心思想就是归并排序，因为归并排序总会比较两段排好序的序列
    那我们就可以在比较时顺便计算出小和喽
    相关问题还有逆序对的计算
     */
    private void mergeSort(int[] a, int[] result, int left, int right) {
        if (left >= right) {
            result[left] = a[left];
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(a, result, left, mid);
        mergeSort(a, result, mid + 1, right);
        int leftStart = left;
        int rightStart = mid + 1;
        int start = left;
        while (leftStart <= mid && rightStart <= right) {
            if (a[leftStart] <= a[rightStart]) {
                sum += a[leftStart] * (right - rightStart + 1);
                result[start++] = a[leftStart++];
            } else {
                result[start++] = a[rightStart++];

            }
        }
        while (leftStart <= mid) {
            result[start++] = a[leftStart++];
        }

        while (rightStart <= right) {
            result[start++] = a[rightStart++];
        }

        for (int i = left; i <= right; i++) {
            a[i] = result[i];
        }

    }


}