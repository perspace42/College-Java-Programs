<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
Author: Scott Field
Version: 1.0
Date: 10/04/2023
Purpose:
To display the results of the user entered computations outputting Correct
if the user entered the correct answer and outputting Wrong if the user entered incorrect computatons
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz Results</title>
</head>
<body>

<!-- Get The Lists From Application Storage -->
<%int[] firstNumList  = (int[]) session.getAttribute("firstNumList"); %>
<%int[] secondNumList = (int[]) session.getAttribute("secondNumList");%>
<%int[] answerNumList = (int[]) session.getAttribute("answerNumList");%>

<% 
	//set a number to count the number of correct answers 
	int count = 0;
%>

<%	for(int i = 0; i < 10; i++) { %><% 
		//Get the numbers from the form
		int firstNum  = firstNumList[i];
		int secondNum = secondNumList[i];
		int actualAnswer = answerNumList[i];
		//get the input answer using index
		int userAnswer = Integer.parseInt(request.getParameter("answer"+i));
		String result;
		//If the user answer is equal to the actual answer
		if (userAnswer == actualAnswer){
			result = "Correct";
			//add 1 to the correct count
			count += 1;
		}else{
			result = "Wrong";
		}
	%>
	<p style="margin: 3px 0;">
		<span style="display: inline-block; width: 50px; text-align: left; padding-right: 5px;"><%= firstNum %> + <%= secondNum %></span>
	    <span style="display: inline-block; width: 25px; text-align: left;">=</span>
	    <span style="display: inline-block; width: 25px; text-align: left;"><%= userAnswer %></span>
	    <span style="display: inline-block; width: 50px;"><%= result %></span>
	</p>
	
<%	}	%>

<p>The total correct count is: <%=count %></p>



</body>
</html>