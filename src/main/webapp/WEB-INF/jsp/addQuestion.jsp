<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>Add Question</title>
</head>
<body>
<form method="post" action="/question/manage/add">
    <div>
        <label>New Question</label>
        <div>
            <label>Category</label>
<%--            <input type="text" name="subject">--%>
            <select name="category" id="category">
                <c:forEach items="${categories}" var="category" varStatus="loop">
                    <option value="${category.category_id}">${category.name}</option>
                </c:forEach>
            </select>
        </div><br/>
        <label>Question description:</label>
        <div>
            <textarea name="content" cols="50" rows="10"></textarea>
        </div>
        <div>
            <label>Option1:</label>
            <input type="text" name="option1">
        </div>
        <div>
            <label>Option2:</label>
            <input type="text" name="option2">
        </div>
        <div>
            <label>Option3:</label>
            <input type="text" name="option3">
        </div>
        <div>
            <label>Option4:</label>
            <input type="text" name="option4">
        </div>
        <label>The right answer:</label>
        <tbody>
        <tr>
            <input type="radio" name="solution" value="1" />Option1<br/>
            <input type="radio" name="solution" value="2" />Option2<br/>
            <input type="radio" name="solution" value="3" />Option3<br/>
            <input type="radio" name="solution" value="4" />Option4<br/>
        </tr>
        </tbody>
        <button type="submit">Add</button>
    </div>
</form>
</body>
</html>
