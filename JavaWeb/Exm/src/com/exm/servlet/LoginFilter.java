package com.exm.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	public static final String MAIN_JSP = "/Exm/Main.jsp";

	// public static final String LOGIN_JSP="/Exm/";
	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String path = req.getContextPath();
		String targetUri = uri.substring(path.length());
		HttpSession session = req.getSession(false);
		if (!("/Exm/Main.jsp").equals(targetUri)) {
			if (session == null || session.getAttribute("loginName") == null) {
				res.sendRedirect(MAIN_JSP);
				return;
			} else {
				chain.doFilter(req, res);
				return;
			}
		} else {
			chain.doFilter(req, res);
			return;
		}
	}

	public void destroy() {

	}
}
