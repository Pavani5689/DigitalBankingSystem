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

@WebServlet("/edituser")
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// open the edit form with the saved details
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("customer", new BankDAO().fetchById(id));
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	// save the changes
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer c = new Customer();
		c.setId(Integer.parseInt(request.getParameter("id")));
		c.setName(request.getParameter("name").trim());
		c.setEmail(request.getParameter("email").trim());
		c.setAge(Integer.parseInt(request.getParameter("age")));
		c.setGender(request.getParameter("gender"));
		c.setMobile(request.getParameter("mobile").trim());
		c.setAddress(request.getParameter("address").trim());

		new BankDAO().editCustomer(c);
		response.sendRedirect("findAll");
	}
}
