/*
Author: Scott Field
Date: 10/04/2023
Version: 1.0 
Purpose:
Provide the Servlet to handle the data from the HTML pages
*/

package com.journaldev.first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/calculateLoan")
public class calculateLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculateLoan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the home page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lib/loanForm.html");
		//forward to that home page
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initialize the variables
		int years = 0;
		double interestRate = 0;
		double loanAmount = 0;
		
		//Because The Page Does Not Accept Invalid Data, No Validation Is Normally Needed When Getting The Input Here
		try {
			years = Integer.parseInt(request.getParameter("numberOfYears").toString());
			interestRate = Double.parseDouble(request.getParameter("interestRate").toString());
			loanAmount = Double.parseDouble(request.getParameter("loanAmount").toString());
		//However in case I missed something any exception will end the function
		}catch(Exception e){
			//return if an error is found
			System.out.println("A problem has occurred");
			return;
		}
		
		//From the user input create the loan object
		Loan loanObject = new Loan(interestRate,years,loanAmount);
		
		//Get the path to the file
		String fullPath = getServletContext().getRealPath("/WEB-INF/lib/output.html");
		//set the printWriter to write to the file
		PrintWriter out = new PrintWriter(fullPath);
		
		out.println(HTML_START);
		out.println("<p>Loan Amount:"+ loanAmount +"</p>");
		out.println("<p>Annual Interest Rate:"+ interestRate +"</p>");
		out.println("<p>Number of Years:"+ years +"</p>");
		out.println("<p>Monthly Payment:"+ loanObject.getMonthlyPayment() +"</p>");
		out.println("<p>Total Payment:"+ loanObject.getTotalPayment() +"</p>");
		out.println(HTML_END);
		
		//close the PrintWriter
		out.close();
		
		//redirect the user to the output
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lib/output.html");
	    dispatcher.forward(request, response);
	}

}
