package com.leetcode.ljw.q_2;

/**
 * 用递归方式实现求一个正整数的阶乘
 * 难度: 易
 */
public class Solution {

    public long factorial(int n) {// n为正整数, 假设结果不会越界
        if (n < 1) {
            throw new IllegalArgumentException("n应当为正整数");
        }
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().factorial(10)
        );
    }

}
