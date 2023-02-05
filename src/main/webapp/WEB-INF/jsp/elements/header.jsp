<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>--%>
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
    <a href="/home" class="page-link">Home</a>
    <a href="/quizStart" class="page-link">Start Quiz</a>
    <a href="/contact" class="page-link">Contact Us</a>
    <a href="/feedback" class="page-link">Feedback</a>
    <a href="/account/${user.user_id}" class="page-link">Account</a>
    <a href="/logout" class="page-link">Log out</a>
</div>

<%--<div class="nav-link">--%>
<%--    <ul class="nav-link">--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/home" class="page-link">Home</a>--%>
<%--        </li>--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/quizStart" class="page-link">Start Quiz</a>--%>
<%--        </li>--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/contact" class="page-link">Contact Us</a>--%>
<%--        </li>--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/feedback" class="page-link">Feedback</a>--%>
<%--        </li>--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/account/${user.user_id}" class="page-link">Account</a>--%>
<%--        </li>--%>
<%--        <li class="nav-link-item">--%>
<%--            <a href="/logout" class="page-link">Log out</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--</div>--%>
</body>
</html>
