<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(board.contents, newline, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board?a=list">글목록</a>
					<c:if test="${authuser.no eq board.userNo }">
						<a href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${board.no}">글수정</a>
					</c:if>
					<c:if test="${authuser.no ne null }">
					<a href="${pageContext.servletContext.contextPath }/board?a=writeform&no=${board.no}">답글쓰기</a>
					</c:if>
				</div>
				<div id="reply">
					<form method="post" action="${pageContext.servletContext.contextPath }/reply">
						<input type="hidden" name="a" value="write">
						<input type="hidden" name="boardNo" value="${board.no}">
						<input type="hidden" name="userNo" value="${authuser.no }">
						<table class="tbl-ex">
							<tr>
								<th>댓글</th>
							</tr>
						</table>
						<c:if test="${authuser ne null }">
						<table class="tbl-ex">
							<tr>
								<td>
									<textarea id="cont" name="contents"></textarea>
								</td>
							</tr>
						</table>
						<div class="bottom">
							<input type="submit" value="등록">
						</div>
						</c:if>
					</form>
					<c:forEach items="${reply }" var="vo">
					<table class="tbl-ex">
						<tr>
							<td>${vo.userName }
								<c:if test="${authuser.no eq vo.userNo }">
								<div id="delete">
									<a href="${pageContext.servletContext.contextPath }/reply?a=delete&no=${vo.no}&boardNo=${board.no}">
										<img alt="" style="width : 20px" src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">
									</a>
								</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>${vo.contents }</td>
<%-- 							<td>${vo.writeDate }</td> --%>
						</tr>
					</table>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>