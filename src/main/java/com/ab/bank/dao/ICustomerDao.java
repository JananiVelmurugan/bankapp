package com.ab.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.bank.model.Customer;

public interface ICustomerDao extends JpaRepository<Customer, Integer> {

	public Customer findByIdAndPassword(Integer id, String password);

}
