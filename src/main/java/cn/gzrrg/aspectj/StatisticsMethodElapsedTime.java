package cn.gzrrg.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 *  aop统计方法运行耗时
 * @author huangsz  2018/7/25 0025
 */
@Component
@Aspect
public class StatisticsMethodElapsedTime {

    private Logger log = LoggerFactory.getLogger(StatisticsMethodElapsedTime.class);

    /**
     * 声明切入点
     */
    @Pointcut("within(cn.gzrrg.service.SomeService)")
    public void pointcut(){

    }

    /**
     * stopWatch.start() 开始计时, 然后通过 .proceed() 来调用我们实际的服务方法,
     * 当调用结束后, 通过 stopWatch.stop() 来结束计时.
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object methodInvokeExpiredTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        // 开始
        stopWatch.start();
        Object retVal = proceedingJoinPoint.proceed();
        // 结束
        stopWatch.stop();
        log.info("时间="  + stopWatch.getTotalTimeMillis());
        this.reportToMonitorSystem(proceedingJoinPoint.getSignature().toString(),stopWatch.getTotalTimeMillis());
        return retVal;
    }

    public void reportToMonitorSystem(String methodName, long expiredTime) {
        log.info("上报到公司监控平台,方法：耗时" + methodName + "," + expiredTime);
    }
}
