package com.project.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.project.atm.entity.Account;

import jakarta.persistence.LockModeType;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Account findByAccountId(int accountId);
	
}
