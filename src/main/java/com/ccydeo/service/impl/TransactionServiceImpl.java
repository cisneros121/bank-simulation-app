package com.ccydeo.service.impl;

import com.ccydeo.enums.AccountType;
import com.ccydeo.exception.AccountOwnerShipException;
import com.ccydeo.exception.BadRequestException;
import com.ccydeo.exception.InsufficientBalanceException;
import com.ccydeo.model.Account;
import com.ccydeo.model.Transaction;
import com.ccydeo.repository.AccountRepository;
import com.ccydeo.repository.TransactionRepository;
import com.ccydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

private final AccountRepository accountRepository;
private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {

        validateAccount(sender,receiver);
        checkAccountOwnerShip(sender,receiver);
        executeBalanceAndUpdateIfRequired(amount,sender,receiver);
        Transaction transaction=Transaction.builder().sender(sender.getId()).receiver(receiver.getId()).amount(amount).creationDate(creationDate)
                .message(message).build();

        return transactionRepository.save(transaction);
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if (checkSenderBalance(sender,amount)){
            //update the balance
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else {
            throw new InsufficientBalanceException("Balance is not enough for this transfer");
        }

    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        //verify sender has enough balance to send to receiver
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;


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

        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different from receiver account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
        checkAccountOwnerShip(sender,receiver);


    }

    private void checkAccountOwnerShip(Account sender, Account receiver) {

        if (sender.getAccountType().equals(sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))) {
            if (!(sender.getAccountType().equals(receiver.getAccountType()))) {
                throw new AccountOwnerShipException("If one of the accounts is saving the user must be the same for sender and receiver");
            }

        }
    }
    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }


    public void findAccountById(UUID uuid){

        accountRepository.findById(uuid);
    }


}

