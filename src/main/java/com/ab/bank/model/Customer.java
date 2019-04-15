package com.ab.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "seq994_cus_id", allocationSize = 1)
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int accountBalance;

//		@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
//		private Set<Transaction> transaction = new HashSet<>();
//		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//		public Set<Transaction> getTransaction() {
//			return transaction;
//		}
//
//		public void setTransaction(Set<Transaction> transaction) {
//			this.transaction = transaction;
//		}

	public long getId() {
		return id;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

//		public void addTransaction(Transactions transaction) {
//			transaction.setCustomer(this);			//this will avoid nested cascade
//			this.getTransaction().add(transaction);}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", UserName=" + userName
				+ ", password=" + password + ", accountBalance=" + accountBalance + "]";
	}
}