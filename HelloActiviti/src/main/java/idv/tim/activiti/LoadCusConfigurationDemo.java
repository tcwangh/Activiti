package idv.tim.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.tim.activiti.conf.CusProcessEngineConfiguration;

public class LoadCusConfigurationDemo {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(LoadCusConfigurationDemo.class);
    public static void main(String[] args) {
		
    	CusProcessEngineConfiguration config 
			= (CusProcessEngineConfiguration) ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("cus-activiti.cfg.xml");
		slf4jLogger.debug("[Database Type]:" + config.getDatabaseType());
		slf4jLogger.debug("[Jdbc Url]:" + config.getJdbcUrl());
		slf4jLogger.debug("[Process Engine Name]:" + config.getProcessEngineName());
		slf4jLogger.debug("[ExecutionTimeOut]:" + config.getExecutionTimeout());
		
		ProcessEngine engine = config.buildProcessEngine();
		
		RepositoryService repositoryService = engine.getRepositoryService();
		RuntimeService runtimeService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		IdentityService identityService = engine.getIdentityService();
		ManagementService managementService = engine.getManagementService();
		HistoryService historyService = engine.getHistoryService();
		
		slf4jLogger.debug("[RepositoryService]:" + repositoryService);
		slf4jLogger.debug("[RuntimeService]:" + runtimeService);
		slf4jLogger.debug("[TaskService]:" + taskService);
		slf4jLogger.debug("[IdentityService]:" + identityService);
		slf4jLogger.debug("[ManagementService]:" + managementService);
		slf4jLogger.debug("[HistoryService]:" + historyService);
		
		engine.close();

	}

}
