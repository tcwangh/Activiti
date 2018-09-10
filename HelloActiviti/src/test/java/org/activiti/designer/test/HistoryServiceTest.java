package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class HistoryServiceTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	private void deployProcess() {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("bookorder1.bpmn").deploy();
	}
	
	private String startAndComplete() throws InterruptedException {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("isbn", "123456");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bookorder_v2", variableMap);
		
		TaskService taskService =  activitiRule.getTaskService();
		ArrayList<Task> taskList = (ArrayList<Task>)taskService.createTaskQuery().processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<taskList.size();i++) {
			System.out.println("task id " + taskList.get(i).getId() + ", name " + taskList.get(i).getName() + ", " + 
					"def key " + taskList.get(i).getTaskDefinitionKey() + ", inst " + 
					taskList.get(i).getProcessInstanceId()) ;
		}
		System.out.println("Sleeping..");
		Thread.sleep(10000);
		System.out.println("Beak.");
		Task theTask = (Task) taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println("task id " + theTask.getId() + ", name " + theTask.getName() + ", " + 
				"def key " + theTask.getTaskDefinitionKey() + ", inst " + 
				theTask.getProcessInstanceId()) ;
		variableMap = new HashMap<String, Object>();
		variableMap.put("extraInfo", "Extra information");
		variableMap.put("isbn", "654321");
		taskService.complete(theTask.getId(), variableMap);
		return processInstance.getId();
	}
	
	@Test
	public void queryHistoryInstances() throws InterruptedException {
		deployProcess();
		String processInstanceID = startAndComplete();
		HistoryService historyService =  activitiRule.getHistoryService();
		HistoricProcessInstance historyProcessInstance = historyService.
				createHistoricProcessInstanceQuery().
				processInstanceId(processInstanceID).singleResult();
		assertNotNull(historyProcessInstance);
		assertEquals(processInstanceID,historyProcessInstance.getId());
		System.out.println("History process with definition id " + historyProcessInstance.getProcessDefinitionId() + 
				", instance id " + historyProcessInstance.getId() +
				", started at " + historyProcessInstance.getStartTime() + ", ended at " + 
				historyProcessInstance.getEndTime() + ", duration was " + 
				historyProcessInstance.getDurationInMillis());
	}
	
	@Test
	public void queryHistoricActivities() throws InterruptedException {
		deployProcess();
		String processInstanceID = startAndComplete();
		HistoryService historyService =  activitiRule.getHistoryService();
		List<HistoricActivityInstance> activityList =  historyService.createHistoricActivityInstanceQuery().list();
		System.out.println("activity size is " + activityList.size());
		for (HistoricActivityInstance historicActivityInstance:activityList){
			System.out.println("history activity " + historicActivityInstance.getActivityName() + 
					", type " + historicActivityInstance.getActivityType() + 
					", duration was " + historicActivityInstance.getDurationInMillis());
		}
	}

}
