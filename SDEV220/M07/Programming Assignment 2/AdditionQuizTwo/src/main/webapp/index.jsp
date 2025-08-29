<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.Math" %>

<!-- 
Author: Scott Field
Date: 10/04/2023
Version: 1.0
Purpose:
provide the page for the user to input their answers to the addition problems
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Addition Quiz</title>
</head>
<body>
<!-- Output The 10 Input Addition Fields -->
<!-- The name is set to the index of the answer so that the program can differentiate between them -->
	<!-- Use Lists To Store The Results -->
<form action = "output.jsp" id = "inputForm">
	<!-- Define Variables -->
	<%int[] firstNumList  = new int[10];%>
	<%int[] secondNumList = new int[10];%>
	<%int[] answerNumList = new int[10];%>
	
	<!-- Output Quiz Loop -->
	<%	for( int i = 0; i < 10; i++){	%>
			<%int firstNum  = (int) (Math.random() * 100); %>
			<%int secondNum = (int) (Math.random() * 100); %>
			
			<%firstNumList[i] = firstNum; %>
			<%secondNumList[i] = secondNum; %>
			<%answerNumList[i] = firstNum + secondNum; %>
				
			<p style="margin: 3px 0;">
	        	<span style="display: inline-block; width: 50px; text-align: left; padding-right: 5px;"><%= firstNum %> + <%= secondNum %></span>
	        	<span style="display: inline-block; width: 25px; text-align: left;">=</span>
	        	<span style="display: inline-block; width: 50px;"><input type="number" name="answer<%= i %>" style="width: 100%; padding: 5px;" required/></span>
	        </p>
	<%	}	%>
	
	<%
		session.setAttribute("firstNumList" , firstNumList );
		session.setAttribute("secondNumList", secondNumList);
		session.setAttribute("answerNumList", answerNumList);
	%>
	
	
    
	<p><input type = "submit">Click the browser's Refresh button to get a new quiz</p>
</form>


</body>

</html>