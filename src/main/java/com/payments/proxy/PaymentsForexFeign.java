package com.payments.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author shar939
 *
 */

@FeignClient(name = "get-exchange-rate", fallback = StubbedCurrency.class)
public interface PaymentsForexFeign {

	@RequestMapping(value = "/forex/from/{from}/to/{to}")
	public BigDecimal getExchangeValue(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to);
	
	
}

@Component
class StubbedCurrency implements PaymentsForexFeign {

	@RequestMapping(value = "/forex/from/{from}/to/{to}")
	
	public BigDecimal getExchangeValue(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to) {
		return new BigDecimal(65);
	}
}