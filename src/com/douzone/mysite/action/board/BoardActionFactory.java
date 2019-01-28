package com.douzone.mysite.action.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("list".equals(actionName)) {
			action = new ListAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		
		return action;
	}

}
