package idv.tim.activiti.listener;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ExecutionListenerExecution;
import idv.tim.activiti.utils.EventUtil;

public class GossipAboutTransition {
	
	public void gossip(ExecutionListenerExecution execution) {
		PvmTransition transition = (PvmTransition) execution.getEventSource();
		System.out.println("Did you hear " + transition.getSource().getId() + 
				" transitioned to " + transition.getDestination().getId());
		EventUtil.addEvent(execution, "transition");
	}
}
