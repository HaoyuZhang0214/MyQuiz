<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>Update Question</title>
</head>
<body>
<form method="post" action="/question/manage/update">
    <div>
        <label>Update Question</label><br/>

        <label>Question description:</label>
        <div>
            <textarea name="content" cols="50" rows="10" value="${question.content}"></textarea>
        </div>
        <div>
            <label>Option1:</label>
            <input type="text" name="option1" value="${options.get(0).content}">
        </div>
        <div>
            <label>Option2:</label>
            <input type="text" name="option2" value=""${options.get(1).content}">
        </div>
        <div>
            <label>Option3:</label>
            <input type="text" name="option3" value=""${options.get(2).content}">
        </div>
        <div>
            <label>Option4:</label>
            <input type="text" name="option4" value=""${options.get(3).content}">
        </div>
        <label>The right answer:</label>
        <tbody>
        <tr>
<%--            <c:choose>--%>
<%--                <c:when test="${options.get(0).is_solution eq true}">--%>
<%--                    <input type="radio" name="solution" value="1" checked/>Option1<br/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <input type="radio" name="solution" value="1"/>Option1<br/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${options.get(1).is_solution eq true}">--%>
<%--                    <input type="radio" name="solution" value="2" checked/>Option2<br/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <input type="radio" name="solution" value="2"/>Option2<br/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${options.get(2).is_solution eq true}">--%>
<%--                    <input type="radio" name="solution" value="3" checked/>Option3<br/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <input type="radio" name="solution" value="3"/>Option3<br/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${options.get(3).is_solution eq true}">--%>
<%--                    <input type="radio" name="solution" value="4" checked/>Option4<br/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <input type="radio" name="solution" value="4"/>Option4<br/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
            <input type="radio" name="solution" value="1" checked/>Option1<br/>
            <input type="radio" name="solution" value="2" />Option2<br/>
            <input type="radio" name="solution" value="3" />Option3<br/>
            <input type="radio" name="solution" value="4" />Option4<br/>
        </tr>
        </tbody>
        <button type="submit">Update</button>
    </div>
</form></body>
</html>
