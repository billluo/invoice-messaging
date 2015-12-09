package com.rh.invoicelog;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestCriteria {
	String today="24-Nov-2015";
	
	@Autowired
	ErrorLogCriteria errorLogCriteria =new InvoiceErrorCriteria();
	
	@Test
	public void testDate() {
		Assert.assertEquals(today, errorLogCriteria.getCriteria());		
	}
}
