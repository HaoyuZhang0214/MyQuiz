<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>Question Manage</title>
</head>
<body>
<%--<label>Questions</label><br/>--%>
<button onclick="window.location.href='/question/manage/add'">Add Question</button>
<table>
    <c:out value="    Category  |       Content      | Status"></c:out>
    <c:forEach items="${questionPOJOs}" var="question" varStatus="loop">
        <form method="post">
            <tr>
                <td>${loop.index+1}</td>
                <td>${question.category}</td>
                <td>${question.content}</td>
                <c:choose>
                    <c:when test="${question.status eq true}">
                        <td>Enabled</td>
                        <td><button onclick="form.action='/question/manage/${question.question_id}';">Disable</button></td>
                    </c:when>
                    <c:otherwise>
                        <td>Disabled</td>
                        <td><button onclick="form.action='/question/manage/${question.question_id}';">Enable</button></td>
                    </c:otherwise>
                </c:choose>
                <td><button onclick="form.action='/question/update/${question.question_id}';">Edit</button></td>
                <br/>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
