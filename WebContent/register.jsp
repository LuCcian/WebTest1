<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MOBS register</title>
</head>
<body>
	<h1 align="center">
	欢迎注册MOBS(多人在线账务系统)
	</h1>
	<form name="registerForm1" action="Register" method="post">
	<table>
	<tr>
		<td align="right">
			用户名：
		</td>
		<td>
			<input type="text" size="18" name="userid" value=<%=request.getAttribute("userid")==null?"":request.getAttribute("userid") %> />
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
	<tr>
		<td align="right">
			确认密码：
		</td>
		<td>
			<input type="password" size="19" name="confirmPassword"/>
		</td>
	</tr>
	<tr>
    <td colspan="2">
    <%
    	//out.print("["+request.getAttribute("addInf")+"]");
    	if(request.getAttribute("addInf")!=null)
    		out.print(request.getAttribute("addInf"));
    %>
    </td>
    </tr>
	</table>
	
	<table>
	<tr>
		<td width="80"/>
		<td width="80">
			<input type="submit" name="register" value="注册"/>
		</td>
		<td width="80">
			<input type="reset" name="reset" value="重置"/>
		</td>
	</tr>
	</table>
	<p><a href="index.jsp">返回</a></p>
	</form>
</body>
</html>