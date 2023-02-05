<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QuizNavBar</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .navbar {
            overflow: hidden;
            background-color: #333;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .navbar a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .navbar a:hover {
            background: #f1f1f1;
            color: black;
        }

        .navbar a.active {
            background-color: #04AA6D;
            color: white;
        }

        .main {
            padding: 16px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>

<div class="navbar">
    <a href="/quiz/${name}/0" class="${questionNumber==0} ? active;">1</a>
    <a href="/quiz/${name}/1" class="${questionNumber==1} ? active;">2</a>
    <a href="/quiz/${name}/2" class="${questionNumber==2} ? active;">3</a>
    <a href="/quiz/${name}/3" class="${questionNumber==3} ? active;">4</a>
    <a href="/quiz/${name}/4" class="${questionNumber==4} ? active;">5</a>
</div>

</body>
</html>
