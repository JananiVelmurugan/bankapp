package com.ab.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.bank.model.Transaction;

public interface ITransactionDao extends JpaRepository<Transaction, Integer> {

	public List<Transaction> findByCustomerId(Integer id);

}
