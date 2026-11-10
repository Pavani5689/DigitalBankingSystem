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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if ("admin".equals(session.getAttribute("role"))) {
			response.sendRedirect("admin.jsp");
			return;
		}

		BankDAO dao = new BankDAO();
		Customer customer = dao.fetchByEmail((String) session.getAttribute("email"));
		List<Transaction> recent = dao.fetchHistory(customer.getAccountNumber(), 5);

		request.setAttribute("customer", customer);
		request.setAttribute("recent", recent);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}
