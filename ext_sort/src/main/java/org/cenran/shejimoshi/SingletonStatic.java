package org.cenran.shejimoshi;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran.shejimoshi
 * @Author: 岑然
 * @CreateTime: 2023-09-07  19:10
 * @Description: TODO
 * @Version: 1.0
 */
public class SingletonStatic {

    private static class SingletonHolder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
