<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Dashboard" /></jsp:include>
<div class="balance">
	<small>Welcome, <c:out value="${customer.name}" /> &middot; A/C No. ${customer.accountNumber}</small>
	<div class="amount">Rs. <fmt:formatNumber value="${customer.balance}" minFractionDigits="2" maxFractionDigits="2" /></div>
	<small>Available balance</small>
</div>
<div class="grid">
	<a class="feature" href="deposit"><div class="ico">&#11015;&#65039;</div><h3>Deposit</h3><p>Add money to your account</p></a>
	<a class="feature" href="withdraw"><div class="ico">&#11014;&#65039;</div><h3>Withdraw</h3><p>Take money out</p></a>
	<a class="feature" href="transfer"><div class="ico">&#128257;</div><h3>Transfer</h3><p>Send to another account</p></a>
	<a class="feature" href="history"><div class="ico">&#128337;</div><h3>History</h3><p>All your transactions</p></a>
</div>
<div class="card">
	<h2>Recent Transactions</h2>
	<div class="table-wrap">
	<table>
		<tr><th>Date</th><th>Type</th><th>Amount</th><th>Balance</th><th>Description</th></tr>
		<c:forEach items="${recent}" var="t">
			<tr>
				<td><fmt:formatDate value="${t.txnDate}" pattern="dd MMM yyyy HH:mm" /></td>
				<td><span class="badge ${t.type == 'DEPOSIT' || t.type == 'TRANSFER_IN' ? 'in' : 'out'}">${t.type}</span></td>
				<td class="${t.type == 'DEPOSIT' || t.type == 'TRANSFER_IN' ? 'in' : 'out'}">${t.amount}</td>
				<td>${t.balanceAfter}</td>
				<td><c:out value="${t.description}" /></td>
			</tr>
		</c:forEach>
		<c:if test="${empty recent}"><tr><td colspan="5">No transactions yet.</td></tr></c:if>
	</table>
	</div>
</div>
<jsp:include page="includes/footer.jsp" />
