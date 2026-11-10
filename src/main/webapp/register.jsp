<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Open" /></jsp:include>
<div class="card narrow">
	<h2>Open a New Account</h2>
	<c:if test="${not empty status}"><div class="msg ${ok ? 'ok' : 'bad'}"><c:out value="${status}" /></div></c:if>
	<form action="register" method="post">
		<label>Full Name</label>
		<input type="text" name="name" required />
		<label>Email</label>
		<input type="email" name="email" required />
		<label>Password</label>
		<input type="password" name="password" minlength="4" required />
		<label>Age</label>
		<input type="number" name="age" min="18" max="120" required />
		<label>Gender</label>
		<div class="gender">
			<label><input type="radio" name="gender" value="Male" required />Male</label>
			<label><input type="radio" name="gender" value="Female" />Female</label>
			<label><input type="radio" name="gender" value="Other" />Other</label>
		</div>
		<label>Mobile</label>
		<input type="text" name="mobile" pattern="[0-9]{10}" title="10 digit mobile number" required />
		<label>Address</label>
		<textarea name="address" rows="3" required></textarea>
		<label>Opening Deposit (Rs.)</label>
		<input type="number" name="openingDeposit" min="0" step="0.01" value="0" required />
		<button type="submit">Create Account</button>
	</form>
</div>
<jsp:include page="includes/footer.jsp" />
