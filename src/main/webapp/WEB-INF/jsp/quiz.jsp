<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>Quiz</title>
</head>

<body>
<thead>
<tr>
    <th>${quiz.name}</th><section class="timer"></section>
</tr>
</thead>
<br/>
<tbody>
<table>
    <c:forEach items="${questionEntities}" var="questionEntity" varStatus="loop">
        <c:choose>
            <c:when test="${loop.index==questionNumber}">
                <form method="post">
                    <label>${loop.index+1}. ${questionEntity.content}</label> <br/>
                    <tbody>
                    <tr>
                        <c:forEach items="${questionEntity.options}" var="option" varStatus="loop">
                            <c:choose>
                                <c:when test="${choice.contains(option.option_id)}">
                                    <input type="radio" name="option" value="${option.option_id}" checked/>${option.content}<br/>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="option" value="${option.option_id}" />${option.content}<br/>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                    </tbody>
                    <c:choose>
                        <c:when test="${questionNumber==0}">
                            <button type="submit" onclick="form.action='/quiz/${name}/${questionNumber+1}';">Next</button>
                        </c:when>
                        <c:when test="${questionNumber==4}">
                            <button type="submit" onclick="form.action='/quiz/${name}/${questionNumber-1}';">Prev</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" onclick="form.action='/quiz/${name}/${questionNumber-1}';">Prev</button>
                            <button type="submit" onclick="form.action='/quiz/${name}/${questionNumber+1}';">Next</button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </c:when>
        </c:choose>
    </c:forEach>
</table>
</tbody>
<form method="post" action="/quiz/${name}">
    <button type="submit">Submit</button>
</form>
<div>

</div>
<jsp:include page="elements/quizNavBar.jsp"/>
</body>
</html>
