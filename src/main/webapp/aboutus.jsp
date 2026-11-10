<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/header.jsp"><jsp:param name="title" value="About" /></jsp:include>
<section class="hero" style="padding:60px 30px">
	<span class="pill">Our Story</span>
	<h1>About <em>DigitalBank</em></h1>
	<p>A Digital Banking &amp; Transaction Management System built to make everyday banking simple.</p>
</section>
<div class="grid">
	<div class="feature"><div class="ico">&#127919;</div><h3>Our Mission</h3><p>Open an account online, manage your money, and keep a clear record of every transaction.</p></div>
	<div class="feature"><div class="ico">&#128274;</div><h3>Secure by Design</h3><p>Transfers are all-or-nothing, so your money is never left in limbo.</p></div>
	<div class="feature"><div class="ico">&#9889;</div><h3>Built for Speed</h3><p>Instant balances, instant history, zero waiting.</p></div>
</div>
<jsp:include page="includes/footer.jsp" />
