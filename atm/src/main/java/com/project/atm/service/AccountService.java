package com.project.atm.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.atm.entity.Account;
import com.project.atm.entity.Transaction;
import com.project.atm.exception.InsufficientFundsException;
import com.project.atm.exception.ResourceNotFoundException;
import com.project.atm.repository.AccountRepository;
import com.project.atm.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

	@Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public BigDecimal checkBalance(int accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return account.getBalance();
    }
    
    @Transactional
    public void deposite(int accountId, BigDecimal amount) {
    	Account account = accountRepository.findById(accountId)
    			.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    	account.setBalance(amount);
    	accountRepository.save(account);
    	
    	// Log transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        transactionRepository.save(transaction);
    	
    }

    @Transactional
    public void withdraw(int accountId, BigDecimal amount) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        // Log transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        
        transaction.setType("WITHDRAWAL");
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        transactionRepository.save(transaction);
    }
}











