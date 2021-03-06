package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("authuser");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		if("".equals(request.getParameter("no"))) {
			new BoardDao().insert(user.getNo(), title, contents);
		}else {
			long no = Long.parseLong(request.getParameter("no"));
			new BoardDao().updateOrder(no);
			new BoardDao().insert(no, title, contents, user.getNo());
		}
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=list&page=1");
	}

}
