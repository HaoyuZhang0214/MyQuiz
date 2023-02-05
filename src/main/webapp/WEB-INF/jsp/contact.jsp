<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="elements/header.jsp"/>
<html>
<head>
    <title>Contact</title>
</head>
<body>
    <form method="post" action="/contact">
        <div>
            <label>Contact Us</label>
            <div>
                <label>Subject</label>
                <input type="text" name="subject">
            </div>
            <div>
                <textarea name="message" cols="50" rows="10"></textarea>
            </div>
            <button type="submit">Send</button>
        </div>
    </form>
</body>
</html>
