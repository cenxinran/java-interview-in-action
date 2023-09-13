package org.cenran;

import java.util.concurrent.Executors;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-11  20:26
 * @Description: TODO
 * @Version: 1.0
 */
public class _02 {

    //public static void main(String[] args) {
    //
    //    Foo foo = new Foo();
    //    Executors.newCachedThreadPool().execute({
    //            foo.first();
    //    });
    //
    //}

    public class Foo {
        public void first() {
            System.out.println("first");
        }

        public void second() {
            System.out.println("second");
        }

        public void third() {
            System.out.println("third");
        }
    }

}
