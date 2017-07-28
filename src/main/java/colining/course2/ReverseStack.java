package colining.course2;

import java.util.Stack;

/**
 * Created by colin on 2017/7/27.
 */
public class ReverseStack {
    /*
        逆序一个栈，只能使用递归函数
     */
    public static void main(String[] args) {
        ReverseStack reverseStack = new ReverseStack();
        int[] array = new int[]{1, 2, 3, 4, 5};
        reverseStack.reverseStackRecursively(array, array.length);
    }

    /**
        牛客的oj，我就还是自己造了个stack
     */
    public int[] reverseStackRecursively(int[] stack, int top) {
        Stack<Integer> myStack = new Stack<>();
        for (int i = top - 1; i >= 0; i--) {
            myStack.push(stack[i]);
        }
        reverseStack(myStack);
        for (int i = 0; i < stack.length; i++) {
            stack[i] = myStack.pop();
        }
        return stack;
    }

    /*
    核心思想：把栈底元素拿出，保留下来，然后递归；
    达到递归结束条件时，返回，并压入保留的值；
    这样最先拿出来的，最后压入，就逆序了
     */
    private void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = get(stack);
        reverseStack(stack);
        stack.push(last);
    }
    /*
    我们先考虑没有为空这个判断，函数也可正常运行，
    那就是把元素一个一个拿出来，再一个一个放进去；
    我们先在加了为空的判断，说最后一个你别再压进去了；
    然后向上传递这个值
     */
    private int get(Stack<Integer> stack) {
        int a = stack.pop();
        if (stack.isEmpty()) {
            return a;
        }
        int last = get(stack);
        stack.push(a);
        return last;
    }

}