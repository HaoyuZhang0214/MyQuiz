<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>FeedbackAdmin</title>
</head>
<body>
<label>Feedbacks</label><br/>
<tbody>
<table>
    <c:forEach items="${feedbacks}" var="feedback" varStatus="loop">
        <div>
            <label>${feedback.rating} Stars</label><br/>
            <textarea name="content" cols="70" rows="10">
                ${feedback.content}
            </textarea><br>
            <label>${feedback.date}</label><br/>
        </div>
    </c:forEach>
</table>
</tbody>
</body>
</html>
