package org.activiti.designer.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class RepositoryServiceTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	@Test
	public void deleteDeployment () throws InterruptedException {
		
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		String deploymentId = repositoryService.createDeployment().
				addClasspathResource("bookorder1.bpmn").deploy().getId();
		System.out.println("Deployment id " + deploymentId);
		
		ArrayList<Deployment> deploymentList = (ArrayList<Deployment>)repositoryService.createDeploymentQuery().processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<deploymentList.size();i++) {
			System.out.println("Found Deployment[" + i + "] id " + deploymentList.get(i).getId() + 
					" deployment at " + deploymentList.get(i).getDeploymentTime());
		}
		
		ArrayList<ProcessDefinition> processDefinitionList = (ArrayList<ProcessDefinition>)repositoryService.createProcessDefinitionQuery().
				processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<processDefinitionList.size();i++) {
			System.out.println("Found Process Definition [" + i + "] id " + processDefinitionList.get(i).getId() + 
					" ,name " + processDefinitionList.get(i).getName() + " ,version " + processDefinitionList.get(i).getVersion()
					 + ", " + processDefinitionList.get(i).getDeploymentId());
		}
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("isbn", "123456");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bookorder_v2", variableMap);
		Thread.sleep(3000);
		processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println("Process Definition id " +  processInstance.getId() + 
				", process definition " + processInstance.getProcessDefinitionId());
		repositoryService.deleteDeployment(deploymentId,true);
		
		deploymentList = (ArrayList<Deployment>)repositoryService.createDeploymentQuery().processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<deploymentList.size();i++) {
			System.out.println("After Delete Deployment[" + i + "] id " + deploymentList.get(i).getId() + 
					" deployment at " + deploymentList.get(i).getDeploymentTime());
		}
		
	}

}
