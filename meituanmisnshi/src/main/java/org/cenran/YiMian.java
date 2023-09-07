package org.cenran;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-07  19:59
 * @Description: 纬创-美团-一面手写算法题_1：查出数组中的重复数字
 * @Version: 1.0
 */
public class YiMian {

    public static void main(String[] args) {

        int[] arr = {1,3,5,3,1,5,7,9,8};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                Integer value = map.get(arr[i]);
                map.put(arr[i], value + 1);
            }
            map.put(arr[i], 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println("数字" + entry.getKey() + "存在" + entry.getValue() + "个");
            }
        }

    }

}
