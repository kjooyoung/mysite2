package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.GuestbookDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long no = Long.parseLong(request.getParameter("no"));
		String password = new GuestbookDao().getPassword(no);
		if(request.getParameter("password").equals(password)) {
			new GuestbookDao().delete(no);
		}
		
		WebUtils.redirect(request, response, request.getContextPath()+"/guestbook?a=list");
	}

}
