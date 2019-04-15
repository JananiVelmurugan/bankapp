package com.ab.bank.service;

import java.util.List;

import com.ab.bank.model.Transaction;

public interface ITransactionService {
	public void save(Transaction transaction);

	public List<Transaction> findByCustomerId(Integer customerId);
}
