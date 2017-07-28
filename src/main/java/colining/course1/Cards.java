package colining.course1;

/**
 * Created by colin on 2017/7/20.
 */
public class Cards {
    public static void main(String[] args) {
        int testTime = 1;
        boolean err = false;
        for (int i = 0; i < testTime; i++) {
            int[] array = generateRondomArray();
            int a = bestScore1(array, array.length);
            System.out.println(a);
            int b = bestScore2(array, array.length);
            System.out.println(b);
            int c = bestScore3(array, array.length);
            System.out.println(c);
            int d = bestScore4(array, array.length);
            System.out.println(d);
            if (a != b || a != c || a != d) {
                err = true;
            }
        }
        if (err)
            System.out.println("233333");
        else
            System.out.println("666666");
//        int[] a = new int[]{1, 2, 3, 4};
//        System.out.println(bestScore3(a, a.length));
    }

    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 20) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) + 1;
        }
        return res;
    }

    /*
    总共就两个选手，我们比较先手和后手所能取得的最大值，然后就可以啦；
     */
    public int cardGame(int[] A, int n) {
        return bestScore1(A, n);
    }

    private static int bestScore1(int[] array, int n) {
        int start = 0;
        int end = array.length - 1;
//        int max = Math.max(first(array, start, end), second(array, start, end));
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        int firstScore = first(array, start, end);
        int max = Math.max(firstScore, sum - firstScore);
        return max;
    }

    /*
    这个比较难以理解，他是指当前选手为后手所能得到的最大值
    但是此时，主动权不在他，而在于先手，先手一定会拿起这样一张牌:
    使得你下次作为先手时，所能取得的最大值最小；
    假如当前start == end ，说明就剩下一张牌，然后你还是后手，
    然后你自然是拿不到牌了
     */
    private static int second(int[] array, int start, int end) {
        if (start == end) {
            return 0;
        }
        return Math.min(first(array, start + 1, end), first(array, start, end - 1));
    }

    /*
    当前选手先选的话，所能得到的最大值，
    取决于选择两侧中的哪一个+选择后作为后手时所能得到的最大值
     */
    private static int first(int[] array, int start, int end) {
        if (start == end) {
            return array[start];
        }
        return Math.max(array[start] + second(array, start + 1, end), array[end] + second(array, start, end - 1));
    }

    public static int bestScore2(int[] array, int length) {
        int start = 0;
        int end = array.length - 1;
        first1(array, start, end);
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        int firstScore = first1(array, start, end);
        int max = Math.max(firstScore, sum - firstScore);
        return max;
    }

    /*
    优化一：其实就是代码简洁了一点，因为后手还是取决于先手的值，
    所以我们就将求后手最大值删掉，只用先手函数就够了
     */
    private static int first1(int[] array, int start, int end) {
        if (start == end)
            return start;
        if (start + 1 == end)
            return Math.max(array[start], array[end]);
        return Math.max(array[start] + Math.min(first1(array, start + 2, end), first1(array, start + 1, end - 1)),
                array[end] + Math.min(first1(array, start + 1, end - 1), first1(array, start, end - 2)));
    }

    /*
     有暴力递归反推出填表的顺序，将其转换为两张表互相填写的过程
     写表的时候是从下往上写的，不是从左到右，代码看起来比较难懂；
     ps：一定要注意数组的横纵坐标，以及start，end
     */
    public static int bestScore3(int[] array, int length) {
        int[][] first = new int[length][length];
        int[][] second = new int[length][length];
        for (int end = 0; end < length; end++) {
            first[end][end] = array[end];
            for (int start = end - 1; start >= 0; start--) {
                first[start][end] = Math.max(array[start] + second[start + 1][end], array[end] + second[start][end - 1]);
                second[start][end] = Math.min(first[start + 1][end], first[start][end - 1]);
            }
        }
        return Math.max(first[0][length - 1], second[0][length - 1]);
    }

    /*
    优化上一个动态规划，两张表，变成一张表；
    由于在一张表时，是隔着依赖的，所以我们一开始要初始化对角线，和它右上方的那条斜线
     */
    public static int bestScore4(int[] array, int length) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            return Math.max(array[0], array[1]);
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        int[][] first = new int[length][length];
        for (int i = 0; i < array.length - 1; i++) {
            first[i][i] = array[i];
            first[i][i + 1] = Math.max(array[i], array[i + 1]);
        }
        first[length - 1][length - 1] = array[length - 1];
        for (int k = 2; k < length; k++) {
            for (int end = k; end < length; end++) {
                int start = end - k;
                first[start][end] = Math.max(array[start] + Math.min(first[start + 2][end], first[start + 1][end - 1]),
                        array[end] + Math.min(first[start + 1][end - 1], first[start][end - 2]));
            }

        }
        return Math.max(first[0][length - 1], sum - first[0][length - 1]);
    }
}
