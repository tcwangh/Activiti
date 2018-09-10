package org.activiti.designer.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

import idv.tim.activiti.model.LoanApplication;

public class LoanRequestTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	private Deployment deployProcess() {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("loanrequest_v0.bpmn20.xml").deploy();
		System.out.println("Deployment id " + deployment.getId());
		return deployment;
	}
	@Test
	public void creditCheckTrue() {
		//deployProcess();
		Map<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put("name", "Miss Piggy");
		processVariables.put("income", 1001L);
		processVariables.put("loanAmount", 101L);
		processVariables.put("emailAddress", "miss.piggy@localhost");
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("loanrequest_v0", processVariables);
		
		List<HistoricDetail> historyVariables = activitiRule.getHistoryService()
				.createHistoricDetailQuery().processInstanceId(processInstance.getProcessInstanceId())
													.variableUpdates().orderByVariableName().asc().list();
		assertNotNull(historyVariables);
		
		for (int i=0;i<historyVariables.size();i++) {
			 
			HistoricVariableUpdate loanAppUpdate = (HistoricVariableUpdate) historyVariables.get(i);
			System.out.println("task id " + historyVariables.get(i).getTaskId()+ " variable name " + loanAppUpdate.getVariableName() + 
					", type " + loanAppUpdate.getVariableTypeName() +
					", value " + loanAppUpdate.getValue() + ", time " +  loanAppUpdate.getTime());
			if ("loanApplication".equals(loanAppUpdate.getVariableName())){
				LoanApplication la = (LoanApplication)loanAppUpdate.getValue();
				System.out.println("la's email address " + la.getEmailAddress());
			}
		}
		
	}
	
	

}
