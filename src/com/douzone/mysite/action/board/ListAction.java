package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.Criteria;
import com.douzone.mysite.vo.PageMaker;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kwd = "";
		
		if(request.getParameter("kwd") != null) {
			kwd += request.getParameter("kwd");
		}
		Criteria cri = new Criteria();
		
//		if(request.getParameter("page") != null) {
		int page = Integer.parseInt(request.getParameter("page"));
		cri.setPage(page);
//		}
		
		List<BoardVo> list = new BoardDao().getList(kwd, cri.getPageStart(), cri.getPerPageNum());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		int totalNum = new BoardDao().getTotalCount(kwd);
		pageMaker.setTotalCount(totalNum);
		
//		int totalPage = totalNum / cri.getPerPageNum();
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("pageMaker", pageMaker);
		request.setAttribute("totalNum", totalNum);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
