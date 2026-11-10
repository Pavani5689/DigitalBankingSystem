<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="History" /></jsp:include>
<div class="card">
	<h2>Transaction History</h2>
	<div class="table-wrap">
	<table>
		<tr><th>ID</th><th>Date</th><th>Type</th><th>Amount</th><th>Balance After</th><th>Description</th></tr>
		<c:forEach items="${txnList}" var="t">
			<tr>
				<td>${t.id}</td>
				<td><fmt:formatDate value="${t.txnDate}" pattern="dd MMM yyyy HH:mm" /></td>
				<td><span class="badge ${t.type == 'DEPOSIT' || t.type == 'TRANSFER_IN' ? 'in' : 'out'}">${t.type}</span></td>
				<td class="${t.type == 'DEPOSIT' || t.type == 'TRANSFER_IN' ? 'in' : 'out'}">${t.amount}</td>
				<td>${t.balanceAfter}</td>
				<td><c:out value="${t.description}" /></td>
			</tr>
		</c:forEach>
		<c:if test="${empty txnList}"><tr><td colspan="6">No transactions yet.</td></tr></c:if>
	</table>
	</div>
</div>
<jsp:include page="includes/footer.jsp" />
