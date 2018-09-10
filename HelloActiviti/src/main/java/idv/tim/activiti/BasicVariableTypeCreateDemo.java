package idv.tim.activiti;

import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.tim.activiti.model.TestVO;

public class BasicVariableTypeCreateDemo {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CreateProcessEngineDemo.class);
	public static void main(String[] args) {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		TaskService taskService = engine.getTaskService();
		taskService.deleteTask("task1", true);
		Task task1=taskService.newTask("task1");
		taskService.saveTask(task1);
		Date d = new Date();
		short s = 3;
		taskService.setVariable(task1.getId(), "arg0", false); //boolean
		taskService.setVariable(task1.getId(), "arg1", d); //Date
		taskService.setVariable(task1.getId(), "arg2", 1.5D); //Double
		taskService.setVariable(task1.getId(), "arg3", 2); //Integer
		taskService.setVariable(task1.getId(), "arg4", 10L); //Long
		taskService.setVariable(task1.getId(), "arg5", null); //Null
		taskService.setVariable(task1.getId(), "arg6", s); //Short
		taskService.setVariable(task1.getId(), "arg7", "test"); //String
		taskService.setVariable(task1.getId(), "arg8", new TestVO("tcwangh")); //Object
		
		
	}

}
