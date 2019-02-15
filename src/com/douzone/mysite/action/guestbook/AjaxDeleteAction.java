package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestbookDao;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		String password = request.getParameter("password");
		long no = Long.parseLong(request.getParameter("no"));
		if(password.equals(new GuestbookDao().getPassword(no))){
			new GuestbookDao().delete(no);
			map.put("result", no);
		} else {
			map.put("result", false);
		}
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		String jsonString = jsonObject.toString();
		
		response.getWriter().println(jsonString);
	}

}
