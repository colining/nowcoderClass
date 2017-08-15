package colining.course4;

/**
 * Created by colin on 2017/8/10.
 */
public class Trapping_Rain_Water {
    /*
    Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it is able to trap after raining.
    For example,
    Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */
    public static void main(String[] args) {
        int[] array = new int[]{5,5,1,7,1,1,5,2,7,6};
        System.out.println(trap(array));
        System.out.println(trap1(array));
    }

    private static int trap1(int[] arry) {
        if (arry == null || arry.length < 3) {
            return 0;
        }
        int n = arry.length - 2;
        int[] leftMaxs = new int[n];
        leftMaxs[0] = arry[0];
        for (int i = 1; i < n; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arry[i]);
        }
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arry[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arry[i+2]);
        }
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arry[i]);
        }
        return value;
    }

    public static int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int value = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 1;
        int right = height.length - 2;
        //核心思路就是比较左右的最大值，确定当前位置的瓶颈；
        //当前检查检查left 为例，它的左边有一个瓶颈，右边有一个瓶颈；
        //对于left，瓶颈的直接来源就是leftMax，如果rightMax>leftMax
        //瓶颈值就是leftMax；否则的话，就等于right的瓶颈是rightMax;

        while (left <= right) {
            if (leftMax <= rightMax) {
                value += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left++]);
            } else {
                value += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right--]);
            }
        }
        return value;
    }




}
