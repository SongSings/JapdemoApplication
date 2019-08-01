package com.datajpa.jpademo.Aspect;

import com.datajpa.jpademo.Util.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private static final Logger logger  = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 这样写是将重复的代码提取出来方便处理
     */
    @Pointcut("execution(public * com.datajpa.jpademo.web..*(..))")
    public void log() {}

    /**
     *
     * @param joinPoint
     * 注意：该方法中的HttpServletRequest为javax.servlet.http.HttpServletRequest;
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("1");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求路径url
        logger.info("url={}",request.getRequestURL());

        //记录请求方式method
        logger.info("method={}",request.getMethod());

        //记录访问者ip
        logger.info("ip={}", IPUtil.getIpAddr(request));//IPUtil.getIpAddr(request) or//request.getRemoteAddr()

        //记录访问的类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        //记录传递的参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("2");
    }

    @AfterReturning(returning = "obj",pointcut = "log()")
    public void doAfterReturning(Object obj) {
        logger.info("3");
        logger.info("response={}",obj);
    }


}
