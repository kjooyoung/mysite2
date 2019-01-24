package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuestbookVo vo = new GuestbookVo();
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setMessage(request.getParameter("message"));
		new GuestbookDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/guestbook?a=list");
	}

}
