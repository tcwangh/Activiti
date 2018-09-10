package idv.tim.activiti;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;

import java.io.File;
import java.io.InputStream;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FileUtils;

public class DynamicProcess {
	
	public static void main(String[] args) {
		BpmnModel model = new BpmnModel();
		Process process = new Process();
		model.addProcess(process);
		process.setId("my-dynamic-process-1");
		process.addFlowElement(createStartEvent());
		process.addFlowElement(createEndEvent());
		
		process.addFlowElement(createSequenceFlow("start","end"));
		new BpmnAutoLayout(model).execute();
		ProcessEngine theEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = theEngine.getRepositoryService();
		String deploymentId = repositoryService.createDeployment().name("TestProcesses").addBpmnModel("dynamic-startend.bpmn", model).deploy().getId();
		System.out.println("Deployment id " + deploymentId);
		RuntimeService runtimeService = theEngine.getRuntimeService();
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("my-dynamic-process-1");
		
		// 6. Save process diagram to a file
		InputStream processDiagram = null;
		try {
			 processDiagram = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
			 FileUtils.copyInputStreamToFile(processDiagram, new File("target/my-dynamic-process-1.png"));
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		InputStream processBpmn = null;
		try {
			processBpmn = repositoryService.getResourceAsStream(deploymentId, "dynamic-startend.bpmn");
		    FileUtils.copyInputStreamToFile(processBpmn, new File("target/dynamic-startend.bpmn20.xml"));
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	   
	}
	
	protected static SequenceFlow createSequenceFlow(String from,String to) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		return flow;
	}
	
	protected static StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		return startEvent;
	}
	
	protected static EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		return endEvent;
	}

}
