package com.ab.bank.service;

import java.util.List;

import com.ab.bank.model.Customer;
import com.ab.bank.model.Transaction;

public interface ICustomerService {

	public List<Customer> findAll();

	public void save(Customer customer);

	public Customer getCustomerByIdAndPassword(int id, String password);

	public Customer findById(int id);

//	int findBalanceById(int id);

//	public List<Transaction> findByCustomerId(Integer id);

}
