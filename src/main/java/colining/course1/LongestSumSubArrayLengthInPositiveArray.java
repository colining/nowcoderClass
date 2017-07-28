package colining.course1;

import java.util.HashMap;

/**
 * Created by colin on 2017/7/22.
 */
public class LongestSumSubArrayLengthInPositiveArray {

    /*
    如果全都是正数，求和为k的最大子数组长度，就是双指针法
     */
    public static int getMaxLength(int[] array,int k) {
        int start = 0;
        int end = 0;
        int sum = array[start];
        int maxLength = 0;
        while (end < array.length) {
            if (sum == k) {
                maxLength = Math.max(maxLength, end - start + 1);
                sum -= array[start++];
            } else if (sum < k) {
                end++;
                if (end == array.length) {
                    break;
                }
                sum += array[end];
            } else if (sum > k) {
                sum -= array[start++];
            }
        }
        return maxLength;
    }


    public static int getMaxLength1(int[] array, int k) {
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (map.containsKey(sum - k)) {
                maxLength = Math.max(i - map.get(sum - k), maxLength);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
//        int[] arr = new int[]{0,0,0,0,0,0};
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength1(arr, k));

    }
}
