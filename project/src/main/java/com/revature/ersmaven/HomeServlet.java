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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		AuthService authServ = new AuthService();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		UserDAO userDao = new UserDAO();
		Optional<User> optionUser = userDao.getByUsername(username);
		User user = optionUser.get();
		System.out.println("user = "+user);
		ReimbursementService reimburseServ = new ReimbursementService();
		Role role =user.getRole();
		int id = user.getId();
		int flag=0;
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		if (role.equals(Role.EMPLOYEE)) {
			reimburseList = reimburseServ.getReimbursementByAuthor(id);
		} else if (role.equals(Role.FINANCE_MANAGER)) {
			flag=1;
			reimburseList = reimburseServ.getAllReimbursement();
		}
		String firstName = null;
		String lastName = null;
		String full_Name = null;
		if (optionUser.isPresent()) {
			firstName = user.getFirstName();
			lastName = user.getLastName();
			full_Name = firstName + lastName;
		}
		
		out.println("Welcome, <a href=''>" + full_Name + " </a>");
		out.println("<a href='Logout'>Logout</a>");
		out.println("</tbody></table></body></html>");
		
		if (role.equals(Role.EMPLOYEE)) {
			out.println("<html> <head> <title> Review Reimbursement Requests </title> </head> <body> <h1> Reimbursement Requests </h1>"
					+"<table border='1'> <thead> <tr> <th> ID </th> <th> Amount </th> <th> Author </th> <th> Resolver </th> <th> Status </th> <th> Request Date </th> <th> Approve Date </th></thead>");
		}
		
		
		if (flag == 1) {
			out.println("<head> <title> Review Reimbursement Requests </title> </head> <body> <h1> Reimbursement Requests </h1>"
					+ "<table border='1'> <thead> <tr> <th> ID </th> <th> Amount </th> <th> Author </th> <th> Resolver </th> <th> Status </th> <th> Request Date </th> <th> Approve Date </th><th> Adjudicate </th> </thead>");
		}
		for (Reimbursement reimbursement : reimburseList) {
			out.println("<tr> <td>" +reimbursement.getId()+"</td>");
			out.println("<td>" +reimbursement.getAmount()+"</td>");
			out.println("<td>" +reimbursement.getAuthor()+"</td>");
			out.println("<td>" +reimbursement.getResolver()+"</td>");
			out.println("<td>" +reimbursement.getStatus()+"</td>");
			out.println("<td>" +reimbursement.getCreationDate()+"</td>");
			out.println("<td>" +reimbursement.getResolutionDate()+"</td>");
			if (flag == 1) {
				out.println("<td> <a href='ApproveServletForm'> Approve </a> |<a href=''> Deny </a> </td>");
			}
			out.println("</tr>");
		}
		if (role.equals(Role.EMPLOYEE)) {
			out.println("<a href='createreimburse.html'> Submit Reimbursement Request");
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

