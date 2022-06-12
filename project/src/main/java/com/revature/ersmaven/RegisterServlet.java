package com.revature.ersmaven;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		UserService userService = new UserService();
		User userToBeRegistered = new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		userToBeRegistered.setUsername(username);
		userToBeRegistered.setPassword(password);
		if (role.equalsIgnoreCase("Employee"))
			userToBeRegistered.setRole(Role.EMPLOYEE);
		else if (role.equalsIgnoreCase("financeManager"))
			userToBeRegistered.setRole(Role.FINANCE_MANAGER);
		User registeredUser = userService.create(userToBeRegistered);
		if (registeredUser != null)
			response.sendRedirect("login.html");
		else
			throw new RegistrationUnsuccessfulException("Registration is UnSuccessful!!!");
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
