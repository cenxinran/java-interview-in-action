package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-06  14:10
 * @Description: TODO
 * @Version: 1.0
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            boolean flag = true;
            for (int j = 0; j < length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;

                    flag = false;

                }

            }
            if (flag) {
                break;
            }

        }

    }
}
