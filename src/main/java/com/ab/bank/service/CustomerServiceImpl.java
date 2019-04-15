package com.ab.bank.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.bank.dao.ICustomerDao;
import com.ab.bank.model.Customer;
import com.ab.bank.model.Transaction;

@Service("servicecustomer")
public class CustomerServiceImpl implements ICustomerService {
	@PersistenceContext
	private EntityManager em;
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
	public Customer findById(int id) {

		Customer customer = customerDao.findById(id).get();
		return customer;

	}

//	@Override
//	public int findBalanceById(int id) {
//
//		String qstr = "select accountBalance from Customer customer where id=:pid";
//		TypedQuery<Integer> query = em.createQuery(qstr, Integer.class);
//		query.setParameter("pid", id);
//		int a = query.getSingleResult();
//		return a;
//
//	}

	@Override
	public Customer getCustomerByIdAndPassword(int id, String password) {
		try {
			String qstr = "Select customer from Customer customer where id=:pid and password=:ppassword";
			TypedQuery<Customer> query = em.createQuery(qstr, Customer.class);
			query.setParameter("pid", id);
			query.setParameter("ppassword", password);
			Customer customer = query.getSingleResult();

			return customer;
		} catch (Exception e) {
			return null;
		}

	}

//	@Override
//	public List<Transaction> findByCustomerId(Integer id) {
//		try {
//			String qstr = "Select transaction from Transaction transaction where cust_id=:pid";
//			TypedQuery<Transaction> query = em.createQuery(qstr, Transaction.class);
//			query.setParameter("pid", id);
//			List<Transaction> transaction = query.getResultList();
//			return transaction;
//
//		} catch (Exception e) {
//			return null;
//		}
//
//	}
}
