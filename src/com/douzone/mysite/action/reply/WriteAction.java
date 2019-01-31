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
		ReplyDao dao = new ReplyDao();
		ReplyVo vo = new ReplyVo();
		vo.setContents(request.getParameter("contents"));
		vo.setBoardNo(boardNo);
		vo.setUserNo(Long.parseLong(request.getParameter("userNo")));
		if("true".equals(request.getParameter("reply"))) {
			//대댓글 달기
			long no = Long.parseLong(request.getParameter("no"));
			ReplyVo momReply = dao.getReply(no);
			ReplyVo refReply = dao.getRefOrderNo(momReply);
			ReplyVo bigMomReply = dao.getMomRefOrderNo(momReply);
			System.out.println(refReply.getContents());
			if(refReply.getOrderNo()==0) {
				if(bigMomReply.getOrderNo()!=0) {
					System.out.println("안되는자리");
					dao.updateOrder(bigMomReply);
					bigMomReply.setDepth(bigMomReply.getDepth()+1);
					dao.insert(bigMomReply, vo);
				}else {
					dao.insert(momReply.getNo(), vo);
				}
			} else {
				dao.updateOrder(refReply);
				dao.insert(refReply, vo);
			}
		} else {
			dao.insert(vo);
		}
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&no="+boardNo);
	}

}
