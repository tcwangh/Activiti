package idv.tim.activiti;

import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreateProcessEngineDemo {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CreateProcessEngineDemo.class);
    public static void main(String[] args) {
		
		//ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		//slf4jLogger.debug("[Database Type]:" + config.getDatabaseType());
		//slf4jLogger.debug("[Jdbc Url]:" + config.getJdbcUrl());
		//slf4jLogger.debug("[Process Engine Name]:" + config.getProcessEngineName());
		//ProcessEngine engine = config.buildProcessEngine();
		//slf4jLogger.debug("[Built Process Engine]:" + engine);
		
		//ProcessEngines.init();
		ProcessEngine theEngine = ProcessEngines.getDefaultProcessEngine();
		
		//slf4jLogger.debug("[Built Process Engine]:" + theEngine);
		Map<String,ProcessEngine> engines = ProcessEngines.getProcessEngines();
		slf4jLogger.debug("[Process Engine size]:" + engines.size());
		slf4jLogger.debug("[Default Process Engine]:" + engines.get("default"));
		ProcessEngines.destroy();
		engines = ProcessEngines.getProcessEngines();
		slf4jLogger.debug("[Process Engine size After Destroy]:" + engines.size());

	}

}
