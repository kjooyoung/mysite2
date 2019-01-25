package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authuser");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		System.out.println(password);
		if("".equals(request.getParameter("password"))) {
			System.out.println("비밀번호x");
			new UserDao().update(authUser.getNo(), name, gender);
		} else {
			new UserDao().updateAll(authUser.getNo(), name, password, gender);
		}
		
		WebUtils.redirect(request, response, request.getContextPath()+"/user?a=modifyform");
	}

}
