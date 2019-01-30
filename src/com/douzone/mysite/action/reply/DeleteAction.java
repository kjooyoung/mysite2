package com.douzone.mysite.action.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.ReplyDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		new ReplyDao().delete(Long.parseLong(request.getParameter("no")));
		long boardNo = Long.parseLong(request.getParameter("boardNo"));
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&no="+boardNo);
	}

}
