<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>Account</title>
</head>

<body>
<div>
    <div>
        <label>Username:</label>
        <c:out value="${user.username}"/>
    </div>
    <div>
        <label>Password:</label>
        <c:out value="${user.password}"/>
    </div>
    <div>
        <label>First Name:</label>
        <c:out value="${user.firstname}"/>
    </div>
    <div>
        <label>Last Name:</label>
        <c:out value="${user.lastname}"/>
    </div>
    <div>
        <label>Address:</label>
        <c:out value="${user.address}"/>
    </div>
    <div>
        <label>Email:</label>
        <c:out value="${user.email}"/>
    </div>
    <div>
        <label>Phone:</label>
        <c:out value="${user.phone}"/>
    </div>
    <button onclick="window.location.href='/account/setting'">Modify Info</button>
</div>
</body>

</html>
