package com.project.atm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.atm.entity.Transaction;
import com.project.atm.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> getTransactionsByAccountId(int accountId){
		return transactionRepository.findAll()
				.stream()
				.filter(transaction -> transaction.getAccount().getAccountId() == accountId)
				.toList();
		
	}
}
