package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

import net.sf.json.JSONArray;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestbookVo> list = new GuestbookDao().getList();
		response.setContentType("application/json; charset=utf-8");
		JSONArray jsonObject = JSONArray.fromObject(list);
		String jsonString = jsonObject.toString();
		
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

}
