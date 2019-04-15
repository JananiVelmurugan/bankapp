package com.ab.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.bank.dao.ITransactionDao;
import com.ab.bank.model.Transaction;

@Service("transactionService")
public class TransactionServiceImpl implements ITransactionService {
	@Autowired
	private ITransactionDao transactionDao;

	@Override
	public List<Transaction> findByCustomerId(Integer customerId) {
		List<Transaction> list = transactionDao.findByCustomerId(customerId);
		return list;
	}

	@Override
	public void save(Transaction transaction) {

		transactionDao.save(transaction);
	}

}
