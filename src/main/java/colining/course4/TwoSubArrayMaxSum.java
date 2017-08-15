package colining.course4;

/**
 * Created by colin on 2017/8/15.
 */
public class TwoSubArrayMaxSum {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        func1(array);
        return;
    }

    private static int func1(int [] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] rArray = new int[arr.length];
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            cur += arr[i];
            max = Math.max(max, cur);
            rArray[i] = max;
            cur = cur < 0 ? 0 : cur;
        }
        int res = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        cur = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            res = Math.max(res, max + rArray[i + 1]);
            cur = cur < 0 ? 0 : cur;
        }
        return res;
    }

}
