<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/headerAdmin.jsp"/>
<html>
<head>
    <title>UserManage</title>
</head>
<body>
<label>Users</label><br/>
<tbody>
<table>
    <c:out value="    Full Name  |  Address  |  Email  |  Phone  | Status"></c:out>
    <c:forEach items="${users}" var="user" varStatus="loop">
        <form method="post">
            <tr>
                <td>${loop.index+1}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.address}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <c:choose>
                    <c:when test="${user.status eq true}">
                        <td>Active</td>
                        <td><button onclick="form.action='/user/manage/${user.user_id}';">SUSPEND</button></td>
                    </c:when>
                    <c:otherwise>
                        <td>Suspended</td>
                        <td><button onclick="form.action='/user/manage/${user.user_id}';">ACTIVATE</button></td>
                    </c:otherwise>
                </c:choose>
                <br/>
            </tr>
        </form>
    </c:forEach>
</table>
</tbody>
</body>
</html>
