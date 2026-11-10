<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Deposit Money" /></jsp:include>
<div class="card narrow">
	<h2>Deposit Money</h2>
	<p style="margin-bottom:6px">Current balance: <b>Rs. <fmt:formatNumber value="${customer.balance}" minFractionDigits="2" maxFractionDigits="2" /></b> &middot; A/C ${customer.accountNumber}</p>
	<c:if test="${not empty status}"><div class="msg ${ok ? 'ok' : 'bad'}"><c:out value="${status}" /></div></c:if>
	<form action="deposit" method="post">
		
		<label>Amount (Rs.)</label>
		<input type="number" name="amount" min="0.01" step="0.01" required />
		<button type="submit">Deposit</button>
	</form>
</div>
<jsp:include page="includes/footer.jsp" />
