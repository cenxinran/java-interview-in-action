package org.cenran;

import java.security.PublicKey;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * 第一题
 * 编写一个Java函数，通过调用AService.get()、BService.get()、CService.get()三个接口，获取三个整数，然后将这三个整数累加，最终返回累加的值。要求：
 * 1.调用三个接口的操作需要并行执行，以提高效率；
 * 2.累加操作需要在获取三个整数的操作完成后进行，因此需要保证三个整数均已获取后才能进行累加操作；
 * 3.考虑多线程安全问题。
 */

/**
 * @BelongsProject: ${PROJECT_NAME}
 * @BelongsPackage: org.cenran
 * @Author: ${USER}
 * @CreateTime: ${YEAR}-${MONTH}-${DAY}  ${HOUR}:${MINUTE}
 * @Description: 纬创-美团-笔试_1
 * @Version: 1.0
 */
public class BiShi_1 {
    public static void main(String[] args) {
        // 1. 创建线程池
        // 1.1 准备参数
        /* 核心线程数 */
        int corePoolSize = 3;
        /* 最大线程数量 */
        int maxPoolSize = 6;
        /* 空闲线程存活时间 */
        long keepAliveTime = 10;
        /* 时间单位 */
        TimeUnit unit = TimeUnit.SECONDS;
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        /* 线程工厂 */
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        /* 拒绝策略 */
        MyIgnorePolicy myIgnorePolicy = new MyIgnorePolicy();
        // 1.2创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize, keepAliveTime, unit,
                workQueue, myThreadFactory, myIgnorePolicy);
        // 1.3 预启动核心线程，提高工作效率
        executor.prestartAllCoreThreads();
        // 1.4 添加任务
        // 1.4.1 创建一个计数器
        CountDownLatch countService = new CountDownLatch(3);
        try {
            // 1.4.2 并发执行各个任务
            Future futureA = executor.submit(new myTaskA(countService));
            Future futureB = executor.submit(new myTaskB(countService));
            Future futureC = executor.submit(new myTaskC(countService));
            // 1.4.3 计数器等待
            countService.await(4, TimeUnit.SECONDS);
            // 1.5 执行 sum 任务
            Integer getA = (Integer) futureA.get();
            Integer getB = (Integer) futureB.get();
            Integer getC = (Integer) futureC.get();
            int sum = getA + getB + getC;
            System.out.println("sum = " + sum);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 1.5 关闭线程池
            executor.shutdown();
        }
    }

    // 任务 A
    static class myTaskA implements Callable {

        //private AService aService;
        AService aService = new AService();
        private CountDownLatch countDownLatch;

        public myTaskA(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            return aService.get();
        }
    }

    // 任务 B
    static class myTaskB implements Callable {

        //private BService bService;
        BService bService = new BService();

        private CountDownLatch countDownLatch;

        public myTaskB(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Object call() throws Exception {
            return bService.get();
        }
    }

    // 任务 C
    static class myTaskC implements Callable {

        //private CService cService;
        CService cService = new CService();
        private CountDownLatch countDownLatch;

        public myTaskC(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Object call() throws Exception {
            return cService.get();
        }
    }

    // 线程工厂
    static class MyThreadFactory implements ThreadFactory {
        private AtomicInteger threadId = new AtomicInteger(0);
        /* 线程名前缀 */
        private String threadNamePrefix;

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(threadNamePrefix + "-sum-" + threadId.getAndIncrement());
            return thread;
        }
    }

    // 拒绝策略
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            doLog(runnable, executor);
        }

        private void doLog(Runnable runnable, ThreadPoolExecutor executor) {
            // 记录日志信息等的实现
            System.err.println(executor.toString() + runnable.toString() + " rejected");
        }
    }

    public interface Service {
        int get();
    }

    public static class AService implements Service {
        @Override
        public int get() {
            // get 方法的具体实现，此处直接 return 1 作为演示
            return 1;
        }
    }

    public static class BService implements Service {
        @Override
        public int get() {
            // get 方法的具体实现，此处直接 return 2 作为演示
            return 2;
        }
    }

    public static class CService implements Service {
        @Override
        public int get() {
            // get 方法的具体实现，此处直接 return 3 作为演示
            return 3;
        }
    }
}