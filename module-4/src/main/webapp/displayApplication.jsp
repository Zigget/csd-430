<!--
Samuel Sidzyik
CSD-430
Module 4.2
9/6/26
-->

<%@ page import="databean.ApplicationBean" %>

<%
    String expStr = request.getParameter("experience");
    int exp = 0;

    if (expStr != null && !expStr.isEmpty()) {
        exp = Integer.parseInt(expStr);
    }

    ApplicationBean bean = new ApplicationBean();
    bean.setFullName(request.getParameter("fullName"));
    bean.setEmail(request.getParameter("email"));
    bean.setExperience(exp);
    bean.setPosition(request.getParameter("position"));
    bean.setReason(request.getParameter("reason"));
%>

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
<p>This table displays the job application information submitted.</p>

<table border="1">
    <tr>
        <th>Field</th>
        <th>Description</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Full Name</td>
        <td>Applicant's legal name</td>
        <td><%= bean.getFullName() %></td>
    </tr>
    <tr>
        <td>Email</td>
        <td>Primary contact email</td>
        <td><%= bean.getEmail() %></td>
    </tr>
    <tr>
        <td>Experience</td>
        <td>Years of professional experience</td>
        <td><%= bean.getExperience() %></td>
    </tr>
    <tr>
        <td>Position</td>
        <td>Role the applicant is applying for</td>
        <td><%= bean.getPosition() %></td>
    </tr>
    <tr>
        <td>Reason</td>
        <td>Applicant's motivation for applying</td>
        <td><%= bean.getReason() %></td>
    </tr>
</table>


</body>
</html>
