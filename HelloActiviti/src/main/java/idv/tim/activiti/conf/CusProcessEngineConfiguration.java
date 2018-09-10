package idv.tim.activiti.conf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandContextInterceptor;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.interceptor.LogInterceptor;

import idv.tim.activiti.interceptor.InterceptorA;

public class CusProcessEngineConfiguration extends ProcessEngineConfigurationImpl{
	
	private int executionTimeout = 30;
	public CusProcessEngineConfiguration() {
		
	}
	public int getExecutionTimeout() {
		return executionTimeout;
	}
	public void setExecutionTimeout(int executionTimeout) {
		this.executionTimeout = executionTimeout;
	}
	@Override
	protected CommandInterceptor createTransactionInterceptor() {
		return null;
	}
	@Override
	protected Collection< ? extends CommandInterceptor> getDefaultCommandInterceptors() {
	    List<CommandInterceptor> interceptors = new ArrayList<CommandInterceptor>();
	    interceptors.add(new LogInterceptor());
	    
	    CommandInterceptor transactionInterceptor = createTransactionInterceptor();
	    if (transactionInterceptor != null) {
	      interceptors.add(transactionInterceptor);
	    }
	    interceptors.add(new InterceptorA());
	    interceptors.add(new CommandContextInterceptor(commandContextFactory, this));
	    return interceptors;
	  }
	

}
