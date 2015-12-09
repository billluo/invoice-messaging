package com.rh.invoicelog;

//import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import com.rh.invoice.domain.ErrorLog;

public class TestErrorLog {
	@Test
	public void testErrorLog(){
		ErrorLog errorLogTest = new ErrorLog();
		errorLogTest.setDescription("no vendor found");
		errorLogTest.setErrorColumn("vendor");
		errorLogTest.setErrorValue("9999999");
		errorLogTest.setFileName("test01.csv");
		errorLogTest.setInvoiceNumber("inv10101");
		errorLogTest.setProcessName("INT030");
		errorLogTest.setRecordId(1234L);
		errorLogTest.setErrorDate(new Date());
		
		Assert.assertEquals("no vendor found", errorLogTest.getDescription());
		Assert.assertEquals("vendor", errorLogTest.getErrorColumn());
		Assert.assertEquals("9999999", errorLogTest.getErrorValue());
		Assert.assertEquals("test01.csv", errorLogTest.getFileName());
		Assert.assertEquals("inv10101", errorLogTest.getInvoiceNumber());
		Assert.assertEquals("INT030", errorLogTest.getProcessName());
		Assert.assertEquals(Long.valueOf(1234), errorLogTest.getRecordId());
		Assert.assertEquals(new Date(), errorLogTest.getErrorDate());	
//		Date myDate = new Date();
//		System.out.println(myDate);
//		System.out.println(new SimpleDateFormat("MMM-dd-yyyy").format(myDate));		
	}
}
