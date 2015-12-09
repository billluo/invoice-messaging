package com.rh.invoice.repository;

import java.util.ArrayList;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoicelog.ErrorLogCriteria;

public interface ErrorLogRepository {
	
	public ErrorLog findById(int id) ;
	public ArrayList<ErrorLog> findByInvoiceNumber(String invoiceNbr);
	public ArrayList<ErrorLog> findByCriteria(ErrorLogCriteria criteria);

}
