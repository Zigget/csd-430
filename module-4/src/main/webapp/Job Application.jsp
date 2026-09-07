<!--
Samuel Sidzyik
CSD-430
Module 4.2
9/6/26
-->

<!DOCTYPE html>
<html>
<head>
    <title>Job Application Form</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Job Application</h2>

    <form action="displayApplication.jsp" method="post">

        <!-- Text input -->
        Full Name: <input type="text" name="fullName" required><br><br>

        <!-- Email input -->
        Email: <input type="email" name="email" required><br><br>

        <!-- Number input -->
        Years of Experience: <input type="number" name="experience" min="0" max="50"><br><br>

        <!-- Dropdown -->
        Position Applying For:
        <select name="position">
            <option value="Developer">Developer</option>
            <option value="Designer">Designer</option>
            <option value="Manager">Manager</option>
        </select><br><br>

        <!-- Text area -->
        Why do you want this job?<br>
        <textarea name="reason" rows="4" cols="40"></textarea><br><br>

        <input type="submit" value="Submit Application">
    </form>

</body>
</html>
