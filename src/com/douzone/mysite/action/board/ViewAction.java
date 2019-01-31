package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.repository.ReplyDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.ReplyVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long no = Long.parseLong(request.getParameter("no"));
		request.setAttribute("board", new BoardDao().getBoard(no));
		request.setAttribute("reply", new ReplyDao().getList(no));
		new BoardDao().updateHit(no);
//		Cookie[] cookies =request.getCookies();
//		if(cookies != null) {
//			for(Cookie c : cookies) {
//				System.out.println(c.getName());
//			}
//		}
		List<ReplyVo> list = new ReplyDao().getList(no);
		for(ReplyVo vo : list) {
			System.out.println(vo);
		}
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
