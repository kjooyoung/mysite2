package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

import net.sf.json.JSONObject;

public class AjaxInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuestbookVo vo = new GuestbookVo();
		System.out.println(request.getParameter("name"));
		vo.setName(request.getParameter("name"));
		vo.setMessage(request.getParameter("message"));
		vo.setPassword(request.getParameter("password"));
		vo.setNo(new GuestbookDao().insert(vo));
		
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(vo);
		String jsonString = jsonObject.toString();
		
		response.getWriter().println(jsonString);
	}

}
