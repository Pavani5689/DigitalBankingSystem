<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Customers" /></jsp:include>
<div class="card">
	<h2>All Customers</h2>
	<div class="table-wrap">
	<table>
		<tr><th>ID</th><th>A/C No.</th><th>Name</th><th>Email</th><th>Age</th><th>Gender</th><th>Mobile</th><th>Balance</th><th>Edit</th><th>Delete</th></tr>
		<c:forEach items="${custList}" var="c">
			<tr>
				<td>${c.id}</td>
				<td>${c.accountNumber}</td>
				<td><c:out value="${c.name}" /></td>
				<td><c:out value="${c.email}" /></td>
				<td>${c.age}</td>
				<td>${c.gender}</td>
				<td><c:out value="${c.mobile}" /></td>
				<td><fmt:formatNumber value="${c.balance}" minFractionDigits="2" maxFractionDigits="2" /></td>
				<td><a href="edituser?id=${c.id}">Edit</a></td>
				<td><a href="delete?id=${c.id}" onclick="return confirm('Delete this customer and all their transactions?')">Delete</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty custList}"><tr><td colspan="10">No customers yet.</td></tr></c:if>
	</table>
	</div>
</div>
<jsp:include page="includes/footer.jsp" />
