package com.revature.models;

import java.io.InputStream;
import java.sql.Date;

/**
 * This concrete Reimbursement class can include additional fields that can be
 * used for extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 * <li>Description</li>
 * <li>Creation Date</li>
 * <li>Resolution Date</li>
 * <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

	private String description;
	private Date creationDate;
	private Date resolutionDate;
	private InputStream receipt;

	public Reimbursement() {
		super();
	}

	/**
	 * This includes the minimum parameters needed for the
	 * {@link com.revature.models.AbstractReimbursement} class. If other fields are
	 * needed, please create additional constructors.
	 */
	public Reimbursement(int id, Status status, int author, int resolver, double amount) {
		super(id, status, author, resolver, amount);
	}

	public Reimbursement(String description, Date creationDate, Date resolutionDate, InputStream receipt) {
		super();
		this.description = description;
		this.creationDate = creationDate;
		this.resolutionDate = resolutionDate;
		this.receipt = receipt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public InputStream getReceipt() {
		return receipt;
	}

	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}

}
