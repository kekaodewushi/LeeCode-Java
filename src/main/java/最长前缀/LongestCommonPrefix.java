package 最长前缀;

/**
 * Created by zangyaoyi on 2020/6/15.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flightow", "flightasow"};
        System.out.println("结果:" + longestCommonPrefix4(strs));

    }

    /**
     * 水平扫描法（遍历所有字符串）
     */
    public static String longestCommonPrefix1(String[] strs) {
        //防止数组空
        if (strs.length == 0) {
            return "";
        }
        //用来作为返回公缀使用
        //数组数量不固定，也没有next方法，只能与上一个进行比较
        //数组第一个元素直接给公缀赋值
        String last = strs[0];

        //遍历数组,与上一个进行比较，所以从第二个开始
        for (int a = 1; a <= strs.length; a++) {
            //字符串操作方便
            StringBuffer common = new StringBuffer();

            //字符串遍历操作
            char[] ca = last.toCharArray();
            char[] cb = strs[a].toCharArray();
            //以短的为准，防止越界
            int length = ca.length < cb.length ? ca.length : cb.length;
            for (int i = 0; i < length; i++) {
                //因为需求是前缀，第一个都不同后面就免了
                if (i == 0 && ca[i] == cb[i]) {
                    common.append(ca[i]);
                }
                //防止出现间隔相同这种情况，如： abc ,adc这种。
                else if (ca[i] == cb[i] && common.length() == i) {
                    common.append(ca[i]);
                } else {
                    break;
                }
            }
            //赋值
            last = common.toString();

        }
        return last;
    }

    /**
     * 分治法（先遍历前半部分，后遍历后半部分）
     */
    public static String longestCommonPrefix2(String[] strs) {
        //防止数组空
        if (strs.length == 0) {
            return "";
        }
        //用来作为返回公缀使用
        //数组数量不固定，也没有next方法，只能与上一个进行比较
        //数组第一个元素直接给公缀赋值
        String last = commonPrefix(strs, 0, strs.length / 2);
        if (last.equals("")) {
            return "";
        }
        String next = commonPrefix(strs, strs.length / 2, strs.length - 1);

        return commonPrefix(new String[]{last, next}, 0, 1);
    }

    /**
     * 二分法官方（找到最短的字符串，分为两个字符串，分别与所有字段比较）
     */
    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public static boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int minChar(String[] strs) {
        String minChar = strs[0];
        for (String str : strs) {
            if (str.length() < minChar.length()) {
                minChar = str;
            }
        }
        return minChar.length();
    }

    public static String commonPrefix(String[] strs, int begin, int end) {
        String last = strs[begin];
        //遍历数组,与上一个进行比较，所以从第二个开始
        for (int a = begin + 1; a <= end; a++) {
            //字符串操作方便
            StringBuffer common = new StringBuffer();

            //字符串遍历操作
            char[] ca = last.toCharArray();
            char[] cb = strs[a].toCharArray();
            //以短的为准，防止越界
            int length = ca.length < cb.length ? ca.length : cb.length;
            for (int i = 0; i < length; i++) {
                //因为需求是前缀，第一个都不同后面就免了
                if (i == 0 && ca[i] == cb[i]) {
                    common.append(ca[i]);
                }
                //防止出现间隔相同这种情况，如： abc ,adc这种。
                else if (ca[i] == cb[i] && common.length() == i) {
                    common.append(ca[i]);
                } else {
                    break;
                }
            }
            //赋值
            last = common.toString();
        }
        return last;
    }

}
