package com.ccydeo.repository;

import com.ccydeo.model.Account;
import com.ccydeo.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();

    public static List<Transaction> findAll() {

        return transactionList;
    }


    public Transaction save(Transaction transaction){
transactionList.add(transaction);
        return transaction;
    }



}
