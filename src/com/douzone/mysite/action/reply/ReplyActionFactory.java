package com.douzone.mysite.action.reply;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;

public class ReplyActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		} 
		
		return action;
	}

}
