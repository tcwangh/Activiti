package org.activiti.designer.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-test-application-context.xml"})
public class SpringTest {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void simpleSpringTest() {
				
		Map<String,Object> variableMap = new HashMap<String,Object>();
		variableMap.put("isbn", 123456L);
		runtimeService.startProcessInstanceByKey("bookorder_v2", variableMap);
		ArrayList<Task> taskList = (ArrayList<Task>)taskService.createTaskQuery().processDefinitionKey("bookorder_v2").list();
		for (int i=0;i<taskList.size();i++) {
			System.out.println("task id " + taskList.get(i).getId() + ", name " + taskList.get(i).getName() + ", " + 
					"def key " + taskList.get(i).getTaskDefinitionKey() + ", inst " + 
					taskList.get(i).getProcessInstanceId()) ;
		}
		//System.out.println("task id " + task.getId() + ", name " + task.getName() + ", " + 
		//		"def key " + task.getTaskDefinitionKey() + ", inst " + 
		//		task.getProcessInstanceId()) ;
		//assertEquals("Complete Order" , task.getName());
		System.out.println(runtimeService.createProcessInstanceQuery().count());
	}

}
