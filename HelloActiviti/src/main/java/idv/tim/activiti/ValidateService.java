package idv.tim.activiti;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ValidateService implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("execution id " + execution.getId());
		System.out.println("isbn " + execution.getVariable("isbn"));
		Long isbn = new Long( execution.getVariable("isbn").toString());
		System.out.println("receive isbn " + isbn);
		execution.setVariable("validatetime", new Date());
	}
	
	

}
