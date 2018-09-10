package org.activiti.designer.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class JavaBpmnTest{
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	private void deployProcess() {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("bookorder2.bpmn").deploy();
	}
	private ProcessInstance startProcessInstance() {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String,Object> variableMap = new HashMap<String,Object>();
		variableMap.put("isbn", "123456");
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("bookorder_v3", variableMap);
		System.out.println("Create Process Instance " + instance.getProcessInstanceId());
		return instance;
	}
	@Test
	public void executeJavaService() {
		deployProcess();
		ProcessInstance processInstance = startProcessInstance();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Date validatetime = (Date) runtimeService.getVariable(processInstance.getId(), "validatetime");
		System.out.println("validatetime is " + validatetime);
		
	}

}
