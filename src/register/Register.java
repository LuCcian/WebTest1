package register;

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

//import com.mysql.jdbc.Driver;

public class Register extends HttpServlet
{
	private String userid;
	private String password;
	private String confirmPassword;
	
	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		this.userid = request.getParameter("userid");
		this.password = request.getParameter("password");
		this.confirmPassword = request.getParameter("confirmPassword");
		PrintWriter responseOut = response.getWriter();
		
		responseOut.println("<h1>"+"This is the Register doPost request's response"+"</h1>");
		//数据库驱动程序
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
			
			String qrySQL = "select user_name from mobs.t_user where user_name='"+userid+"'";
			ResultSet qryRs = statement.executeQuery(qrySQL);
			if(qryRs.next())	//record found
			{
				request.setAttribute("userid", userid);
				request.setAttribute("addInf", "user name has been used,pls try another one");
				RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
				rd.forward(request, response);
				//responseOut.println("<h1>the userid:"+userid+"has been used</h1>");
			}
			else	//no records found
			{
				if(0==password.compareTo(confirmPassword))
				{
					String insSQL = "insert into mobs.t_user(user_name,pass_word) values('"+userid+"','"+password+"')";
					statement.execute(insSQL);
					request.setAttribute("userid", userid);
					request.setAttribute("addInf", "regist succeeded,pls log in");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					//responseOut.println("<h1>userid:"+userid+" register succeeded</h1>");
				}
				else
				{
					request.setAttribute("userid", userid);
					request.setAttribute("addInf", "confirm password is different from password,pls check");
					RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
					rd.forward(request, response);
				}
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
	
	
	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		doPost(request,response);
		/*
		this.userid = request.getParameter("userid");
		this.password = request.getParameter("password");
		this.confirmPassword = request.getParameter("confirmPassword");
		PrintWriter responseOut = response.getWriter();
		
		responseOut.println("<h1>"+"This is the Register doGet request's response"+"</h1>");
		//数据库驱动程序
		String dbDriverName = "org.gjt.mm.mysql.Driver";
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
		String dbConnectURL = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?user="+dbUserName+"&password="+dbPassword;
		try
		{
			Class.forName(dbDriverName);
			Connection connection = DriverManager.getConnection(dbConnectURL, dbUserName, dbPassword);
			Statement statement = connection.createStatement();
			
			String qrySQL = "select user_name from mobs.t_user where user_name="+userid;
			ResultSet qryRs = statement.executeQuery(qrySQL);
			if(qryRs.next())	//record found
			{
				responseOut.println("<h1>the userid:"+userid+"has been used</h1>");
			}
			else	//no records found
			{
				String insSQL = "insert into test.mobs_users values('"+userid+"','"+password+"')";
				statement.execute(insSQL);
				responseOut.println("<h1>userid:"+userid+"register succeeded</h1>");
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