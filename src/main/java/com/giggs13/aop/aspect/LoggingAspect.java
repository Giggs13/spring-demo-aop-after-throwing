package com.giggs13.aop.aspect;

import com.giggs13.aop.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @Before("com.giggs13.aop.aspect.AopExpressions.daoPackageExcludingGettersAndSetters()")
    private void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n---> Executing @Before advice on a method");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature);

        Arrays.stream(joinPoint.getArgs())
                .forEach(System.out::println);
    }

    @AfterReturning(
            pointcut = "com.giggs13.aop.aspect.AopExpressions.findAccounts()",
            returning = "result")
    private void afterReturningFindAccountsAdvice(JoinPoint joinPoint,
                                                  List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n<--- Executing @AfterReturning advice on a method " + method);

        System.out.println("Result is: " + result + "\n");
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        result.forEach(account -> account.setName(
                Optional.ofNullable(account.getName())
                        .orElse("Unknown Person")
                        .toUpperCase()));
    }

    @AfterThrowing(
            pointcut = "com.giggs13.aop.aspect.AopExpressions.findAccounts()",
            throwing = "error")
    private void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,
                                                 Throwable error) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n<--- Executing @AfterThrowing advice on a method " + method);

        System.out.println("Error is: " + error + "\n");
    }
}
