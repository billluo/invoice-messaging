package com.rh.invoicelog;

import java.util.List;

import com.rh.invoice.domain.ErrorLog;

public interface ReportStrategy {
	public void createReport(List<ErrorLog> errorLogs);
}
