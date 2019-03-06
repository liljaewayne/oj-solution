package com.leetcode.ljw.q349;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 * 修改了原题, 免去自动拆装箱问题
 * 难度: 易(描述) / 中(实现)
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Solution {
    public Set<Integer> intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int val : nums1) {
            set.add(val);// 遍历第一个数组, 放到查找表中, 且已去重
        }
        for (int val : nums2) {// 遍历第二个数组
            if (set.contains(val)) {// 如果第一个数组已包含当前数, 放到结果集中
                res.add(val);
            }
        }
        return res;
    }

    /*
     * 扩展: 重复的次数要体现, 见下面描述
     * 即350题: https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     */

    public static void main(String[] args) {
        System.out.println(new Solution().intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
    }
}