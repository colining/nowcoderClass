package colining.course4;

import java.util.Scanner;

/**
 * Created by colin on 2017/8/15.
 */
public class SubMatrixMaxSum {
    /*
    最大矩阵和，在一个大矩阵中，找到一个和最大的小矩阵
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[][] array = new int[n][n];
            for (int j = 0; j < n * n; j++) {
                array[j / n][j % n] = scanner.nextInt();
            }
            func1(array);
        }
    }
    //最大字段和的变形，代码比较难写的说
    private static void func1(int[][] array) {
        int max = Integer.MIN_VALUE;
        int[] helper = null;
        int cur = 0;
        //从哪一行开始
        for (int i = 0; i < array.length; i++) {
            helper = new int[array[0].length];
            //从哪一行结束
            for (int j = i; j < array.length; j++) {
                cur = 0;
                //对于某几行的和求最大字段和
                //helper用于保存某一列的值
                for (int k = 0; k < helper.length; k++) {
                    helper[k] += array[j][k];
                    cur += helper[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        System.out.println(max);
    }
}
