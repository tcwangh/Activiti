package idv.tim.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

public class StartEndProcessExecutor {
	
	public static void main(String[] args) {
		ProcessEngine theEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = theEngine.getRepositoryService();
		String deploymentId = repositoryService.createDeployment().name("TestProcesses").addClasspathResource("startend.bpmn").deploy().getId();
		System.out.println("Deployment id " + deploymentId);
		RuntimeService runtimeService = theEngine.getRuntimeService();
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("startend");
		System.out.println("pid:" + processInstance.getId() + ",pdid="+ processInstance.getProcessDefinitionId());
	}
	

}
