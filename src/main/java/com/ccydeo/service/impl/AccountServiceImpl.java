package com.ccydeo.service.impl;

import com.ccydeo.enums.AccountType;
import com.ccydeo.model.Account;
import com.ccydeo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;


public  AccountServiceImpl(AccountRepository accountRepository){
    this.accountRepository=accountRepository;
}

    @Override
    public Account createNewAccount(BigDecimal bigDecimal, Date dateCreation, AccountType accountType, Long userId) {
      //need to create account obj
        Account account= Account.builder().id(UUID.randomUUID()).balance(bigDecimal).creationDate(dateCreation).accountType(accountType)
                .userId(userId).build();
 return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccounts() {
        return null;
    }
}
