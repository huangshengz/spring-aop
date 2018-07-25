package cn.gzrrg.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP 接口鉴权，自定义一个注解，放在RequestMapping的方法上，然后advice判断token是否正确
 * @author huangsz  2018/7/25 0025
 */
@Component
@Aspect
public class HttpAopAdviceDefine {

    /**
     * 定义一个pointcut，使用切点表达式函数，来描述哪些join point使用advice
     */
    @Pointcut("@annotation(cn.gzrrg.aspectj.AuthCheck)")
    public void poincut(){

    }

    /**
     * 定义一个环绕通知，当拦截到切面的时候，要做什么
     * @param point
     * @return
     * @throws Exception
     */
    @Around("poincut()")
    public Object checkAuth(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        // 获取token
        String token = this.getUserToken(request);
        System.out.println(token);
        if (!token.equalsIgnoreCase("123456")) {
            return "权限不足，请确认！";
        }
        return point.proceed();
    }

    private String getUserToken(HttpServletRequest request) {
        return request.getHeader("token");
    }
}
