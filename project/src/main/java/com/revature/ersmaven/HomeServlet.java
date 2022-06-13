package com.revature.ersmaven;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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
		response.setContentType("text/html");
		AuthService authService = new AuthService();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");

//		System.out.println("username in HomeServlet :" + username);
		UserDAO userDao = new UserDAO();
		Optional<User> optionalUser = userDao.getByUsername(username);
		User user = optionalUser.get();
		System.out.println("User =" + user);
		ReimbursementService reimburseService = new ReimbursementService();
		Role role = user.getRole();
		// System.out.println("Finance Manager ?? :" +
		// role.equals(Role.FINANCE_MANAGER));

		int id = user.getId();
		int flag = 0;
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		if (role.equals(Role.EMPLOYEE)) {
			reimburseList = reimburseService.getReimbursementByAuthor(id);
		} else if (role.equals(Role.FINANCE_MANAGER)) {
			flag = 1;
			reimburseList = reimburseService.getAllReimbursement();
		}

		out.println("<html> <head> <title> Reimbursement Details </title> </head> <body>"
				+ "<a href='createreimburse.html'>Create a Reimbursement Request </a> ");
		String firstName = null;
		String lastName = null;
		String fullName = null;
		if (optionalUser.isPresent()) {
			firstName = user.getFirstName();
			lastName = user.getLastName();
			fullName = firstName + lastName;
		}
		out.println("Welcome, <a href=''>" + fullName + " </a>");
		out.println("<a href='Logout'>Logout</a>");
		out.println(
				"<table border='1'> <thead> <tr> <th> ID </th> <th> Amount </th> <th> Author </th> <th> Resolver </th> <th> Status </th> <th>Creation Date </th> <th> Resolution Date </th> <th>Receipt Image </th> ");
		if (flag == 1) {
			out.println("<th>Actions </th>");
		}
		out.println("</tr></thead><tbody>");
		for (Reimbursement reimbursement : reimburseList) {
			out.print("<tr> <td>" + reimbursement.getId() + "</td>");
			out.print("<td>" + reimbursement.getAmount() + "</td>");
			out.print("<td>" + reimbursement.getAuthor() + "</td>");
			out.print("<td>" + reimbursement.getResolver() + "</td>");
			out.print("<td>" + reimbursement.getStatus() + "</td>");
			out.print("<td>" + reimbursement.getCreationDate() + "</td>");
			out.print("<td>" + reimbursement.getResolutionDate() + "</td>");
			out.print("<td>" + reimbursement.getReceipt() + "</td>");
			if (flag == 1) {
				out.println("<td> <a href=''> Approve </a> ||<a href=''> Deny </a> </td>");
			}
			out.println("</tr>");
		}
		out.print("</tbody></table> </body> </html>");
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
