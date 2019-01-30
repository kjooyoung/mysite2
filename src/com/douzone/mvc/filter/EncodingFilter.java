package com.douzone.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/")
public class EncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Encoding Filter Initialized...");
		
		//web.xml의 filter 정의에서 init-param으로 설정한 encoding 값 가져옴
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null) {// web.xml에 encoding을 설정하지 않은 경우 디폴트 값으로 UTF-8 설정
			encoding = "UTF-8";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request 처리 */
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		
		/* response 처리 */
	}
	
	public void destroy() {

	}
}
