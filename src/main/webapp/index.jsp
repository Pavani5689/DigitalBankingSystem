<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="Home" /></jsp:include>
<section class="hero">
	<span class="pill">&#9679; Secure &middot; Instant &middot; 24/7</span>
	<h1>Banking made <em>simple</em><br/>and secure</h1>
	<p>Open an account in minutes, deposit, withdraw and transfer money, and track every transaction online.</p>
	<div class="actions">
		<a class="btn light" href="register.jsp">Open an Account</a>
		<a class="btn ghost" href="login.jsp">Login</a>
	</div>
	<div class="credit-card">
		<div class="brand">DigitalBank</div>
		<div class="chip"></div>
		<div class="num">4821 &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; 0926</div>
		<div class="row"><span>Premium Member</span><span>12 / 31</span></div>
	</div>
</section>
<div class="stats">
	<div class="stat"><b>24/7</b><span>Access</span></div>
	<div class="stat"><b>100%</b><span>Atomic transfers</span></div>
	<div class="stat"><b>0</b><span>Hidden fees</span></div>
	<div class="stat"><b>&lt;1 min</b><span>Account setup</span></div>
</div>
<div class="grid">
	<div class="feature"><div class="ico">&#128179;</div><h3>Instant Account</h3><p>Register online and get your own account number immediately.</p></div>
	<div class="feature"><div class="ico">&#128176;</div><h3>Deposit &amp; Withdraw</h3><p>Add or take out money at any time with a live balance.</p></div>
	<div class="feature"><div class="ico">&#9889;</div><h3>Fund Transfer</h3><p>Send money to any other account safely &ndash; either the full transfer happens or nothing does.</p></div>
	<div class="feature"><div class="ico">&#128202;</div><h3>Statements</h3><p>See a complete history of every transaction on your account.</p></div>
</div>
<jsp:include page="includes/footer.jsp" />
