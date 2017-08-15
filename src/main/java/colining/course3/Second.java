package colining.course3;

/**
 * Created by colin on 2017/8/10.
 */
public class Second {
    public static void main(String[] args) {
        int[] array = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(array));
    }
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax =0;
        int rightMax = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                }else {
                    res += Math.abs(leftMax - height[left]);
                }
                left++;
            }else {
                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    }else {
                        res += Math.abs(rightMax - height[right]);
                    }

                right--;
            }
        }
        return res;
    }
}
