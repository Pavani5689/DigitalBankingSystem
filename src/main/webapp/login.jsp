<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Login" /></jsp:include>
<div class="card narrow">
	<h2>Login</h2>
	<c:if test="${not empty status}"><div class="msg bad"><c:out value="${status}" /></div></c:if>
	<form action="login" method="post">
		<label>Email</label>
		<input type="email" name="email" required />
		<label>Password</label>
		<input type="password" name="password" required />
		<button type="submit">Login</button>
	</form>
	<p style="margin-top:16px">New here? <a href="register.jsp">Open an account</a></p>
</div>
<jsp:include page="includes/footer.jsp" />
