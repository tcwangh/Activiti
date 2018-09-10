package idv.tim.activiti;

import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.tim.activiti.model.TestVO;

public class BasicVariableTypeReadDemo {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CreateProcessEngineDemo.class);
	public static void main(String[] args) {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		TaskService taskService = engine.getTaskService();
		
		Date d = (Date) taskService.getVariable ("task1", "arg1");
		slf4jLogger.debug("Date:" + d);
		TestVO obj = (TestVO) taskService.getVariable ("task1", "arg8");
		slf4jLogger.debug("Name:" + obj.getName());
		
	}

}
