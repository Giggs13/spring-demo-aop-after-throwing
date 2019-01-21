package com.giggs13.aop;

import com.giggs13.aop.dao.AccountDAOImpl;
import com.giggs13.aop.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAOImpl accountDAO = context.getBean(AccountDAOImpl.class);
        List<Account> accounts = accountDAO.findAccounts();
        accounts.forEach(System.out::println);

        context.close();
    }
}
