<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
	<form name="f" th:action="@{/login}" method="post">
		<sec:csrfInput />
		<fieldset>
			<legend>Please Login</legend>
			<div th:if="${param.error}" class="alert alert-error">Invalid
				username and password.</div>
			<div th:if="${param.logout}" class="alert alert-success">You
				have been logged out.</div>
			<label for="username">Username</label> <input type="text"
				id="username" name="username" /> <label for="password">Password</label>
			<input type="password" id="password" name="password" /> <label
				for="remember-me">Remember Me?</label> <input type="checkbox"
				id="remember-me" name="remember-me" />
			<div class="form-actions">
				<button type="submit" class="btn">Log in</button>
			</div>
		</fieldset>
	</form>
</body>
</html>