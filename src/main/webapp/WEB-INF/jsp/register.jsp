<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>

<body>
<div>
    <form method="post" action="/register">
        <div>
            <label>Username</label>
            <input type="text" name="username">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <label>First Name</label>
            <input type="text" name="firstname">
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastname">
        </div>
        <button type="submit">Sign Up</button>
    </form>
</div>
</body>

</html>
