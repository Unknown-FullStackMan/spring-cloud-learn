package com.simple.myhystrix.aspect;

import com.simple.myhystrix.model.Fuse;
import com.simple.myhystrix.model.HystrixStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author Simple
 * @Date 2023/3/20
 */

@Component
@Aspect
public class HystrixAspect {


    //一个消费者可以有多个提供者，每个提供者都有自己的断路器
    public static Map<String , Fuse> fuseMap = new HashMap<>();

    static {
        fuseMap.put("order-service", new Fuse());
    }

    @Around(value = "@annotation(com.simple.myhystrix.annotion.MyHystrix)")
    public Object HystrixAround(ProceedingJoinPoint joinPoint) {

        //获取到当前提供者的断路器
        Object result = null;
        Fuse fuse = fuseMap.get("order-service");
        HystrixStatus status = fuse.getStatus();
        switch (status){
            case CLOSE:
                //正常调用 执行目标方法
                try {
                    return  joinPoint.proceed();
                } catch (Throwable throwable) {
                    fuse.addFailCount();
                    return  "fallback";
                }

            case OPEN:
                return  "fallback";
            case HALF_OPEN:
                //可以少许流量调用
                int i = new Random().nextInt(5);
                System.out.println(i);

                if ( i ==1 ) {
                    try {
                        result = joinPoint.proceed();
                        fuse.setStatus(HystrixStatus.CLOSE);
                        synchronized (fuse.getLock()) {
                            fuse.getLock().notifyAll();
                        }
                        return result;
                    } catch (Throwable throwable) {
                        return  "fallback";
                    }
                }
            default:
                return  "fallback";

        }

    }
}
