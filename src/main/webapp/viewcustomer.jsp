<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Customer" /></jsp:include>
<div class="card narrow">
	<h2>Customer Details</h2>
	<div class="profile-row"><span>Name</span><span><c:out value="${customer.name}" /></span></div>
	<div class="profile-row"><span>Account Number</span><span>${customer.accountNumber}</span></div>
	<div class="profile-row"><span>Email</span><span><c:out value="${customer.email}" /></span></div>
	<div class="profile-row"><span>Age</span><span>${customer.age}</span></div>
	<div class="profile-row"><span>Gender</span><span>${customer.gender}</span></div>
	<div class="profile-row"><span>Mobile</span><span><c:out value="${customer.mobile}" /></span></div>
	<div class="profile-row"><span>Address</span><span><c:out value="${customer.address}" /></span></div>
	<div class="profile-row"><span>Balance</span><span>Rs. <fmt:formatNumber value="${customer.balance}" minFractionDigits="2" maxFractionDigits="2" /></span></div>
	<a class="btn" href="edituser?id=${customer.id}">Edit</a>
	<a class="btn danger" href="delete?id=${customer.id}" onclick="return confirm('Delete this customer and all their transactions?')">Delete</a>
</div>
<jsp:include page="includes/footer.jsp" />
