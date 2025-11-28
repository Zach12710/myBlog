package com.zzyweb.myblogcommon.aspect;

import com.zzyweb.myblogcommon.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author zhiyi
 *
 **/
@Aspect
@Slf4j
@Component
public class ApiOperationLogAspect {

    @Pointcut("@annotation(com.zzyweb.myblogcommon.aspect.ApiOperationLog)")
    public void apiOperationLog(){}


    /*
    * 环绕增强
    * 1.计算运行时间
    * 2. 生成追踪id 。使用MDC
    * 3. 生成追踪日志，主要包括请求描述，入参 类型 方法
    * 4. 生成追踪日志 请求描述，出参 + 耗时
    * 5. 清空MCD
     */

    //环绕注解 - 参数是设置好的pointcut
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) { // 参数 是Proceeding join point
        try {
            // 1.获取当前时间
            long startTime = System.currentTimeMillis();
            // 2.生成追踪id
            MDC.put("traceId", UUID.randomUUID().toString());
            // 3. 获取请求描述 入参 类型 和方法
            //描述
            String description = getDescription(joinPoint);
            //入参
            Object[] args = joinPoint.getArgs();
            String argsStr = Arrays.stream(args)
                    .map(JsonUtil::toJsonString)
                    .collect(Collectors.joining(","));
            //类型
            String className = joinPoint.getTarget().getClass().getName();
            //方法名
            String methodName = joinPoint.getSignature().getName();

            //4.打印日志
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsStr, className, methodName);

            // 5.执行方法得到结果
            Object result = joinPoint.proceed();

            // 6.获得执行时长
            long executionTime = System.currentTimeMillis() - startTime;
            // 打印出参等相关信息
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJsonString(result));

            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            MDC.clear();
        }
    }

    //帮助函数获得注解对于该方法的描述
    private String getDescription(ProceedingJoinPoint joinPoint) {
        // 1. 拿到方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 2. 根据签名拿到方法
        Method method = signature.getMethod();
        // 3.拿到注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);
        // 4.拿到注解的参数 description 并返回
        return annotation.description();


    }
}
