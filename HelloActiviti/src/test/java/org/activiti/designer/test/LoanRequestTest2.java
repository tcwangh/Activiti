package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class LoanRequestTest2 {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	private Deployment deployProcess() {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("loanrequest_v2.bpmn20.xml").deploy();
		System.out.println("Deployment id " + deployment.getId());
		return deployment;
	}
	
	@Test
	public void startFromSubmit() {
		deployProcess();
		ProcessDefinition definition = activitiRule.getRepositoryService()
				.createProcessDefinitionQuery().processDefinitionKey("loanrequest_v2").latestVersion().singleResult();
		assertNotNull(definition);
		
		FormService formService = activitiRule.getFormService();
		List<FormProperty> formList = formService.getStartFormData(definition.getId()).getFormProperties();
		assertEquals(4,formList.size());
		
		Map<String,String> formProperties = new HashMap<String,String>();
		formProperties.put("name", "Miss Piggy");
		formProperties.put("emailAddress", "piggy@localhost");
		formProperties.put("income", "400");
		formProperties.put("loanAmount", "100");
		ProcessInstance processInstance = formService.submitStartFormData(definition.getId(), formProperties);
		
		List<HistoricDetail> historyVariables = activitiRule.getHistoryService().createHistoricDetailQuery()
				.processInstanceId(processInstance.getProcessInstanceId())
				.formProperties().list();
		assertNotNull(historyVariables);
		assertEquals(4,historyVariables.size());
		
		for (int i=0;i<historyVariables.size();i++) {
			HistoricFormProperty formProperty = (HistoricFormProperty) historyVariables.get(i);
			System.out.println("formProperty[" + i + "] id " + formProperty.getPropertyId() + " value " + formProperty.getPropertyValue());
		}
		
		//HistoricFormProperty formProperty = (HistoricFormProperty) historyVariables.get(0);
		//System.out.println("formProperty[0] id " + formProperty.getPropertyId() + " value " + formProperty.getPropertyValue());
		//assertEquals("loanAmount",formProperty.getPropertyId());
		//assertEquals("100",formProperty.getPropertyValue());
		
		//formProperty = (HistoricFormProperty) historyVariables.get(1);
		//System.out.println("formProperty[1] id " + formProperty.getPropertyId() + " value " + formProperty.getPropertyValue());
		//assertEquals("income",formProperty.getPropertyId());
		//assertEquals("400",formProperty.getPropertyValue());
		
	}

}
