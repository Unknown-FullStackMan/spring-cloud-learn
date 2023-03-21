package com.simple.myhystrix.model;

import lombok.Data;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Simple
 * @Date 2023/3/21
 * 断路器模型
 */
@Data
public class Fuse {


    /**
     * 窗口时间
     */
    public static final Integer WINDOW_TIME = 20;

    /**
     * 最大失败次数
     */
    public static final Integer MAX_FAIL_COUNT = 3;

    /**
     * 断路器中自己的状态
     */
    private  HystrixStatus status = HystrixStatus.CLOSE;

    /**
     * 当前断路器失败的次数
     */
    private AtomicInteger currentFailCount = new AtomicInteger(0);


    private Object lock = new Object();

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4,
            8,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );


    {
        poolExecutor.execute(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(this.status.equals(HystrixStatus.CLOSE)) {
                    //清零
                    this.currentFailCount.set(0);
                }else {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 记录失败次数
     */
    public void addFailCount() {
        int i  =currentFailCount.incrementAndGet();
        if( i >= MAX_FAIL_COUNT ) {
            //说明失败次数已到达阈值
            //修改当前状态为opne
            this.setStatus(HystrixStatus.OPEN);

            // 当断路器打开时，不能访问， 需要将其半开
            // 等待一个窗口时间 让断路器状态变成半开

            poolExecutor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.setStatus(HystrixStatus.HALF_OPEN);
                this.currentFailCount.set(0);
            });
        }
    }
}
