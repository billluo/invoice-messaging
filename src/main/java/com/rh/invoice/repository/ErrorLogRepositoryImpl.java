package com.rh.invoice.repository;

import javax.persistence.Query;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoice.repository.ErrorLogRepository;
import com.rh.invoicelog.ErrorLogCriteria;

@Repository
public class ErrorLogRepositoryImpl implements ErrorLogRepository {
//	@PersistenceUnit(invoicePersistenceUnit)
	@PersistenceContext
    private EntityManager em;
	private ErrorLogCriteria invCriteria;
	
	static Logger logger1 = Logger.getLogger(ErrorLogRepositoryImpl.class.getName());
	//for test
	public ErrorLog findById(int recordId) {
		return em.find(ErrorLog.class, recordId);	
	}
	//for test
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ErrorLog> findByInvoiceNumber(String invoiceNbr){	
		Query query = this.em.createQuery("SELECT e From ErrorLog  e WHERE "
				+ "e.invoiceNumber = :invoiceNumber");
		query.setParameter("invoiceNumber",invoiceNbr);
	
		return (ArrayList<ErrorLog>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ErrorLog> findByCriteria(ErrorLogCriteria criteria){
		this.invCriteria =criteria;
		Query query = this.em.createQuery("SELECT e From ErrorLog  e "
				+ "WHERE TO_CHAR(e.errorDate,'DD-Mon-YYYY') = :errorDate ");
		query.setParameter("errorDate",this.invCriteria.getCriteria());
		
		logger1.info(this.invCriteria.getCriteria());

		return (ArrayList<ErrorLog>) query.getResultList();
	}
}
