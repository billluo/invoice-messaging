package com.rh.invoice.repository;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoice.repository.ErrorLogRepository;
import com.rh.invoicelog.ErrorLogCriteria;

@Repository
public class ErrorLogRepositoryImpl implements ErrorLogRepository {

    private EntityManager em;
	private ErrorLogCriteria invCriteria;
	/**
	 * @return the EntityManager bean
	 */
	public EntityManager getEm() {
		return em;
	}
	/**
	 * @return the invCriteria for search
	 */
	public ErrorLogCriteria getInvCriteria() {
		return invCriteria;
	}
//	@PersistenceUnit(invoicePersistenceUnit)--test environment	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public void setInvCriteria(ErrorLogCriteria invCriteria) {
		this.invCriteria = invCriteria;
	}

	static Logger logger1 = Logger.getLogger(ErrorLogRepositoryImpl.class.getName());
	//for test
	public ErrorLog findById(int recordId) {
		return em.find(ErrorLog.class, recordId);	
	}
	//for test
	@Override
	public ArrayList<ErrorLog> findByInvoiceNumber(String invoiceNbr){	
		TypedQuery<ErrorLog> query = this.em.createQuery("SELECT e From ErrorLog  e WHERE "
				+ "e.invoiceNumber = :invoiceNumber",ErrorLog.class);
		query.setParameter("invoiceNumber",invoiceNbr);
	
		return (ArrayList<ErrorLog>) query.getResultList();
	}

	@Override
	public ArrayList<ErrorLog> findByCriteria(ErrorLogCriteria criteria){
		setInvCriteria(criteria);;
		TypedQuery<ErrorLog> query = this.em.createQuery("SELECT e From ErrorLog  e "
				+ "WHERE TO_CHAR(e.errorDate,'DD-Mon-YYYY') = :errorDate ",ErrorLog.class);
		query.setParameter("errorDate",this.invCriteria.getCriteria());
		
		logger1.info(this.invCriteria.getCriteria());

		return (ArrayList<ErrorLog>) query.getResultList();
	}
}
