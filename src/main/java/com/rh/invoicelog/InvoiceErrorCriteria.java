package com.rh.invoicelog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceErrorCriteria implements ErrorLogCriteria	{

	@Override
	public String getCriteria() {		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		
		return "20-Nov-2015";
		
//		return dateFormat.format(date);	
	}
}
