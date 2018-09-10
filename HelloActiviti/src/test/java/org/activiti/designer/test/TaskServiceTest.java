package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class TaskServiceTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	
	private void deployProcess() {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("bookorder.bpmn").deploy();
	}
	private String startProcessInstance() {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		
		Map<String,Object> variableMap = new HashMap<String,Object>();
		variableMap.put("isbn", "123456");
		String instanceId = runtimeService.startProcessInstanceByKey("bookorder_v2", variableMap).getProcessInstanceId();
		System.out.println("Create Process Instance " + instanceId);
		return instanceId;
	}
	
	@Test
	public void queryTask() {
		deployProcess();
		String instanceId = startProcessInstance();
		String instaneId = "";
		TaskService taskService =  activitiRule.getTaskService();
		ArrayList<Task> taskList = (ArrayList<Task>)taskService.createTaskQuery().processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<taskList.size();i++) {
			System.out.println("task id " + taskList.get(i).getId() + ", name " + taskList.get(i).getName() + ", " + 
					"def key " + taskList.get(i).getTaskDefinitionKey() + ", inst " + 
					taskList.get(i).getProcessInstanceId()) ;
			instaneId = taskList.get(i).getProcessInstanceId();
		}
		Task task = taskService.createTaskQuery().processInstanceId(instaneId).singleResult();
		System.out.println("task id " + task.getId() + ", name " + task.getName() + ", " + 
				"def key " + task.getTaskDefinitionKey() + ", inst " + 
				task.getProcessInstanceId()) ;
		//assertEquals ("Complete Order",task.getName());
	}
	
	@Test
	public void completeTask() {
		TaskService taskService =  activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().processInstanceId("137501").singleResult();
		taskService.complete(task.getId());
		System.out.println("Completed task " + task.getId());
	}

}
