package colining;

import java.util.HashMap;

/**
 * Created by colin on 2017/7/22.
 */
public class LongestSumSubArrayLength {

    /*
    如果数组中可以是负数，正数，0，求和为k的最大子数组长度；
    核心就是连续求和时，sum之间如果正好相差为k，那么中间的子数组和就是k
     */
    public static int getMaxLength(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLength = 0;
        int sum = 0;
        map.put(0, -1);
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
    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
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
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(getMaxLength(arr, 10));

    }
}
