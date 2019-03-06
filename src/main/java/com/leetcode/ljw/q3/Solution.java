package com.leetcode.ljw.q3;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 难度: 难
 * 考点: 滑动窗口, 哈希表
 * <p>
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 */
public class Solution {
    /**
     * 我的答案
     * 若认为Hash表的查询时间复杂度是O(1), 则该实现复杂度为O(N)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> uniqueChars = new HashSet<>();
        char[] chars = s.toCharArray();
        int left = 0;// 左窗口
        int right = 0;// 右窗口
        // 窗口区间 [left, right] 无重复字符串
        int res = 0;
        while (right < chars.length) {// 右窗口不超边界
            if (uniqueChars.contains(chars[right])) {// 如果右窗口不可以走, 左窗口走, 维护窗内状态
                uniqueChars.remove(chars[left]);
                left++;
            } else {// 如果右窗口可以走, 右窗口走, 维护窗内状态, 尝试更新结果
                uniqueChars.add(chars[right]);
                res = Math.max(res, uniqueChars.size());
                right++;
            }
        }

        return res;
    }

    /**
     * 优化点: 如果给定s只含ascii字符, 可以使用偏移量数组代替哈希表, 相当于优化了哈希的时间复杂度.
     * 这种情况下, 左窗口还能在滑动时跳步
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_faster(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

}
