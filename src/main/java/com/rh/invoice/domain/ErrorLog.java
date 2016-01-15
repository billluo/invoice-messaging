package com.rh.invoice.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// use a new Table(name="xxrh_invoice_error",schema="APPS")
//object needs to be serialized to be sent as object in JMS
//or  transform the object(instances) in a JSON
@Entity
@Table(name="xxrh_batchexecution_dtl",schema="APPS")
public class ErrorLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue
	@Column(name ="RECORD_ID")
	Long recordId;
	
	@Column(name ="BUSINESS_PROCESS_NAME")
	String processName;
	
	@Column(name = "BUSINESS_PROCESS_ID1")
	String invoiceNumber;
	
	@Column(name ="ERROR_COLUMN")
	String errorColumn;
	
	@Column(name ="ERROR_VALUE")
	String errorValue;
	
	@Column(name ="ERROR_DATE_TIME")
	@Temporal(TemporalType.DATE)
	Date errorDate;
	
	@Column(name ="ERROR_DESCRIPTION")
	String description;
	
	@Column(name = "BUSINESS_PROCESS_ID5" )
	String fileName;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getProcessName() {
		return processName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getErrorColumn() {
		return errorColumn;
	}

	public void setErrorColumn(String errorColumn) {
		this.errorColumn = errorColumn;
	}

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((recordId == null) ? 0 : recordId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorLog other = (ErrorLog) obj;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		return true;
	}
	//for test purpose
	@Override
	public String toString() {
		return "ErrorLog [processName=" + processName + ", invoiceNumber="
				+ invoiceNumber + ", errorColumn=" + errorColumn
				+ ", errorValue=" + errorValue + ", description=" + description
				+ ", fileName=" + fileName + "]";
	}
}
