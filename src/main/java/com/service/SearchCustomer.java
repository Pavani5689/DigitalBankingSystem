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

@WebServlet("/findByEmail")
public class SearchCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Customer customer = new BankDAO().fetchByEmail(request.getParameter("email").trim());

		if (customer.getId() == 0) {
			request.setAttribute("status", "Customer not found.");
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} else {
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("viewcustomer.jsp").forward(request, response);
		}
	}
}
