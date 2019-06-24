/**
 * 
 */
package com.payments.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payments.model.AccountTrx;
import com.payments.proxy.PaymentsFeign;
import com.payments.proxy.PaymentsForexFeign;

/**
 * @author shar939
 *
 */
@RestController
public class PaymentsController {

	@Autowired
	PaymentsFeign paymentsClient; 
	
	@Autowired
	PaymentsForexFeign paymentsForexClient;
	
	@PostMapping(path = "/transfer")
	public boolean transfer(@RequestBody AccountTrx accountTrx) {
		
		//To Account Number Validation and fetch Account Details.  
		System.out.println("accountTrx.getAccountId() "+accountTrx.getAccountId());
		AccountTrx benificiaryAccnt = paymentsClient.getAccountDtls(accountTrx.getToAccountID()); 
		AccountTrx fromAccnt = paymentsClient.getAccountDtls(accountTrx.getAccountId());
		
		if("".equals(accountTrx.getToCurrency()) || accountTrx.getToCurrency()==null) 
			accountTrx.setCurrency("INR");
		else 
			accountTrx.setToCurrency("INR");
		 
		if(benificiaryAccnt != null && fromAccnt != null) {

			if ("INR".equals(accountTrx.getCurrency())) {
				transferInINR(accountTrx, benificiaryAccnt, fromAccnt);
			} else {
				
				BigDecimal forexMultiplier = paymentsForexClient.getExchangeValue(accountTrx.getCurrency(), 
																accountTrx.getToCurrency());
				System.out.println(forexMultiplier+" getExchangeValue ");
				
				BigDecimal inrTransferAmount = accountTrx.getTransferAmnt().multiply(forexMultiplier);
				
				System.out.println("Transfer Amount in INR "+inrTransferAmount);
				
				accountTrx.setTransferAmnt(inrTransferAmount);
				transferInINR(accountTrx, benificiaryAccnt, fromAccnt);
			}
			
		}
		
		return false; 
	}

	private void transferInINR(AccountTrx accountTrx, AccountTrx benificiaryAccnt, AccountTrx fromAccnt) {
		System.out.println("balance " + benificiaryAccnt.getBalance());

		BigDecimal transferAmount = accountTrx.getTransferAmnt();

		// Deduction
		System.out.println("fromAccnt.getBalance(); "+fromAccnt.getBalance());
		BigDecimal fromAccntBalance = fromAccnt.getBalance();
		BigDecimal fromAccntFinalBalance = fromAccntBalance.subtract(transferAmount);
		fromAccnt.setBalance(fromAccntFinalBalance);

		// Deposit
		BigDecimal existingBalance = benificiaryAccnt.getBalance();
		BigDecimal finalBalance = existingBalance.add(transferAmount);
		benificiaryAccnt.setBalance(finalBalance);
		
		//persist to DB 
		paymentsClient.createOrUpdateAccount(fromAccnt);
		paymentsClient.createOrUpdateAccount(benificiaryAccnt);
	}
}
