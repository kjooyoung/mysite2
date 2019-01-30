package com.douzone.mysite.action.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.ReplyDao;
import com.douzone.mysite.vo.ReplyVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long boardNo = Long.parseLong(request.getParameter("boardNo"));
		ReplyVo vo = new ReplyVo();
		vo.setContents(request.getParameter("contents"));
		vo.setBoardNo(boardNo);
		vo.setUserNo(Long.parseLong(request.getParameter("userNo")));
		new ReplyDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&no="+boardNo);
	}

}
