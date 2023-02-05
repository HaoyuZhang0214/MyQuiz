<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>Home</title>
</head>

<body>
<table>
    <thead>
    <tr>
        <th>All Categories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<table>
    <thead>
    <tr>
        <th>Quiz History</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${quizHistories}" var="quizHistory" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${quizHistory.name}</td>
            <td>${quizHistory.taken_date}</td>
            <td>
                <a href="${quizHistory.link}">link to result</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>

</html>
