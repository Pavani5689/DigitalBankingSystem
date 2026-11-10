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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BankDAO dao = new BankDAO();
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");

		String email = request.getParameter("email").trim();
		String password = request.getParameter("password");

		if (dao.emailExists(email)) {
			request.setAttribute("status", "This email is already registered.");
			request.setAttribute("ok", Boolean.FALSE);
			dispatcher.forward(request, response);
			return;
		}

		BigDecimal opening;
		int age;
		try {
			opening = new BigDecimal(request.getParameter("openingDeposit"));
			age = Integer.parseInt(request.getParameter("age"));
		} catch (NumberFormatException e) {
			request.setAttribute("status", "Please enter valid numbers for age and opening deposit.");
			request.setAttribute("ok", Boolean.FALSE);
			dispatcher.forward(request, response);
			return;
		}
		if (opening.signum() < 0) {
			request.setAttribute("status", "Opening deposit cannot be negative.");
			request.setAttribute("ok", Boolean.FALSE);
			dispatcher.forward(request, response);
			return;
		}

		Customer c = new Customer();
		c.setName(request.getParameter("name").trim());
		c.setEmail(email);
		c.setPassword(password);
		c.setAge(age);
		c.setGender(request.getParameter("gender"));
		c.setMobile(request.getParameter("mobile").trim());
		c.setAddress(request.getParameter("address").trim());

		boolean saved = dao.saveCustomer(c, opening);
		if (saved) {
			request.setAttribute("status", "Account created successfully. You can login now.");
			request.setAttribute("ok", Boolean.TRUE);
		} else {
			request.setAttribute("status", "Error while creating the account.");
			request.setAttribute("ok", Boolean.FALSE);
		}
		dispatcher.forward(request, response);
	}
}
