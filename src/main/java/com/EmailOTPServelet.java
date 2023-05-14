package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmailOTPServelet")
public class EmailOTPServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String email;
    private String password;
	
	public EmailOTPServelet() {
        // default constructor
    }
	
	public EmailOTPServelet(String email, String password) {
        this.email = email;
        this.password = password;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//call method to display on browser OTP sent successfully or not
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String otp = EmailOTP.generateOTP();
		//PrintWriter out=null;
		try {
			//out=response.getWriter();
			EmailOTP.sendOTPEmail(email, otp, pwd);
			response.getWriter().write("OTP sent successfully to " + email);
			//out.println("<center>");
			response.getWriter().write("<center>");
			
			
			
		}
		catch(Exception e) {
			//out.println("Error :"+e.getMessage());
			response.getWriter().write("Error sending OTP to " + email);
			 response.setStatus(400); // set HTTP status code to 400
		}
		finally {
			//out.println("<br><br>");
			response.getWriter().write("<br><br>");
			//out.println("Go to Home page <a href=index.html>Click HERE</a>");
			response.getWriter().write("Go to Home page <a href=index.html>Click HERE</a>");
			//out.println("</center>");
			response.getWriter().write("<center>");
		}
	}

}
