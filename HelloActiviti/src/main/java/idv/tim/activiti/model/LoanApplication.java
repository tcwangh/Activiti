package idv.tim.activiti.model;

import java.io.Serializable;

public class LoanApplication implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean creditCheckOK = false;
	private String customerName = "";
	private Long income = 0L;
	private Long requestedAmount = 0L;
	private String emailAddress = "";
	public boolean isCreditCheckOK() {
		return creditCheckOK;
	}
	public void setCreditCheckOK(boolean creditCheckOK) {
		this.creditCheckOK = creditCheckOK;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public Long getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(Long requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
