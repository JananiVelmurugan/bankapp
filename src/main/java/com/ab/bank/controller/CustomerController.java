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
		List<Customer> customersList = customerService.findAll();

		// display the customers list in the JSP page
		ModelAndView modelAndView = new ModelAndView("customersList");
		modelAndView.addObject("CUSTOMERSLIST", customersList);

		// Return the JSP page
		return modelAndView;

	}

	@PostMapping("validate")
	public ModelAndView requestLogin(@RequestParam int id, @RequestParam String password) {
		ModelAndView modelAndView;

		// Login for manager with static id and password
		if (id == 100 && password.equals("100")) {
			modelAndView = new ModelAndView("showManagerHome");
		} else {

			// Check whether customer with the given id and password is present in the table
			Customer customer = customerService.findByIdAndPassword(id, password);

			// If there is no customer with the given id
			if (customer == null) {
				modelAndView = new ModelAndView("index");
			}

			// Login if its a valid cus;tomer in the table
			else {
				// Assign the given id to customer id
				customerId = id;
				modelAndView = new ModelAndView("showCustomerHome");
			}
		}
		// Return the JSP page
		return modelAndView;
	}

	@GetMapping("/showBalance")
	public ModelAndView showBalance() {
		// Find the customer with the id
		Customer customer = customerService.findById(customerId);

		// Get the balance from the customer object
		int accountBalance = customer.getAccountBalance();

		// Display the balance in the JSP page
		ModelAndView modelAndView = new ModelAndView("showBalance");
		modelAndView.addObject("CURRENTBALANCE", accountBalance);

		// Return the JSP page
		return modelAndView;
	}

	@PostMapping("/deposit")
	public ModelAndView deposit(@RequestParam Integer amount) {

		ModelAndView modelAndView;

		// Find the customer based on the id
		Customer customer = customerService.findById(customerId);

		// Get the account balance of the customer
		int currentBalance = customer.getAccountBalance();

		// Deposit the amount to the balance of the customer
		currentBalance += amount;

		// Update with the new balance
		customer.setAccountBalance(currentBalance);

		// Save the customer with the updated balance
		customerService.save(customer);

		// Create a new transaction object
		Transaction transaction = new Transaction();

		// Populate the transaction object
		transaction.setAmount(amount);
		transaction.setType("DEPOSITED");
		transaction.setCustomer(customer);

		// Save the transaction object
		transactionService.save(transaction);

		// Display the JSP page
		modelAndView = new ModelAndView("showBalance");
		modelAndView.addObject("CURRENTBALANCE", currentBalance);

		// Return the JSP page
		return modelAndView;
	}

	@PostMapping("/withdraw")
	public ModelAndView withdraw(@RequestParam Integer amount) {

		ModelAndView modelAndView;

		// Find the customer based on the id
		Customer customer = customerService.findById(customerId);

		// Get the account balance of the customer
		int currentBalance = customer.getAccountBalance();

		// If the balance is less than the amount to be withdrawn
		if (currentBalance < amount) {
			modelAndView = new ModelAndView("error");
			return modelAndView;
		}
		// If the balance is greater than or equal to the amount to be withdrawn
		else {
			// Withdraw the amount
			currentBalance -= amount;

			// Update the current balance after withdrawal
			customer.setAccountBalance(currentBalance);

			// Save the customer
			customerService.save(customer);

			// Create a new transaction object
			Transaction transaction = new Transaction();

			// Populate the transaction object
			transaction.setAmount(amount);
			transaction.setType("WITHDRAWN");
			transaction.setCustomer(customer);

			// Save the transaction object
			transactionService.save(transaction);

			// Display the JSP page
			modelAndView = new ModelAndView("showBalance");
			modelAndView.addObject("CURRENTBALANCE", currentBalance);
		}

		// Return the JSP page
		return modelAndView;
	}

	@PostMapping("/transfer")
	public ModelAndView doFundTransfer(@RequestParam int id, @RequestParam int amount) {
		ModelAndView modelAndView;

		// Find the sender with the id stored the customer id
		Customer sender = customerService.findById(customerId);

		// Find the receiver with the given id from the JSP
		Customer receiver = customerService.findById(id);

		// Get the account balance from the sender
		int senderBalance = sender.getAccountBalance();

		// Check if the sender balance is less than the amount to be transferred
		if (senderBalance < amount) {
			modelAndView = new ModelAndView("error");

		}
		// If the sender balance is sufficient for the transfer
		else {
			// Debit the amount from sender
			senderBalance = sender.getAccountBalance() - amount;

			// Credit the amount to receiver
			int receiverBalance = receiver.getAccountBalance() + amount;

			// Update the sender balance
			sender.setAccountBalance(senderBalance);

			// Update the receiver balance
			receiver.setAccountBalance(receiverBalance);

			// Save the sender object
			customerService.save(sender);

			// Save the receiver object
			customerService.save(receiver);

			// Create a new transaction for sender
			Transaction senderTransaction = new Transaction();

			// Populate the sender transaction object
			senderTransaction.setAmount(amount);
			senderTransaction.setType("DEBITED");
			senderTransaction.setCustomer(sender);

			// Save the sender transaction object
			transactionService.save(senderTransaction);

			// Create a new transaction for receiver
			Transaction receiverTransaction = new Transaction();

			// Populate the receiver transaction object
			receiverTransaction.setAmount(amount);
			receiverTransaction.setType("CREDITED");
			receiverTransaction.setCustomer(receiver);

			// Save the receiver transaction object
			transactionService.save(receiverTransaction);

			// Display the JSP page
			modelAndView = new ModelAndView("transferDetails");
			modelAndView.addObject("FROMACCOUNTNO", sender.getId());
			modelAndView.addObject("TOACCOUNTNO", receiver.getId());
			modelAndView.addObject("AMOUNTTRANSFERRED", amount);
			modelAndView.addObject("SENDERBALANCE", senderBalance);

		}

		// Return the JSP page
		return modelAndView;
	}

	@GetMapping("showTransaction")
	public ModelAndView showTransaction() {

		// Find all the transactions made by the customer with the Id
		List<Transaction> transactionsList = transactionService.findByCustomerId(customerId);

		// Display the JSP page
		ModelAndView modelandview = new ModelAndView("showTransaction");
		modelandview.addObject("TRANSACTIONSLIST", transactionsList);

		// Return the JSP page
		return modelandview;

	}

}