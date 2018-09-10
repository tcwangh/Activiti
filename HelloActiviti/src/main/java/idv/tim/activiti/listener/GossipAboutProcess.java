package idv.tim.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import idv.tim.activiti.utils.EventUtil;

public class GossipAboutProcess  implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Did you know the following process event occurred = " + execution.getEventName());
		EventUtil.addEvent(execution, "process");
	}
	
	

}
