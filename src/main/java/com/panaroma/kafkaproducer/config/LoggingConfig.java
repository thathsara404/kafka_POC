package com.panaroma.kafkaproducer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Modifier;

@Aspect
@Component
@Slf4j
public class LoggingConfig {

    @Before("execution(* com.panaroma.kafkaproducer..*(..)))")
    public void logBefore(JoinPoint joinPoint) {

        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        if (!Modifier.isPrivate(signature.getModifiers())
                && !signature.getName().startsWith("set")
                && !signature.getName().startsWith("is")) {
            log.info("ENTER METHOD ::"
                    + signature.getReturnType().getSimpleName() + " "
                    + signature.getName() + "("
                    + parameterType(signature.getParameterTypes()) + ")");
        }

        if (signature.getName().equals("studentNameListener")) {
            log.info("Reading from Kafka  Student-Name-Listener " );
        }

    }

    @After("execution(* com.panaroma.kafkaproducer..*(..)))")
    public void logAfter(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        MethodSignature signature = (MethodSignature)     joinPoint.getSignature();

        if (!Modifier.isPrivate(signature.getModifiers())
                && !signature.getName().startsWith("set")
                && !signature.getName().startsWith("is")) {
            log.info("EXIT METHOD ::"
                    + signature.getReturnType().getSimpleName() + " "
                    + signature.getName() + "("
                    + parameterType(signature.getParameterTypes()) + ")");
        }
    }

    @AfterThrowing(pointcut = "execution(* com.panaroma.kafkaproducer..*(..)))", throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        MethodSignature signature = (MethodSignature)     joinPoint.getSignature();

        if (!Modifier.isPrivate(signature.getModifiers())
                && !signature.getName().startsWith("get")
                && !signature.getName().startsWith("set")
                && !signature.getName().startsWith("is")) {
            log.error("EXCEPTION IN METHOD ::"
                    + signature.getReturnType().getSimpleName() + " "
                    + signature.getName() + "("
                    + parameterType(signature.getParameterTypes()) + ")");
            log.error("Exception",error);
        }
    }

    private String parameterType(Class<?>[] classes) {
        StringBuffer buffer = new StringBuffer();
        String returnValue = "";

        for (Class<?> string : classes) {
            buffer.append(Modifier.toString(string.getModifiers()));
            buffer.append(" ");
            buffer.append(string.getSimpleName());
            buffer.append(",");
        }

        returnValue = buffer.toString();

        if (returnValue.trim().length() > 0) {
            returnValue = returnValue.substring(0, returnValue.length() - 1);
        }

        return returnValue;
    }

}
