package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestRuntimeService {

	private static String filename = "D:\\ctwang\\workspacemars\\HelloActiviti\\src\\main\\resources\\diagrams\\bookorder.bpmn";
	private static RuntimeService runtimeService;
	
	@BeforeClass
	public static void init() {
		ProcessEngine processEngine = ProcessEngineConfiguration.
					createStandaloneInMemProcessEngineConfiguration().
					buildProcessEngine();
		//RepositoryService repositoryService = processEngine.getRepositoryService();
		//repositoryService.createDeployment().addClasspathResource("resources/bookorder.bpmn").deploy();
		runtimeService = processEngine.getRuntimeService();
	}
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		//repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml",
		//		new FileInputStream(filename)).deploy();
		RuntimeService runtimeService2 = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		System.out.println(runtimeService);
		System.out.println(runtimeService2);
	}
}