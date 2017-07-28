package colining.course2;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by colin on 2017/7/28.
 */
public class MountainsAndFlames {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println(getPath(array));

    }
    //创建一个新的bean，表示某个高度的山有几个；
    static class Hill {
        public int value;
        public int times;

        public Hill(int value) {
            this.value = value;
            times = 1;
        }
    }

    public static long getPath(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int maxIndex = 0;
        long result = 0L;
        int size = array.length;
        //获取最大值的位置
        for (int i = 0; i < size; i++) {
            maxIndex = array[i] > array[maxIndex] ? i : maxIndex;
        }
        Stack<Hill> stack = new Stack<>();
        stack.push(new Hill(array[maxIndex]));
        int index = getNextIndex(size, maxIndex);
        int curValue;
        //以此将数组中的元素放入栈中
        while (index != maxIndex) {
            curValue = array[index];
            //单调栈的思路：一定要是下面大，上面小；而且一个元素在弹出的时候
            //我们就得到了里它最近的两个结点；然后把times加上去
            while (!stack.isEmpty() && stack.peek().value < curValue) {
                int times = stack.pop().times;
                result += getInternalSum(times) + times * 2;
            }
            if (!stack.isEmpty() && stack.peek().value == curValue) {
                stack.peek().times++;
            }else {
                stack.push(new Hill(curValue));
            }
            index = getNextIndex(size, index);
        }

        //栈中的元素，也需要依次弹出
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            result += getInternalSum(times);
            //如果栈中不是空的，说明除了几个相等之间的对数，
            //还有与它下面元素的对数
            if (!stack.isEmpty()) {
                //不管下面有多少元素，最少对数会以此
                result += times;
                //如果栈底还有好些元素，说明左右肯定有比它高的元素
                //所以再加一会
                if (stack.size() > 1) {
                    result += times;
                }else {
                    //如果栈底就一个元素：而且这个元素也只出现了一次
                    //times== 1的话。左和右都是达到这个元素，次数只加一次
                    //否则的话就左右肯定可以找到高度相同的不同元素了
                    //也就是左右出发，会有两座不同的但是登高的山
                    result += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return result;
    }

    private static long getInternalSum(int times) {
        return times == 1L?0L:(long) times*(long)(times-1)/2L;
    }

    private static int getNextIndex(int size, int maxIndex) {
        return maxIndex == size-1 ? 0 : maxIndex + 1;
    }
}
