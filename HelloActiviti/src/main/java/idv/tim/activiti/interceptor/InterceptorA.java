package idv.tim.activiti.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterceptorA extends AbstractCommandInterceptor{
	private static Logger logger = LoggerFactory.getLogger(InterceptorA.class);
	
	@Override
	public <T> T execute(CommandConfig config, Command<T> command) {
		logger.trace("This is interceptor A " + command.getClass().getName());
		return next.execute(config, command);
	}

}
