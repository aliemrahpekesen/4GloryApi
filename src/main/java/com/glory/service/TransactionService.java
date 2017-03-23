package com.glory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Transaction;
import com.glory.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public Transaction create(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAll() {
		return (List<Transaction>) transactionRepository.findAll();
	}

	public void delete(Long id) {
		if (id == 0) {
			transactionRepository.deleteAll();
		} else {
			transactionRepository.delete(id);
		}
	}

	public Transaction update(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public Transaction findById(Long id){
		return transactionRepository.findById(id);
	}

}
