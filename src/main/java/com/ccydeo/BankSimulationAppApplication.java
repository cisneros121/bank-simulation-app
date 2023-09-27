package com.ccydeo;

import com.ccydeo.enums.AccountType;
import com.ccydeo.model.Account;
import com.ccydeo.service.impl.AccountServiceImpl;
import com.ccydeo.service.impl.TransactionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
        ApplicationContext container=SpringApplication.run(BankSimulationAppApplication.class, args);

        //get beans for account and transaction
        AccountServiceImpl accountBean = container.getBean(AccountServiceImpl.class);
        TransactionServiceImpl transactionBean = container.getBean(TransactionServiceImpl.class);

Account sender=accountBean.createNewAccount(BigDecimal.valueOf(70),new Date(), AccountType.CHECKING,1L);

Account receiver=accountBean.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.CHECKING,2L);

accountBean.listAllAccounts().forEach(System.out::println);

transactionBean.makeTransfer(sender,receiver,new BigDecimal(40),new Date(),"Transaction");
        System.out.println("------------------------------------------------------");
        transactionBean.findAllTransaction().forEach(System.out::println);
        accountBean.listAllAccounts().forEach(System.out::println);


    }

}
