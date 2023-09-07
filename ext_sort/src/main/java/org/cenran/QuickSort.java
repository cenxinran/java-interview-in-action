package org.cenran;

import jdk.vm.ci.meta.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-07  15:00
 * @Description: TODO
 * @Version: 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    /***
     * @description 快速排序算法
     * @author 岑然
     * @return void
     * @param arr
     * @param left
     * @param right
     * @date 2023/9/7 16:24
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        // 1. 确定基准位
        int temp = arr[left];
        // 2. 双指针往中间夹，遇到比基准大的往右，比基准小的往左
        int i = left;
        int j = right;
        int t; // 为交换准备的缓存
        while (i < j) {
            // 2.1 从右往左，大于 temp 的忽略，小于 temp 就要停止，等待交换（换到左边去）
            while (arr[j] >= temp && i < j) {
                j--;
            }
            // 2.2 从左往右，小于 temp 的忽略，大于 temp 就要停止，等待交换（换到右边去）
            while (arr[i] <= temp && i < j) {
                i++;
            }
            // 2.3 左右两边都找到了需要交换的元素，并且都在停着等待交换。开始交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
            // 备注：交换完了之后，双指针又可以继续往中间夹逼了，也就是继续走外层 while 循环，直到双指针相遇
        }
        // 3. 直到左右指针相遇，将指针位与基准位互换
        // 备注：双指针相遇的位置，其实就是基准位需要存放到的位置
        arr[left] = arr[i];
        arr[i] = temp;
        // 4. 递归的快排基准位的左右两段
        quickSort(arr, left, j - 1);
        quickSort(arr, j + 1, right);
    }
}
