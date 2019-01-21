package com.giggs13.aop;

import com.giggs13.aop.dao.AccountDAOImpl;
import com.giggs13.aop.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAOImpl accountDAO = context.getBean(AccountDAOImpl.class);
        try {
            boolean tripWire = true;
            List<Account> accounts = accountDAO.findAccounts(tripWire);
            accounts.forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("\n\nMain Program ... caught exception: " + ex);
        }

        context.close();
    }
}
