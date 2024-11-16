package com.project.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.atm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
