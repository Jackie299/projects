package com.revature.ersmaven;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

/**
 * Servlet implementation class ReimburseServlet
 */
@WebServlet("/ReimburseServlet")

//This annotation defines the maximum
//file size which can be taken.
@MultipartConfig(maxFileSize = 16177215)
public class ReimburseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementService reimbursementService = new ReimbursementService();
	AuthService authService = new AuthService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReimburseServlet() {
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
		HttpSession session = request.getSession();
		int id = 0;
		User user = null;
		if (session != null) {
			String username = (String) session.getAttribute("username");

		}
		Reimbursement reimbursement = new Reimbursement();
		double amount = Double.parseDouble(request.getParameter("amount"));
		String description = request.getParameter("description");
//		InputStream receipt = req
		// Input stream of the upload file
		InputStream inputStream = null;

		String message = null;

		// Obtains the upload file
		// part in this multipart request
		Part filePart = request.getPart("receipt");

		if (filePart != null) {

			// Prints out some information
			// for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// Obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

		if (authService.retrieveCurrentUser().isPresent()) {
			user = authService.retrieveCurrentUser().get();
		}
		String username = user.getUsername();
		System.out.println("username in reimburse servlet =" + username);
		reimbursement.setAmount(amount);
		reimbursement.setDescription(description);
		reimbursement.setReceipt(inputStream);
		reimbursement.setAuthor(user.getId());
		// Sends the statement to the
		// database server
		Reimbursement reimburse = reimbursementService.create(reimbursement);

		if (reimburse != null) {
			message = "File uploaded and saved into database";
		}
		System.out.println(message);
//		int author = Integer.parseInt(request.getParameter("author"));
//		String status = request.getParameter("status");
//		Reimbursement newReimburse = reimbursementService.create(reimbursement);

		if (username != null) {
			response.sendRedirect("HomeServlet?username=" + username);
		} else {
			out.println("Error while creating Reimbursement Request!!!");
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
