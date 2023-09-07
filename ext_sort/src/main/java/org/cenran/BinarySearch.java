package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-07  16:47
 * @Description: TODO
 * @Version: 1.0
 */
public class BinarySearch {
    /***
     * @description 二分查找（递归实现） - 指定的 key 的索引值
     * @author 岑然
     * @return int 返回 key 的索引值
     * @param arr
     * @param key
     * @param left
     * @param right
     * @date 2023/9/7 16:55
     */
    public static int binarySearch(int[] arr, int key, int left, int right) {
        // 1. 参数校验
        if (left > right || key < arr[left] || key > arr[right]) { // 因为其本身就是大小有序的，所以 key 大于最大值或者小于最小值，就必定不存在 key
            return -1;
        }
        // 2. 对比 key 和 arr 的中间值
        int mid = (left + right) / 2;
        if (key < arr[mid]) {
            // 4. key 小，就往左递归
            return binarySearch(arr, key, left, mid - 1);
        } else if (key > arr[mid]) {
            // 3. key 大，就往右递归
            return binarySearch(arr, key, mid + 1, right);
        } else {
            // 4. 如果 key 就是 mid 的值，那就直接返回
            return mid;
        }
    }

    public static int binarySearchInWhile(int[] arr, int key, int left, int right) {
        // 1. 参数校验
        if (left > right || key < arr[left] || key > arr[right]) { // 因为其本身就是大小有序的，所以 key 大于最大值或者小于最小值，就必定不存在 key
            return -1;
        }
        // 2. 对比 key 和 arr 的中间值
        //while(low <= high){
        //    middle = (low + high) / 2;
        //    if(arr[middle] > key){
        //        //比关键字大则关键字在左区域
        //        high = middle - 1;
        //    }else if(arr[middle] < key){
        //        //比关键字小则关键字在右区域
        //        low = middle + 1;
        //    }else{
        //        return middle;
        //    }
        //}
        return 0;
    }
}
