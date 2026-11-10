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

/** Handles deposit, withdraw and transfer forms. */
@WebServlet({ "/deposit", "/withdraw", "/transfer" })
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// opening the page: show the form with the current balance
		String page = request.getServletPath().substring(1) + ".jsp";
		showForm(request, response, page);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath().substring(1);
		String page = action + ".jsp";
		HttpSession session = request.getSession();
		long acc = (Long) session.getAttribute("accountNumber");

		BigDecimal amount;
		try {
			amount = new BigDecimal(request.getParameter("amount")).setScale(2, java.math.RoundingMode.DOWN);
		} catch (NumberFormatException | NullPointerException e) {
			request.setAttribute("status", "Please enter a valid amount.");
			request.setAttribute("ok", Boolean.FALSE);
			showForm(request, response, page);
			return;
		}
		if (amount.signum() <= 0) {
			request.setAttribute("status", "Amount must be greater than zero.");
			request.setAttribute("ok", Boolean.FALSE);
			showForm(request, response, page);
			return;
		}

		BankDAO dao = new BankDAO();
		String error;
		String success;

		if (action.equals("deposit")) {
			error = dao.deposit(acc, amount);
			success = "Deposited Rs. " + amount + " successfully.";
		} else if (action.equals("withdraw")) {
			error = dao.withdraw(acc, amount);
			success = "Withdrew Rs. " + amount + " successfully.";
		} else {
			long to;
			try {
				to = Long.parseLong(request.getParameter("toAccount").trim());
			} catch (NumberFormatException e) {
				request.setAttribute("status", "Please enter a valid receiver account number.");
				request.setAttribute("ok", Boolean.FALSE);
				showForm(request, response, page);
				return;
			}
			error = dao.transfer(acc, to, amount);
			success = "Transferred Rs. " + amount + " to A/C " + to + " successfully.";
		}

		request.setAttribute("status", error == null ? success : error);
		request.setAttribute("ok", error == null);
		showForm(request, response, page);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("email");
		request.setAttribute("customer", new BankDAO().fetchByEmail(email));
		request.getRequestDispatcher(page).forward(request, response);
	}
}
