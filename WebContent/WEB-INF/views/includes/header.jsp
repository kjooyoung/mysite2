<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<a href="<%=request.getContextPath()%>"><h1>MySite</h1></a>
	<ul>
		<li><a href="<%=request.getContextPath()%>/user?a=loginform">로그인</a></li>
		<li><a href="<%=request.getContextPath()%>/user?a=joinform">회원가입</a></li>
		<li><a href="<%=request.getContextPath()%>/user?a=modifyform">회원정보수정</a></li>
		<li><a href="<%=request.getContextPath()%>/user?a=logout">로그아웃</a></li>
		<li>님 안녕하세요 ^^;</li>
	</ul>
</div>