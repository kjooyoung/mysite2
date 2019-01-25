package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		//login을 하지 않고 logout url을 직접 쳐서 들어오는 경우
		if(session != null && session.getAttribute("authuser") != null) {
			// logout처리
			session.removeAttribute("authuser");
			// browser 메모리에 있는 session id 날림
			session.invalidate();
		}
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
