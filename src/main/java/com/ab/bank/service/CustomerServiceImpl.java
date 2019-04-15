package com.ab.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.bank.dao.ICustomerDao;
import com.ab.bank.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;

	@Override
	public List<Customer> findAll() {
		List<Customer> customerlist = customerDao.findAll();
		return customerlist;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public Customer findById(Integer id) {
		Customer customer = customerDao.findById(id).get();
		return customer;

	}

	@Override
	public Customer findByIdAndPassword(Integer id, String password) {
		Customer customer = null;
		try {
			customer = customerDao.findByIdAndPassword(id, password);
		} catch (Exception e) {
			customer = null;
		}
		return customer;
	}

}
