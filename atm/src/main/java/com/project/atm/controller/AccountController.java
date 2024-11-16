package com.project.atm.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.atm.service.AccountService;
import com.project.atm.service.TransactionService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping("/{accountId}/balance")
	public ResponseEntity<BigDecimal> checkBalance(@PathVariable int accountId){
		return ResponseEntity.ok(accountService.checkBalance(accountId));
	}
	
	@PostMapping("/{accountId}/WITHDRAWAL")
	public ResponseEntity<String> withdraw(@PathVariable int accountId, @RequestBody BigDecimal amount){
		
		accountService.withdraw(accountId, amount);
		return ResponseEntity.ok("Withdrawal successful");
	}
	
	@PostMapping("/{accountId}/DEPOSIT")
	public ResponseEntity<String> deposite(@PathVariable int accountId, @RequestBody BigDecimal amount){
		
		accountService.deposite(accountId, amount);
		return ResponseEntity.ok("Deposite successful");
	}
	
	@GetMapping("/{accountId}/transactions")
	public ResponseEntity<List<?>> getTransactions(@PathVariable int accountId){
		return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
	}
}









