package org.cenran.shejimoshi;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran.shejimoshi
 * @Author: 岑然
 * @CreateTime: 2023-09-07  19:05
 * @Description: TODO
 * @Version: 1.0
 */
public class SingletonDemo {
    private volatile static SingletonDemo singletonDemo;

    public SingletonDemo() {
    }

    public static SingletonDemo getSingletonDemo() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {

                if (singletonDemo == null) {

                    singletonDemo = new SingletonDemo();

                }

            }
        }
        return singletonDemo;
    }
}
