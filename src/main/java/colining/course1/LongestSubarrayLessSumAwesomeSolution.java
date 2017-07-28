package colining.course1;

import java.util.HashMap;

/**
 * Created by colin on 2017/7/22.
 */
public class LongestSubarrayLessSumAwesomeSolution {

    /*
    给定一个数组，值可以为正、负和0，请返回累加和小于等于k的最长子数组长度
     */
    /*
    因为是小于等于k，我们将负值算在一起，从后向前计算，以当前值为开头，最小值是多少；
    然后记录一下区间，
     */
    public static int maxLengthAwesome(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = array.length;
        int[] sums = new int[length];
        sums[length-1] = array[length - 1];
        map.put(length - 1, length - 1);
        //sums存放的是，以每个位置开头的最小值
        //map中存放的是，最小值的开始喝结尾
        //这样负数就可以连在一起了
        for (int i = length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] += array[i] + sums[i + 1];
                map.put(i, map.get(i + 1));
            }else {
                sums[i] = array[i];
                map.put(i, i);
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        //从头开始使用双指针
        for (int i = 0; i < length; i++) {
            while (end < length && sum + sums[end] <= k) {
                sum += sums[end];
                end = map.get(end) + 1;
            }
            sum -= end > i ? array[i] : 0;
            res = Math.max(res, end - i);
            end = Math.max(end, i + 1);
        }

        return res;
    }

    /*
    原始的方法是先算出以每一个位置的和
    然后sum - k<0 说明已加入的数的和小于k，
    如果sum - k >0 我们就寻找 和为 sum -k 的位置
    然后中间剩余的部分就还是小于k的啦
      */
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 1000000; i++) {
//            int[] arr = generateRandomArray(10, 20);
//            int k = (int) (Math.random() * 20) - 5;
//            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
//                System.out.println("oops!");
//            }
//        }
        int[] array = new int[]{1, -2, -1, 5, 5, 2, -3, -2, 1, 2};
        maxLength(array,2);

    }
}
