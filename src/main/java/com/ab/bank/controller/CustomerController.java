package com.ab.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ab.bank.model.Customer;
import com.ab.bank.model.Transaction;
import com.ab.bank.service.ICustomerService;
import com.ab.bank.service.ITransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ITransactionService transactionService;

	int customerId;

	@PostMapping("create")
	public ModelAndView save(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password) {

		// Create a new customer
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setuserName(userName);
		customer.setPassword(password);
		customer.setAccountBalance(0);

		// Save the customer object to the table
		customerService.save(customer);

		// List all the customers from the table
		List<Customer> customerList = customerService.findAll();

		// display the customers list in the JSP page
		ModelAndView modelAndView = new ModelAndView("customersList");
		modelAndView.addObject("CUSTOMERSLIST", customerList);

		return modelAndView;

	}

	@PostMapping("validate")
	public ModelAndView requestLogin(@RequestParam int id, @RequestParam String password) {
		ModelAndView modelAndView;
		// Check whether customer with the given id and password is present in the table
		Customer customer = customerService.findByIdAndPassword(id, password);

		// If there is no customer with the given id
		if (customer == null) {
			modelAndView = new ModelAndView("index");
		}
		// Login for manager with static id and password
		else if (customer.getId() == 100 && customer.getPassword().equals("ayan123")) {
			modelAndView = new ModelAndView("showManagerHome");
		}
		// Login if its a valid customer in the table
		else {
			customerId = id;
			modelAndView = new ModelAndView("showCustomerHome");
		}
		return modelAndView;
	}

	@GetMapping("/showbalance")
	public ModelAndView showBalance() {
		// Find the customer with the id
		Customer customer = customerService.findById(customerId);

		// Get the balance from the customer object
		int accountBalance = customer.getAccountBalance();

		// Display
		ModelAndView modelAndView = new ModelAndView("showbalance");

		modelAndView.addObject("CURRENTBALANCE", accountBalance);
		return modelAndView;
	}

	@PostMapping("/deposit")
	public ModelAndView deposit(@RequestParam Integer amount) {

		Customer customer = customerService.findById(customerId);
		int currentBalance = customer.getAccountBalance();

		ModelAndView modelAndView;
		System.out.println("before depositing --->" + currentBalance);
		System.out.println("amount--->" + amount);

		currentBalance += amount;
		System.out.println("after depositing--->" + currentBalance);
		customer.setAccountBalance(currentBalance);
		customerService.save(customer);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setType("DEPOSITED");
		transaction.setCustomer(customer);
		transactionService.save(transaction);

		modelAndView = new ModelAndView("showbalance");
		modelAndView.addObject("CURRENTBALANCE", currentBalance);

		return modelAndView;
	}

	@PostMapping("/withdraw")
	public ModelAndView withdraw(@RequestParam Integer amount) {

		Customer customer = customerService.findById(customerId);
		int currentBalance = customer.getAccountBalance();

		ModelAndView modelAndView;
		System.out.println("before withdrawal--->" + currentBalance);
		System.out.println("amount--->" + amount);

		if (currentBalance < amount) {
			modelAndView = new ModelAndView("lowbalance");
			return modelAndView;
		} else {
			currentBalance -= amount;
			System.out.println("after withdrawal--->" + currentBalance);
			customer.setAccountBalance(currentBalance);
			customerService.save(customer);

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setType("WITHDRAWN");
			transaction.setCustomer(customer);
			transactionService.save(transaction);

			modelAndView = new ModelAndView("showbalance");
			modelAndView.addObject("CURRENTBALANCE", currentBalance);
		}
		return modelAndView;
	}

	@PostMapping("/transfer")
	public ModelAndView doFundTransfer(@RequestParam int id, @RequestParam int amount) {

		Customer sender = customerService.findById(customerId);
		Customer receiver = customerService.findById(id);

		int senderBalance = sender.getAccountBalance();

		ModelAndView modelAndView;
		if (senderBalance < amount) {
			modelAndView = new ModelAndView("accountinsfficient");
			return modelAndView;
		} else {
			senderBalance = sender.getAccountBalance() - amount;
			int receiverBalance = receiver.getAccountBalance() + amount;

			sender.setAccountBalance(senderBalance);
			receiver.setAccountBalance(receiverBalance);

			customerService.save(sender);
			customerService.save(receiver);

			Transaction senderTransaction = new Transaction();
			senderTransaction.setAmount(amount);
			senderTransaction.setType("DEBITED");
			senderTransaction.setCustomer(sender);
			transactionService.save(senderTransaction);

			Transaction receiverTransaction = new Transaction();
			receiverTransaction.setAmount(amount);
			receiverTransaction.setType("CREDITED");
			receiverTransaction.setCustomer(receiver);
			transactionService.save(receiverTransaction);

			modelAndView = new ModelAndView("transferdetails");

			modelAndView.addObject("FROMACCOUNTNO", sender.getId());
			modelAndView.addObject("TOACCOUNTNO", receiver.getId());
			modelAndView.addObject("AMOUNTTRANSFERRED", amount);
			modelAndView.addObject("SENDERBALANCE", senderBalance);

			return modelAndView;
		}

	}

	@GetMapping("transaction")
	public ModelAndView printTransactions() {
		List<Transaction> transactionsList = transactionService.findByCustomerId(customerId);
		System.out.println(transactionsList.size());
		ModelAndView modelandview = new ModelAndView("myReceipt");
		modelandview.addObject("TRANSACTIONSLIST", transactionsList);
		return modelandview;

	}

}