<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>HomeAdmin</title>
</head>
<body>
    <label>Quiz Results</label>
    <form method="get" action="/homeAdmin">
        <select name="category" id="category" onchange="this.form.submit()">
            <option value="All Categories">All Categories</option>
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <option value="${category.name}">${category.name}</option>
            </c:forEach>
        </select>
    </form>
    <form method="get" action="/homeAdmin">
        <select name="user" id="user" onchange="this.form.submit()">
            <option value="0">All Users</option>
            <c:forEach items="${users}" var="user" varStatus="loop">
                <option value="${user.user_id}">${user.user_id}</option>
            </c:forEach>
        </select>
    </form>
    <br/>
    <tbody>
    <table>
        <c:out value="     Full Name  |  Category  |  Question Num  |  Score  | Date  |  Link"></c:out>
        <c:forEach items="${quizResults}" var="quizResult" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td>${quizResult.fullname}</td>
                <td>${quizResult.category}</td>
                <td>${quizResult.numOfQuestions}</td>
                <td>${quizResult.score}</td>
                <td>${quizResult.taken_date}</td>
                <td>
                    <a href="${quizResult.link}">link to result</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </tbody>
</body>
</html>
