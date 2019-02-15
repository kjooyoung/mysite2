package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String sPage = request.getParameter("p");
		if("".equals(sPage)) {
			sPage = "1";
		}
		if(sPage.matches("\\d*") == false) {
			sPage = "1";
		}
		int page = Integer.parseInt(sPage);
		
		List<GuestbookVo> list = new GuestbookDao().getList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		String jsonString = jsonObject.toString();
		
		response.getWriter().println(jsonString);
	}

}
