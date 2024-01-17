package com.journaldev.jsf.filter;

import com.journaldev.jsf.entity.User;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		try {


			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession(false);
			String url = request.getRequestURI();

			User user = null;
			if(session != null){
				user = (User) session.getAttribute("validUser");
			}

			if(user == null){
				if(url.contains("logout")){
					response.sendRedirect(request.getContextPath() + "/login.xhtml");
				}else {
					filterChain.doFilter(servletRequest, servletResponse);
				}
			}else {
				if(url.contains("register")){
					response.sendRedirect(request.getContextPath() + "/admin.xhtml");
				} else if (url.contains("logout")) {
					session.invalidate();
					response.sendRedirect(request.getContextPath() + "/login.xhtml");
				}else{
					filterChain.doFilter(servletRequest, servletResponse);
				}
			}

//			HttpServletRequest reqt = (HttpServletRequest) request;
//			HttpServletResponse resp = (HttpServletResponse) response;
//			HttpSession ses = reqt.getSession(false);
//
//			String reqURI = reqt.getRequestURI();
//			if (reqURI.indexOf("/login.xhtml") >= 0
//					|| (ses != null && ses.getAttribute("username") != null)
//					|| reqURI.indexOf("/public/") >= 0
//					|| reqURI.contains("javax.faces.resource"))
//				chain.doFilter(request, response);
//			else
//				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}