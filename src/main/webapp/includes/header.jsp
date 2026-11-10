<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String role = (String) session.getAttribute("role");
	String ctx = request.getContextPath();
	String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
	if (uri == null) uri = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
	if (uri == null) uri = request.getRequestURI();
	String pageTitle = request.getParameter("title") == null ? "Digital Banking" : request.getParameter("title");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><%=pageTitle%> | Digital Banking</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="stylesheet" href="<%=ctx%>/css/style.css" />
</head>
<body>
	<div class="bg-grid"></div><div class="bg-blob"></div>
	<header class="topbar">
		<div class="inner">
			<div class="logo"><div class="mark">&#9670;</div><span>Digital<span class="t">Bank</span></span></div>
			<button class="menu-toggle" id="menuToggle" aria-label="Menu">&#9776;</button>
			<nav id="mainNav">
				<% if (role == null) { %>
				<a href="<%=ctx%>/index.jsp" class="<%=uri.endsWith("/index.jsp") ? "active" : ""%>">Home</a>
				<a href="<%=ctx%>/aboutus.jsp" class="<%=uri.endsWith("/aboutus.jsp") ? "active" : ""%>">About Us</a>
				<a href="<%=ctx%>/contactus.jsp" class="<%=uri.endsWith("/contactus.jsp") ? "active" : ""%>">Contact Us</a>
				<a href="<%=ctx%>/register.jsp" class="<%=uri.endsWith("/register.jsp") ? "active" : ""%> cta">Open Account</a>
				<a href="<%=ctx%>/login.jsp" class="<%=uri.endsWith("/login.jsp") ? "active" : ""%>">Login</a>
				<% } else if (role.equals("admin")) { %>
				<a href="<%=ctx%>/admin.jsp" class="<%=uri.endsWith("/admin.jsp") ? "active" : ""%>">Home</a>
				<a href="<%=ctx%>/findAll" class="<%=uri.endsWith("/findAll") ? "active" : ""%>">Customers</a>
				<a href="<%=ctx%>/search.jsp" class="<%=uri.endsWith("/search.jsp") ? "active" : ""%>">Search</a>
				<a href="<%=ctx%>/alltransactions" class="<%=uri.endsWith("/alltransactions") ? "active" : ""%>">All Transactions</a>
				<a href="<%=ctx%>/logout" class="<%=uri.endsWith("/logout") ? "active" : ""%> logout">Logout</a>
				<% } else { %>
				<a href="<%=ctx%>/dashboard" class="<%=uri.endsWith("/dashboard") ? "active" : ""%>">Dashboard</a>
				<a href="<%=ctx%>/deposit" class="<%=uri.endsWith("/deposit") ? "active" : ""%>">Deposit</a>
				<a href="<%=ctx%>/withdraw" class="<%=uri.endsWith("/withdraw") ? "active" : ""%>">Withdraw</a>
				<a href="<%=ctx%>/transfer" class="<%=uri.endsWith("/transfer") ? "active" : ""%>">Transfer</a>
				<a href="<%=ctx%>/history" class="<%=uri.endsWith("/history") ? "active" : ""%>">History</a>
				<a href="<%=ctx%>/viewprofile" class="<%=uri.endsWith("/viewprofile") ? "active" : ""%>">Profile</a>
				<a href="<%=ctx%>/logout" class="<%=uri.endsWith("/logout") ? "active" : ""%> logout">Logout</a>
				<% } %>
			</nav>
		</div>
	</header>
	<main>
