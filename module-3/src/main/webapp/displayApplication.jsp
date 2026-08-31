<!--
Samuel Sidzyik
CSD-430
Module 3.2
8/30/26
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Application Received</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h2>Application Details</h2>

<!-- 
simple output of data from application form
I did the scriplets inline
-->
<p><strong>Name:</strong> <%= request.getParameter("fullName") %></p>
<p><strong>Email:</strong> <%= request.getParameter("email") %></p>
<p><strong>Years of Experience:</strong> <%= request.getParameter("experience") %></p>
<p><strong>Position:</strong> <%= request.getParameter("position") %></p>
<p><strong>Reason:</strong> <%= request.getParameter("reason") %></p>

</body>
</html>
