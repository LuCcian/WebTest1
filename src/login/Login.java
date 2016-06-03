package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{
	private String userid;
	private String password;
	private String confirmPassword;

	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		this.userid = request.getParameter("userid");
		this.password = request.getParameter("password");
		this.confirmPassword = request.getParameter("confirmPassword");
		
		PrintWriter responseOut = response.getWriter();
		responseOut.println("<h1>"+"This is the Login doPost request's response"+"</h1>");
		//数据库连接驱动
		String dbDriverName = "com.mysql.jdbc.Driver";
		//数据库所在主机IP
		String dbHost = "127.0.0.1";
		//数据库连接端口号
		String dbPort = "3306";
		//欲连接数据库名
		String dbName = "mobs";
		//用户名
		String dbUserName = "lc";
		//密码
		String dbPassword = "1234";
		//数据库连接url
		String dbConnectURL = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		try
		{
			Class.forName(dbDriverName);
			Connection connection = DriverManager.getConnection(dbConnectURL, dbUserName, dbPassword);
			Statement statement = connection.createStatement();
			
			String qrySQL = "select user_name, pass_word from mobs.t_user where user_name='"+userid+"'";
			ResultSet qryRs = statement.executeQuery(qrySQL);
			if(qryRs.next())	//record found
			{
				if(password.equals(qryRs.getString("pass_word")))	//login success, enter home page
					response.sendRedirect(request.getContextPath() + "/HomePage.html");
					//responseOut.println("<h1>Welcome :"+userid+"!</h1>");
				else	//wrong password
				{
					request.setAttribute("userid", userid);
					request.setAttribute("addInf", "wrong password!");
					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					//responseOut.println("<h1>wrong password!</h1>");
				}
			}
			else	//no records found
			{
				request.setAttribute("userid", userid);
				request.setAttribute("addInf", "no such user!");
				RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				//responseOut.println("<h1>userid:"+userid+"not found!</h1>");
			}
			return;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			responseOut.println("<h1>SQLException:"+e.getErrorCode()+" "+e.getMessage()+"</h1>");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			responseOut.println("<h1>ClassNotFoundException:"+e.getLocalizedMessage()+" "+e.getMessage()+"</h1>");
		}
	}
	
	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		doPost(request,response);
		/*
		this.userid = request.getParameter("userid");
		this.password = request.getParameter("password");
		this.confirmPassword = request.getParameter("confirmPassword");
		
		PrintWriter responseOut = response.getWriter();
		responseOut.println("<h1>"+"This is the Login doGet request's response"+"</h1>");
		//数据库连接驱动
		String dbDriverName = "com.mysql.jdbc.Driver";
		//数据库所在主机IP
		String dbHost = "127.0.0.1";
		//数据库连接端口号
		String dbPort = "3306";
		//欲连接数据库名
		String dbName = "mobs";
		//用户名
		String dbUserName = "lc";
		//密码
		String dbPassword = "1234";
		//数据库连接url
		String dbConnectURL = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		try
		{
			Class.forName(dbDriverName);
			Connection connection = DriverManager.getConnection(dbConnectURL, dbUserName, dbPassword);
			Statement statement = connection.createStatement();
			
			String qrySQL = "select user_name, pass_word from mobs.t_user where user_name='"+userid+"'";
			ResultSet qryRs = statement.executeQuery(qrySQL);
			if(qryRs.next())	//record found
			{
				if(password.equals(qryRs.getString("PASSWORD")))	//login success, enter home page
				response.sendRedirect(request.getContextPath() + "/HomePage.html");
				//responseOut.println("<h1>Welcome :"+userid+"!</h1>");
				else	//wrong password
					responseOut.println("<h1>wrong password!</h1>");
			}
			else	//no records found
			{
				responseOut.println("<h1>userid:"+userid+"not found!</h1>");
			}
			return;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			responseOut.println("<h1>SQLException:"+e.getErrorCode()+" "+e.getMessage()+"</h1>");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			responseOut.println("<h1>ClassNotFoundException:"+e.getLocalizedMessage()+" "+e.getMessage()+"</h1>");
		}
		*/
	}
}