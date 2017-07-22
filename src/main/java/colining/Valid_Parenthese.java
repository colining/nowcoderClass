package colining;

import java.util.Stack;

/**
 * Created by colin on 2017/7/22.
 */
public class Valid_Parenthese {
    /*
    三种不同的括弧，使用栈会比较的快
     */
    public static void main(String[] args) {
        System.out.println(isValid("(){}[(]"));
        String str1 = "((())())";
        System.out.println(isValid1(str1));


        String str2 = "(())(()(()))";
        System.out.println(isValid1(str2));

        String str3 = "()";
        System.out.println(isValid1(str3));
        System.out.println(maxLength(str3));
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()&&isPair(stack.peek(), s.charAt(i))) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    private static boolean isPair(Character pop, char c) {
        if (pop == '}' || pop == ']' || pop == ')') {
            return false;
        } else if (pop == '{' && c == '}') {
            return true;
        } else if (pop == '(' && c == ')') {
            return true;
        } else if (pop == '[' && c == ']') {
            return true;
        }
        return false;
    }


    public static boolean isValid1(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        int sum = 0;
        for (char c : string.toCharArray()) {
            if (c == '(') {
                sum++;
            } else if (c == ')') {
                sum--;
            }
            if (sum < 0) {
                return false;
            }
        }
        return sum == 0;
    }

    /*
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    For "(()", the longest valid parentheses substring is "()", which has length = 2.

    Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */

    /*
    字符串的典型提醒，给定某个条件，让求最长；
    抓住一个核心，通过一个dp数组，记录以每个位置为止的
    最长子串，后面的子串取决于前面的，可以通过dp数组得到
    用例参考：()(()())
     */
    public static int maxLength(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        char[] chars = string.toCharArray();
        int[] dp = new int[string.length()];
        int pre;
        int max = 0;
        //只有一个括弧，不可能成对，所以从1开始记录
        for (int i = 1; i < chars.length; i++) {
            //当当前字符为 ),可能会有合理子串的产生
            if (chars[i] == ')') {
                //寻找与当前)对应的位置，减去dp[i - 1]
                //是因为可能会出现((()()))这样的情况
                pre = i - dp[i - 1] - 1;
                //若与之对应的恰好也是(,说明子串合理，然后计算长度
                if (pre >= 0 && chars[pre] == '(') {
                    //长度构成是：新加的这组括弧长度 2
                    //)前一个括弧所构成的合理子串长度
                    //与之对应的(的前面也可能是合理的；
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
