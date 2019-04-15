package com.ab.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ab.bank.model.Customer;
import com.ab.bank.service.ICustomerService;
import com.ab.bank.service.ITransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ITransactionService transactionService;

	int temp;
	// Transaction transaction = new Transaction();

//	@PostMapping("save")
//	public ModelAndView save(@RequestParam String firstName, @RequestParam String lastName,
//			@RequestParam String userName, @RequestParam String password) {
//		Customer customer = new Customer();
//
//		customer.setFirstName(firstName);
//		customer.setLastName(lastName);
//		customer.setuserName(userName);
//		customer.setPassword(password);
//		customer.setAccountBalance(0);
//		customerservice.save(customer);
//		List<Customer> customerlist = customerservice.findAll();
//		ModelAndView modelAndView = new ModelAndView("customerlist");
//		modelAndView.addObject("CUSTLIST", customerlist);
//
//		return modelAndView;
//
//	}
//
	@PostMapping("validate")
	public ModelAndView requestLogin(@RequestParam int id, @RequestParam String password) {
		ModelAndView modelAndView;
		Customer customer = customerService.findById(id);
		if (customer == null) {
			modelAndView = new ModelAndView("index");
		} else if (customer.getId() == id && customer.getPassword().equals(password)) {
			modelAndView = new ModelAndView("home");
		} else {
			modelAndView = new ModelAndView("index");
		}
		return modelAndView;
	}

//
//	@PostMapping("/manager")
//	public ModelAndView enterManager(@RequestParam String name, @RequestParam String password) {
//		ModelAndView modelAndView;
//		String a = name;
//		String b = password;
//
//		if (a.equals("ayan") && b.equals("mehta")) {
//			modelAndView = new ModelAndView("createaccount");
//		} else {
//			modelAndView = new ModelAndView("home22");
//		}
//		return modelAndView;
//
//	}
//
//	@GetMapping("/balance")
//	public ModelAndView getbalancefromid() {
//
//		int customer = customerservice.findBalanceById(temp);
//		ModelAndView modelAndView = new ModelAndView("showbalance");
//
//		modelAndView.addObject("BAL", customer);
//		Transaction transaction = new Transaction();
//		transaction.setAmount(customer);
//		transaction.setType("previous balance");
//
//		return modelAndView;
//	}

//	@PostMapping("/deposit")
//	public ModelAndView doWithdraw(@RequestParam Integer amount) {
//
//		Customer customer = customerService.findById(temp);
//		int currentBalance = customer.getAccountBalance();
//
//		ModelAndView modelAndView;
//		System.out.println("before depositing --->" + currentBalance);
//		System.out.println("amount--->" + amount);
//
//		currentBalance += amount;
//		System.out.println("after depositing--->" + currentBalance);
//		customer.setAccountBalance(currentBalance);
//		customerService.save(customer);
//
//		Transaction transaction = new Transaction();
//		transaction.setAmount(amount);
//		transaction.setType("DEPOSITED");
//		transaction.setCustomer(customer);
//		transactionService.save(transaction);
//
//		modelAndView = new ModelAndView("showbalance");
//		modelAndView.addObject("CURRENTBALANCE", currentBalance);
//
//		return modelAndView;
//	}
//	@PostMapping("/withdraw")
//	public ModelAndView doWithdraw(@RequestParam Integer amount) {
//
//		Customer customer = customerService.findById(temp);
//		int currentBalance = customer.getAccountBalance();
//
//		ModelAndView modelAndView;
//		System.out.println("before withdrawal--->" + currentBalance);
//		System.out.println("amount--->" + amount);
//
//		if (currentBalance < amount) {
//			modelAndView = new ModelAndView("lowbalance");
//			return modelAndView;
//		} else {
//			currentBalance -= amount;
//			System.out.println("after withdrawal--->" + currentBalance);
//			customer.setAccountBalance(currentBalance);
//			customerService.save(customer);
//
//			Transaction transaction = new Transaction();
//			transaction.setAmount(amount);
//			transaction.setType("WITHDRAWN");
//			transaction.setCustomer(customer);
//			transactionService.save(transaction);
//
//			modelAndView = new ModelAndView("showbalance");
//			modelAndView.addObject("CURRENTBALANCE", currentBalance);
//		}
//		return modelAndView;
//	}

//	@PostMapping("/transfer")
//	public ModelAndView doFundTransfer(@RequestParam int id, @RequestParam int amount) {
//
//		Customer sender = customerService.findById(temp);
//		Customer receiver = customerService.findById(id);
//
//		int senderBalance = sender.getAccountBalance();
//
//		ModelAndView modelAndView;
//		if (senderBalance < amount) {
//			modelAndView = new ModelAndView("accountinsfficient");
//			return modelAndView;
//		} else {
//			senderBalance = sender.getAccountBalance() - amount;
//			int receiverBalance = receiver.getAccountBalance() + amount;
//
//			sender.setAccountBalance(senderBalance);
//			receiver.setAccountBalance(receiverBalance);
//
//			customerService.save(sender);
//			customerService.save(receiver);
//
//			Transaction senderTransaction = new Transaction();
//			senderTransaction.setAmount(amount);
//			senderTransaction.setType("DEBITED");
//			senderTransaction.setCustomer(sender);
//			transactionService.save(senderTransaction);
//
//			Transaction receiverTransaction = new Transaction();
//			receiverTransaction.setAmount(amount);
//			receiverTransaction.setType("CREDITED");
//			receiverTransaction.setCustomer(receiver);
//			transactionService.save(receiverTransaction);
//
//			modelAndView = new ModelAndView("transferdetails");
//
//			modelAndView.addObject("FROMACCOUNTNO", sender.getId());
//			modelAndView.addObject("TOACCOUNTNO", receiver.getId());
//			modelAndView.addObject("AMOUNTTRANSFERRED", amount);
//			modelAndView.addObject("SENDERBALANCE", senderBalance);
//
//			return modelAndView;
//		}
//
//	}

//	@GetMapping("transaction")
//	public ModelAndView printTransactions() {
//		List<Transaction> transactionsList = transactionService.findByCustomerId(temp);
//		System.out.println(transactionsList.size());
//		ModelAndView modelandview = new ModelAndView("myReceipt");
//		modelandview.addObject("TRANSACTIONSLIST", transactionsList);
//		return modelandview;
//
//	}

}