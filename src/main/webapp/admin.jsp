<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Admin" /></jsp:include>
<section class="hero">
	<span class="pill">&#9881; Control Center</span>
	<h1>Admin <em>Panel</em></h1>
	<p>Manage customers and monitor every transaction in the bank.</p>
</section>
<div class="grid">
	<a class="feature" href="findAll"><div class="ico">&#128101;</div><h3>View Customers</h3><p>See, edit and delete customers</p></a>
	<a class="feature" href="search.jsp"><div class="ico">&#128269;</div><h3>Search Customer</h3><p>Find a customer by email</p></a>
	<a class="feature" href="alltransactions"><div class="ico">&#128220;</div><h3>All Transactions</h3><p>Bank-wide transaction log</p></a>
</div>
<jsp:include page="includes/footer.jsp" />
