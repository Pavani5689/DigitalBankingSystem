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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email").trim();
		String password = request.getParameter("password");

		HttpSession session = request.getSession(true);

		if (email.equals("admin@bank.com") && password.equals("admin@123")) {
			session.setAttribute("email", email);
			session.setAttribute("role", "admin");
			response.sendRedirect("admin.jsp");
			return;
		}

		Customer customer = new BankDAO().checkLogin(email, password);
		if (customer != null) {
			session.setAttribute("email", email);
			session.setAttribute("role", "customer");
			session.setAttribute("name", customer.getName());
			session.setAttribute("accountNumber", customer.getAccountNumber());
			response.sendRedirect("dashboard");
		} else {
			request.setAttribute("status", "Invalid email or password.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
