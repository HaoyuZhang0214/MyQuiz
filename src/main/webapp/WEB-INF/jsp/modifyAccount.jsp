<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>ModifyAccount</title>
</head>
<body>
    <form method="post" action="/account/${user.user_id}">
        <div>
            <label>Username:</label>
            <c:out value="${user.username}"/>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" value=${user.password}>
        </div>
        <div>
            <label>First Name:</label>
            <input type="text" name="firstname" value=${user.firstname}>
        </div>
        <div>
            <label>Last Name:</label>
            <input type="text" name="lastname" value=${user.lastname}>
        </div>
        <div>
            <label>Address:</label>
            <input type="text" name="address" value=${user.address}>
        </div>
        <div>
            <label>Email:</label>
            <input type="text" name="email" value=${user.email}>
        </div>
        <div>
            <label>Phone:</label>
            <input type="text" name="phone" value=${user.phone}>
        </div>
        <button type="submit">Save</button>
    </form>

</body>
</html>
