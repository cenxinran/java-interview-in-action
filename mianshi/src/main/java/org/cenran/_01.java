package org.cenran;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-11  20:15
 * @Description: TODO
 * @Version: 1.0
 */
public class _01 {

    public class Singleton {
        private Singleton singleton;

        public synchronized Singleton getSingleton() {
            if (singleton == null) {
                return new Singleton();
            }
            return singleton;
        }
    }


}
