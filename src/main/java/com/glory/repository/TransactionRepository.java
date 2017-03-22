package com.glory.repository;

import org.springframework.data.repository.CrudRepository;

import com.glory.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}
