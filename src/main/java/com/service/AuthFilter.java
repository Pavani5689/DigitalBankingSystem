package com.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BankDAO;
import com.model.Customer;
import com.model.Transaction;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/** Keeps pages private: public pages are open, admin pages need the admin role, everything else needs a login. */
@WebFilter("/*")
public class AuthFilter implements Filter {

	private static final String[] PUBLIC = { "/", "/index.jsp", "/login", "/login.jsp", "/register", "/register.jsp",
			"/aboutus.jsp", "/contactus.jsp", "/css/" };
	private static final String[] ADMIN = { "/admin.jsp", "/findAll", "/viewcustomers.jsp", "/findByEmail",
			"/search.jsp", "/viewcustomer.jsp", "/edituser", "/edit.jsp", "/delete", "/alltransactions",
			"/alltransactions.jsp" };

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String path = request.getRequestURI().substring(request.getContextPath().length());

		for (String p : PUBLIC) {
			if (path.equals(p) || (p.endsWith("/") && !p.equals("/") && path.startsWith(p))) {
				chain.doFilter(req, res);
				return;
			}
		}

		HttpSession session = request.getSession(false);
		String role = session == null ? null : (String) session.getAttribute("role");

		if (role == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		for (String p : ADMIN) {
			if (path.equals(p) && !role.equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/dashboard");
				return;
			}
		}
		chain.doFilter(req, res);
	}
}
