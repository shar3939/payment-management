/**
 * 
 */
package com.payments.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.payments.model.AccountTrx;

/**
 * @author shar939
 *
 */
@FeignClient(name = "bank-account-mgmt")
public interface PaymentsFeign {
	
	@GetMapping(path = "/accounts/accountID/{accountID}")
	public AccountTrx getAccountDtls(@PathVariable(name = "accountID") Long accountID);

	@PostMapping(path = "/accounts", produces = "application/json", consumes = "application/json")
	public String createOrUpdateAccount(@RequestBody AccountTrx account);
}
