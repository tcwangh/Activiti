package idv.tim.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import idv.tim.activiti.model.LoanApplication;

public class CreateApplicationTask implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LoanApplication la = new LoanApplication();
		System.out.println(execution.getVariable("creditCheckOK"));
		System.out.println(execution.getVariables());
		la.setCreditCheckOK((Boolean)execution.getVariable("creditCheckOK"));
		la.setCustomerName((String)execution.getVariable("name"));
		la.setIncome((Long)execution.getVariable("income"));
		la.setRequestedAmount((Long)execution.getVariable("loanAmount"));
		la.setEmailAddress((String)execution.getVariable("emailAddress"));
		//execution.setVariable("loanApplication", la);
	}
	
	

}
