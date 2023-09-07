package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  14:25
 * @Description: TODO
 * @Version: 1.0
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minValue = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minValue] > arr[j]) {
                    minValue = j;
                }
            }
            if (minValue != i) {
                int temp = arr[i];
                arr[i] = arr[minValue];
                arr[minValue] = temp;
            }
        }
    }
}
