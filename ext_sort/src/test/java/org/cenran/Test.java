package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.ceneran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  14:17
 * @Description: TODO
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {3, 5, 9, 4, 8, 1, 6, 7, 2};
        //BubbleSort.bubbleSort(arr);
        SelectionSort.selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
