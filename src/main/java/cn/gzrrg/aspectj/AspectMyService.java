package cn.gzrrg.aspectj;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  日志记录，记录一个接口的参数，返回信息，异常信息。
 *  @author huangsz  2018/7/25 0025
 */
@Component
@Aspect
public class AspectMyService {

    private Logger log = LoggerFactory.getLogger(AspectMyService.class);

    /**
     * 定义要被切的方法
     */
    @Pointcut("within(cn.gzrrg.service.AspectService)")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void logBefore(JoinPoint point){
        log.info("在执行方法之前获得参数咯：" +  point.getSignature().toShortString() + "," + point.getArgs().toString());
    }

    @AfterReturning(pointcut = "pointcut()", returning = "retVal")
    public void logAfterReturning(JoinPoint point, Object retVal) {
        log.info("在执行方法之后获得参数咯：" +  point.getSignature().toShortString() + "," + point.getArgs().toString());
    }

    /**
     * 异常
     * @param point
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void logException(JoinPoint point, Exception exception) {
        log.info("异常信息：" + point.getSignature() + "," + exception.getMessage());
    }
}
