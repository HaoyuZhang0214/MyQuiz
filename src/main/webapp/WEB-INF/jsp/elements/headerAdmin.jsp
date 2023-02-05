<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HeaderAdmin</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<div class="topnav">
    <a href="/homeAdmin" class="page-link">Quiz Result</a>
    <a href="/user/manage" class="page-link">User Management</a>
    <a href="/question/manage" class="page-link">Quiz Question Management</a>
    <a href="/feedbackAdmin" class="page-link">Feedback</a>
    <a href="/logout" class="page-link">Log out</a>
</div>
</body>
</html>
