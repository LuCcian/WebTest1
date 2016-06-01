<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MOBS index</title>
</head>
<body>
	<script language="javascript">
	function login_click()
	{
		indexForm1.action = "Login";
		indexForm1.method = "post";
		indexForm1.submit();
	}
	
	function register_click()
	{
		indexForm1.action = "register.jsp";
		indexForm1.submit();
	}
	</script>
	<h1 align="center">多人在线账务系统(MOBS)</h1>
	<form name="indexForm1" action="register.jsp" method="post">
	<table>
	<tr>
		<td align="right">
			用户名：
		</td>
		<td>
			<input type="text" size="18" name="userid"/>
		</td>
	</tr>
	<tr>
		<td align="right">
			密码：
		</td>
		<td>
			<input type="password" size="19" name="password"/>
		</td>
	</tr>
	</table>
	
	<table>
	<tr>
		<td width="64"/>
		<td width="80">
			<input type="submit" name="login" value="登录" onClick="return login_click()"/>
		</td>
		<td width="80">
			<input type="submit" name="register" value="注册" onClick="return register_click()"/>
		</td>
	</tr>
	</table>
	</form>
	<p><a href="register.jsp">注册新用户</a></p>
</body>
</html>