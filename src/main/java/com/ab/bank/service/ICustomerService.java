package com.ab.bank.service;

import java.util.List;

import com.ab.bank.model.Customer;

public interface ICustomerService {

	public List<Customer> findAll();

	public void save(Customer customer);

	public Customer findById(Integer id);

	public Customer findByIdAndPassword(Integer id, String password);

}
