<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>Quiz Start</title>
</head>
<body>
    <label>Choose the category you want to take.</label>
    <tbody>
    <c:forEach items="${categories}" var="category" varStatus="loop">
        <tr>
            <a href="/quiz/${category.name}/0">${category.name}</a>
        </tr>
    </c:forEach>
    </tbody>
</body>
</html>
