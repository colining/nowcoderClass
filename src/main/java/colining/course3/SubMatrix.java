package colining.course3;

/**
 * Created by colin on 2017/8/10.
 */
public class SubMatrix {
    public static void main(String[] args) {
        int[][] a = new int[][]{{-10,1},{-15,24}};
        int n = 2;
        System.out.println(sumOfSubMatrix(a, 2));
    }
    public static int sumOfSubMatrix(int[][] mat, int n) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] temp = null;
        for (int i = 0; i < n; i++) {
            temp = new int[n];
            for (int j = i; j < n; j++) {
                cur = 0;
                for (int k = 0; k < n; k++) {
                    temp[k] += mat[j][k];
                    cur += temp[k];
                    max =Math.max(cur, max);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }
}