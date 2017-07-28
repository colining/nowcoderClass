package colining.course2;

/**
 * Created by colin on 2017/7/27.
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] a = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(a));
    }
    public static int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int sum=0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(sum, max);
            sum = sum < 0 ? 0 : sum;
        }
        return max;
    }
}
