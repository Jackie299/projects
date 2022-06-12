package com.revature.ersmaven;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsernameFinder
 */
@WebServlet("/UsernameFinder")
public class UsernameFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsernameFinder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
		String username = request.getParameter("username");
		if (username != null) {
			try {
				// Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Connection con =
				// DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
				// "oracle");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ers", "root", "Vampire22");
				PreparedStatement ps = con.prepareStatement("select * from user_table where username=?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					out.print("Unavailable! <img src='unchecked.gif'/>");
				} else {
					out.print("Available! <img src='checked.gif'/>");
				}
			} catch (Exception e) {
				out.print(e);
			}
		} else {
			out.print("Invalid Username!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
