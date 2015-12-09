package com.rh.service;

import java.util.List;

import com.rh.invoice.domain.ErrorLog;

public interface ErrorService {
	public List<ErrorLog> getErrorEntries();
}
