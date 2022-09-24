<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-16"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readonly>
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter Password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter Email" id="email">
		</div>



	</form>
	<button id="btn-update" class="btn btn-primary">회원수정 완료</button>
</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>


