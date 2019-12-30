<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LKWT - login</title>
</head>
<body>
	<div id="loginBox">
		<form action="/login" method="post">
			<div>
				<input type='text' name='username' value='testeacher'>
			</div>
			<div>
				<input type='password' name='password' value='testeacher'>
			</div>
			<div>
				<div>
					<input type='checkbox' name='remember-me'> Remember Me
				</div>
	
				<div>
					<input type='submit'>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</div>
		</form>
	</div>
</body>
</html>