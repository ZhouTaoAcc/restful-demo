package com.kedacom.MyAspet;


import com.kedacom.annotation.WebLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by zhoutao on 2019/9/5.
 */
@Aspect//标识这是一个切面
@Component//交给spring容器管理
public class LoggerAspect {
    private final static Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

    // 以自定义 @WebLog 注解为切点
    @Pointcut("@annotation(com.kedacom.annotation.WebLog)")
    private void cutMethod(){

    }
    /*切点之前*/
    @Before("cutMethod()")
    public void begin(JoinPoint joinPoint) throws Exception {
//        获取request

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("========begin========");
//        获取Weblog注解信息
        String info=getWebInfo(joinPoint);
        logger.info("Point info    :{}",info);

        /*请求地址url*/
        logger.info("请求地址url  :{}",request.getRequestURL().toString());
//      请求方法
        logger.info("请求方法   ：{}",request.getMethod());
         /*具体切入执行方法 获取方法所在的类名及方法名*/
         logger.info("Class Method :{}.{}",joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());

//         请求IP
        logger.info("ip   :{}",request.getRemoteAddr());

        /*请求参数*/
        logger.info("切入方法请求参数  ：{}", Arrays.asList(joinPoint.getArgs()));
    }

    private String getWebInfo(JoinPoint joinPoint) throws ClassNotFoundException {
        /*获取切入点的目标类
        *  1.获取切入点目标类名
        *  2.把该类加载进内存*/
        String targetName = joinPoint.getTarget().getClass().getName();
        logger.info("是什么？"+targetName);//com.kedacom.controller.UserController
        Class<?> targetClass = Class.forName(targetName);
//切入方法的方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取切入方法参数
        Object[] arguments = joinPoint.getArgs();
        // 获取目标类的所有方法
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            // 方法名相同、包含目标注解、方法参数个数相同（避免有重载）
            if (method.getName().equals(methodName) && method.isAnnotationPresent(WebLog.class)
                    && method.getParameterTypes().length == arguments.length) {
                return method.getAnnotation(WebLog.class).value();
            }
        }
        return "";
    }
    /**
     * 切点返回内容后
     *
     * @throws Throwable
     */
    @AfterReturning("cutMethod()")
    public  void after(){
        System.out.println("===@AfterReturning===");
    }
    /**
     * 切点抛出异常后
     *
     * @throws Throwable
     */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() throws Throwable {
        logger.info("============ afterThrowing ==========");
    }
    /**
     * 环绕 切点运行的整个周期
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("cutMethod()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("============ doAround ==========");
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Output Parameter : {}", result);
        // 执行时间
        logger.info("Execution Time : {} ms", System.currentTimeMillis() - startTime);

    }

}
