package com.rh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoice.repository.ErrorLogRepository;
import com.rh.invoicelog.InvoiceErrorCriteria;

@Service("errorService")
public class ErrorServiceImpl implements ErrorService{
	@Autowired
	@Qualifier("errorLogRepositoryImpl")
	ErrorLogRepository errorLogRepositoryImpl;
	
	@Override
	public List<ErrorLog> getErrorEntries() {
		return errorLogRepositoryImpl.findByCriteria(
				new InvoiceErrorCriteria());
	}

}
