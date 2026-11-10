<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Edit" /></jsp:include>
<div class="card narrow">
	<h2>Edit Customer</h2>
	<form action="edituser" method="post">
		<input type="hidden" name="id" value="${customer.id}" />
		<label>Full Name</label>
		<input type="text" name="name" value="<c:out value='${customer.name}' />" required />
		<label>Email</label>
		<input type="email" name="email" value="<c:out value='${customer.email}' />" required />
		<label>Age</label>
		<input type="number" name="age" min="18" max="120" value="${customer.age}" required />
		<label>Gender</label>
		<select name="gender">
			<option value="Male" ${customer.gender == 'Male' ? 'selected' : ''}>Male</option>
			<option value="Female" ${customer.gender == 'Female' ? 'selected' : ''}>Female</option>
			<option value="Other" ${customer.gender == 'Other' ? 'selected' : ''}>Other</option>
		</select>
		<label>Mobile</label>
		<input type="text" name="mobile" value="<c:out value='${customer.mobile}' />" required />
		<label>Address</label>
		<textarea name="address" rows="3" required><c:out value="${customer.address}" /></textarea>
		<button type="submit">Save Changes</button>
	</form>
</div>
<jsp:include page="includes/footer.jsp" />
