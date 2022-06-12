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
import com.revature.models.User;
import com.revature.repositories.UserDAO;
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

		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");

//		System.out.println("username in HomeServlet :" + username);
		UserDAO userDao = new UserDAO();
		Optional<User> user = userDao.getByUsername(username);

		ReimbursementService reimburseService = new ReimbursementService();
		int id = user.get().getId();
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		reimburseList = reimburseService.getReimbursementByAuthor(id);
		out.println("<html> <head> <title> Reimbursement Details </title> </head> <body>"+
				"<a href='createreimburse.html'>Create a Reimbursement Request </a> <table border='1'> <thead> <tr> <th> ID </th> <th> Amount </th> <th> Author </th> <th> Resolver </th> <th> Status </th> <th>Creation Date </th> <th> Resolution Date </th> <th>Receipt Image </th> </tr> </thead> <tbody>");
		for (Reimbursement reimbursement : reimburseList) {
			out.print("<tr> <td>" + reimbursement.getId() + "</td>");
			out.print("<td>" + reimbursement.getAmount() + "</td>");
			out.print("<td>" + reimbursement.getAuthor() + "</td>");
			out.print("<td>" + reimbursement.getResolver() + "</td>");
			out.print("<td>" + reimbursement.getStatus() + "</td>");
			out.print("<td>" + reimbursement.getCreationDate() + "</td>");
			out.print("<td>" + reimbursement.getResolutionDate() + "</td>");
			out.print("<td>" + reimbursement.getReceipt() + "</td> </tr>");
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
