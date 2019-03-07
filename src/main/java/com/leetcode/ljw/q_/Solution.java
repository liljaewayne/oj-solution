package com.leetcode.ljw.q_;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 给定一个很大的数组, 求出其中前K大的元素
 * 比如, 一亿个整数中取前一万大的数 (第一万大的数同理)
 */
public class Solution {
    private static final int n = 1_0000_0000;
    private static final int m = 1_0000;

    /**
     * 要求, 复杂度优于NlogN, 实际上复杂度是NlogM
     *
     * @param nums
     * @return
     */
    public Integer[] topK(int[] nums) {

        // 最小堆, 存一万个数
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(m);

        for (int i = 0; i < nums.length; i++) {
            while (minHeap.size() < m) {
                minHeap.add(nums[i]);
            }

            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.toArray(new Integer[m]);
    }

    /**
     * 测试方法, 忽略
     *
     * @param args
     */
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {// 准备数据, 先生成有序数组, 再随机乱序
            nums[i] = i;
        }

        for (int i = 0; i < n; i++) {// 乱序
            int x = random.nextInt(n);
            int y = random.nextInt(n);

            // swap
            int tmp = nums[x];
            nums[x] = nums[y];
            nums[y] = tmp;
        }

        Integer[] ints = new Solution().topK(nums);

        System.out.println(Arrays.toString(ints));
    }

}
