<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>Quiz Result Detail</title>
</head>
<body>
<div>
    <div>
        <label>Quiz Name:</label>
        <c:out value="${quiz.name}"/>
    </div>
    <div>
        <label>Full Name:</label>
        <c:out value="${user.firstname}"/>
        <c:out value="${user.lastname}"/>
    </div>
<%--    <div>--%>
<%--        <label>Start Time:</label>--%>
<%--        <c:out value="${start_time}"/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label>End Time:</label>--%>
<%--        <c:out value="${end_time}"/>--%>
<%--    </div>--%>
    <div>
        <div>
            <label>Date:</label>
            <c:out value="${date}"/>
        </div>
    </div>
    <div>
        <tbody>
        <table>
            <c:forEach items="${questionEntities}" var="questionEntity" varStatus="loop">
                <label>${loop.index+1}. ${questionEntity.content}</label> <br/>
                <tbody>
                <tr>
                    <c:forEach items="${questionEntity.options}" var="option" varStatus="loop">
                        <c:choose>
                            <c:when test="${choice.contains(option.option_id)}">
                                <input type="radio" name="option" value="${option.option_id}" checked/>${option.content}<br/>
                                <%--              <c:set var="isSolution" value="${option.is_solution}"></c:set>--%>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="option" value="${option.option_id}" />${option.content}<br/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
                    <%--        <c:choose>--%>
                    <%--          <c:when test="${isSolution eq true}">--%>
                    <%--            <label>Correct</label><br/>--%>
                    <%--          </c:when>--%>
                    <%--          <c:otherwise>--%>
                    <%--            <label>Wrong</label><br/>--%>
                    <%--          </c:otherwise>--%>
                    <%--        </c:choose>--%>
                </tbody>
            </c:forEach>
        </table>
        </tbody>
        <div>
            <div>
                <label>Score:</label>
                <c:out value="${score}"/>
            </div>
            <c:choose>
                <c:when test="${score >= 60}">
                    <label>Congratulations! You pass the quiz.</label>
                </c:when>
                <c:otherwise>
                    <label>Sorry, you fail the quiz.</label>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
<%--    <a href="/home">Take Another Quiz</a>--%>
</div>

</body>
</html>
