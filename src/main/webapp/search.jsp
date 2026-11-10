<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Search" /></jsp:include>
<div class="card narrow">
	<h2>Search Customer</h2>
	<c:if test="${not empty status}"><div class="msg bad"><c:out value="${status}" /></div></c:if>
	<form action="findByEmail" method="get">
		<label>Customer Email</label>
		<input type="email" name="email" required />
		<button type="submit">Search</button>
	</form>
</div>
<jsp:include page="includes/footer.jsp" />
