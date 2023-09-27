package com.ccydeo.service.impl;

import com.ccydeo.enums.AccountType;
import com.ccydeo.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal bigDecimal, Date dateCreation, AccountType accountType, Long userId);

    List<Account> listAllAccounts();
}
