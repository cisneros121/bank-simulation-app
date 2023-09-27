package com.ccydeo.service.impl;

import com.ccydeo.exception.BadRequestException;
import com.ccydeo.model.Account;
import com.ccydeo.model.Transaction;
import com.ccydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {


    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {

        validateAccount(sender,receiver);
        return null;
    }

    private void validateAccount(Account sender,Account receiver){
        /*
        if any account is null
        if any account id's are the same
        if the account exist in db
         */
        if (sender==null||receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
